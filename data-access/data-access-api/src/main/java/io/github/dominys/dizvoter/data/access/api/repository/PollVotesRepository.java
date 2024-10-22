package io.github.dominys.dizvoter.data.access.api.repository;

import io.github.dominys.dizvoter.data.access.models.PollVoteEntity;

import java.util.List;
import java.util.Set;

public interface PollVotesRepository {

  void createVote(long voteOptionId, String userName);

  List<PollVoteEntity> readPage(long pollId, Set<Long> voteOptionIds, long page, long pageSize);

  long countTotal(long pollId, Set<Long> voteOptionIds);
}
