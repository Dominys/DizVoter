package io.github.dominys.dizvoter.businesslogic.impl.service;

import io.github.dominys.dizvoter.businesslogic.api.exception.RecordNotFoundException;
import io.github.dominys.dizvoter.businesslogic.api.model.CreatePollDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.EntityPageDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.PollDTO;
import io.github.dominys.dizvoter.businesslogic.api.service.PollService;
import io.github.dominys.dizvoter.data.access.api.repository.PollRepository;
import io.github.dominys.dizvoter.data.access.api.repository.PollVoteOptionsRepository;
import io.github.dominys.dizvoter.data.access.api.repository.PollVotesRepository;
import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.github.dominys.dizvoter.data.access.models.PollVoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

  private final PollRepository pollRepository;
  private final PollVoteOptionsRepository voteOptionsRepository;
  private final PollVotesRepository votesRepository;

  @Override
  @Transactional
  public PollDTO createPoll(CreatePollDTO createPollDTO) {
    var pollEntity = PollEntity.builder()
        .name(createPollDTO.name())
        .build();
    var createdPoll = pollRepository.createPoll(pollEntity);
    voteOptionsRepository.createVoteOptions(createdPoll.id(), createPollDTO.voteNames());
    var createdOptions = voteOptionsRepository.findByPollId(createdPoll.id());
    return PollDTO.builder()
        .poll(createdPoll)
        .voteOptions(createdOptions)
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PollDTO> findById(long id) {
    return pollRepository.findById(id)
        .map(poll ->
            PollDTO.builder()
              .poll(poll)
              .voteOptions(voteOptionsRepository.findByPollId(poll.id()))
              .build());
  }

  @Override
  @Transactional(readOnly = true)
  public EntityPageDTO<PollEntity> readPage(long page, long pageSize) {
    var totalCount = pollRepository.countTotal();
    var pollPage = pollRepository.readPage(page, pageSize);
    return new EntityPageDTO<>(pollPage, totalCount);
  }

  @Override
  @Transactional
  public void vote(long pollId, long optionId, String userName) {
    if(!voteOptionsRepository.isOptionExists(pollId, optionId)) {
      throw new RecordNotFoundException("Vote option does not exist");
    }
    votesRepository.createVote(optionId, userName);
  }

  @Override
  @Transactional(readOnly = true)
  public EntityPageDTO<PollVoteEntity> readVotesPage(
      long pollId, long page, long pageSize, Set<Long> voteOptionIds) {
    var votesPage = votesRepository.readPage(pollId, voteOptionIds, page, pageSize);
    var totalCount = votesRepository.countTotal(pollId, voteOptionIds);
    return new EntityPageDTO<>(votesPage, totalCount);
  }
}
