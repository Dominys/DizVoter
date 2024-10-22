package io.github.dominys.dizvoter.businesslogic.impl.service;

import io.github.dominys.dizvoter.businesslogic.api.model.CreatePollDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.PollDTO;
import io.github.dominys.dizvoter.data.access.api.repository.PollRepository;
import io.github.dominys.dizvoter.data.access.api.repository.PollVoteOptionsRepository;
import io.github.dominys.dizvoter.data.access.api.repository.PollVotesRepository;
import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.github.dominys.dizvoter.data.access.models.PollVoteOptionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PollServiceImplTest {

  @InjectMocks
  private PollServiceImpl pollService;

  @Mock
  private PollRepository pollRepository;
  @Mock
  private PollVoteOptionsRepository voteOptionsRepository;
  @Mock
  private PollVotesRepository votesRepository;


  @Test
  void testCreatePoll() {
    var pollId = 11L;
    var pollName = "testName";
    var pollOptions = List.of("opt1", "opt2");

    var createdPoll = PollEntity.builder()
        .id(pollId)
        .name("testName")
        .build();

    var createdOptions = List.of(mock(PollVoteOptionEntity.class));

    when(pollRepository.createPoll(any())).thenReturn(createdPoll);
    when(voteOptionsRepository.findByPollId(pollId)).thenReturn(createdOptions);

    var createPollDTO = new CreatePollDTO(pollName, pollOptions);

    var expected = PollDTO.builder()
        .poll(createdPoll)
        .voteOptions(createdOptions)
        .build();

    var result = pollService.createPoll(createPollDTO);

    assertThat(result).isEqualTo(expected);

    var ordered = inOrder(pollRepository, voteOptionsRepository);
    ordered.verify(pollRepository).createPoll(PollEntity.builder().name(pollName).build());
    ordered.verify(voteOptionsRepository).createVoteOptions(pollId, pollOptions);
    ordered.verify(voteOptionsRepository).findByPollId(pollId);
    ordered.verifyNoMoreInteractions();
  }

}
