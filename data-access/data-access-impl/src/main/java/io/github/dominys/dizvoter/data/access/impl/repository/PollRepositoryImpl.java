package io.github.dominys.dizvoter.data.access.impl.repository;

import io.github.dominys.dizvoter.data.access.api.repository.PollRepository;
import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.github.dominys.dizvoter.data.access.impl.jooq.Tables.POLLS;

@Repository
@RequiredArgsConstructor
@Timed("pollRepository")
public class PollRepositoryImpl implements PollRepository {

  private final DSLContext dsl;

  @Override
  public PollEntity createPoll(PollEntity poll) {
    var createTime = LocalDateTime.now();
    var id = dsl.insertInto(POLLS)
        .set(POLLS.NAME, poll.name())
        .set(POLLS.CREATE_TIME, createTime)
        .returningResult(POLLS.ID)
        .fetchOneInto(Long.class);
    return poll
        .toBuilder()
        .id(id)
        .createTime(createTime)
        .build();
  }

  @Override
  public Optional<PollEntity> findById(long id) {
    return dsl.selectFrom(POLLS)
        .where(POLLS.ID.eq(id))
        .fetchOptionalInto(PollEntity.class);
  }

  @Override
  public List<PollEntity> readPage(long page, long pageSize) {
    return dsl.selectFrom(POLLS)
        .orderBy(POLLS.CREATE_TIME)
        .offset(page * pageSize)
        .limit(pageSize)
        .fetchInto(PollEntity.class);
  }

  @Override
  public long countTotal() {
    return dsl.selectCount()
        .from(POLLS)
        .fetchOneInto(Long.class);
  }

}
