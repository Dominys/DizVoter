package io.github.dominys.dizvoter.businesslogic.api.model;

import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.github.dominys.dizvoter.data.access.models.PollVoteOptionEntity;
import lombok.Builder;

import java.util.List;

@Builder
public record PollDTO(
    PollEntity poll,
    List<PollVoteOptionEntity> voteOptions
) {
}
