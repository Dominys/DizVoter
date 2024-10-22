package io.github.dominys.dizvoter.businesslogic.api.service;

import io.github.dominys.dizvoter.businesslogic.api.model.CreatePollDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.EntityPageDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.PollDTO;
import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.github.dominys.dizvoter.data.access.models.PollVoteEntity;

import java.util.Optional;
import java.util.Set;

public interface PollService {

  PollDTO createPoll(CreatePollDTO createPollDTO);

  Optional<PollDTO> findById(long id);

  EntityPageDTO<PollEntity> readPage(long page, long pageSize);

  void vote(long pollId, long optionId, String userName);

  EntityPageDTO<PollVoteEntity> readVotesPage(long pollId, long page, long pageSize, Set<Long> voteOptionIds);
}
