package io.github.dominys.dizvoter.data.access.impl.repository;

import io.github.dominys.dizvoter.data.access.api.repository.PollVotesRepository;
import io.github.dominys.dizvoter.data.access.models.PollVoteEntity;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static io.github.dominys.dizvoter.data.access.impl.jooq.Tables.POLL_VOTES;
import static io.github.dominys.dizvoter.data.access.impl.jooq.Tables.POLL_VOTE_OPTIONS;

@Repository
@RequiredArgsConstructor
@Timed("pollVotesRepository")
public class PollVotesRepositoryImpl implements PollVotesRepository {

  private final DSLContext dsl;

  @Override
  public void createVote(long voteOptionId, String userName) {
    dsl.insertInto(POLL_VOTES)
        .set(POLL_VOTES.VOTE_OPTION_ID, voteOptionId)
        .set(POLL_VOTES.USER_NAME, userName)
        .set(POLL_VOTES.CREATE_TIME, LocalDateTime.now())
        .execute();
  }

  @Override
  public List<PollVoteEntity> readPage(long pollId, Set<Long> voteOptionIds, long page, long pageSize) {


    return dsl.select(POLL_VOTES.asterisk())
        .from(POLL_VOTE_OPTIONS)
        .join(POLL_VOTES).on(POLL_VOTES.VOTE_OPTION_ID.eq(POLL_VOTE_OPTIONS.ID))
        .where(createPageConditions(voteOptionIds))
        .orderBy(POLL_VOTES.CREATE_TIME)
        .offset(page * pageSize)
        .limit(pageSize)
        .fetchInto(PollVoteEntity.class);
  }

  @Override
  public long countTotal(long pollId, Set<Long> voteOptionIds) {
    return dsl.selectCount()
        .from(POLL_VOTE_OPTIONS)
        .join(POLL_VOTES).on(POLL_VOTES.VOTE_OPTION_ID.eq(POLL_VOTE_OPTIONS.ID))
        .where(createPageConditions(voteOptionIds))
        .fetchOneInto(Long.class);
  }

  private List<Condition> createPageConditions(Set<Long> voteOptionIds) {
    return CollectionUtils.isEmpty(voteOptionIds)
        ? List.of()
        : List.of(POLL_VOTES.VOTE_OPTION_ID.in(voteOptionIds));
  }

}
