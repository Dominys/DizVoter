package io.github.dominys.dizvoter.data.access.api.repository;

import io.github.dominys.dizvoter.data.access.models.PollVoteOptionEntity;

import java.util.Collection;
import java.util.List;

public interface PollVoteOptionsRepository {

  void createVoteOptions(long pollId, Collection<String> options);

  List<PollVoteOptionEntity> findByPollId(long id);

  boolean isOptionExists(long pollId, long optionId);
}
