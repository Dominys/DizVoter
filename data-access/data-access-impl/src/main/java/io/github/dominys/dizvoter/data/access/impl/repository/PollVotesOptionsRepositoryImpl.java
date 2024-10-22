package io.github.dominys.dizvoter.data.access.impl.repository;


import io.github.dominys.dizvoter.data.access.api.repository.PollVoteOptionsRepository;
import io.github.dominys.dizvoter.data.access.models.PollVoteOptionEntity;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static io.github.dominys.dizvoter.data.access.impl.jooq.Tables.POLL_VOTES;
import static io.github.dominys.dizvoter.data.access.impl.jooq.Tables.POLL_VOTE_OPTIONS;

@Repository
@RequiredArgsConstructor
@Timed("pollVoteOptionsRepository")
public class PollVotesOptionsRepositoryImpl implements PollVoteOptionsRepository {

  private final DSLContext dsl;

  @Override
  public void createVoteOptions(long pollId, Collection<String> options) {
    var inserts = options.stream()
        .map(f -> dsl.insertInto(POLL_VOTE_OPTIONS, POLL_VOTE_OPTIONS.POLL_ID, POLL_VOTE_OPTIONS.NAME)
            .values(pollId, f))
        .toList();
    dsl.batch(inserts)
        .execute();
  }

  @Override
  public List<PollVoteOptionEntity> findByPollId(long id) {
    return dsl.select(
            POLL_VOTE_OPTIONS.asterisk(),
            DSL.count(POLL_VOTES.ID).as("votes"))
        .from(POLL_VOTE_OPTIONS)
        .leftJoin(POLL_VOTES).on(POLL_VOTES.VOTE_OPTION_ID.eq(POLL_VOTE_OPTIONS.ID))
        .where(POLL_VOTE_OPTIONS.POLL_ID.eq(id))
        .groupBy(POLL_VOTE_OPTIONS.ID)
        .orderBy(POLL_VOTE_OPTIONS.NAME)
        .fetchInto(PollVoteOptionEntity.class);
  }

  @Override
  public boolean isOptionExists(long pollId, long optionId) {
    return dsl.fetchExists(POLL_VOTE_OPTIONS,
        POLL_VOTE_OPTIONS.ID.eq(optionId)
        .and(POLL_VOTE_OPTIONS.POLL_ID.eq(pollId)));
  }
}
