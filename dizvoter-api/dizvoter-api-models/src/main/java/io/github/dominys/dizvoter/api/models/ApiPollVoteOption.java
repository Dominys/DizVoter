package io.github.dominys.dizvoter.api.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ApiPollVoteOption(
    Long id,
    @NotBlank
    String name,
    Long votes
) {
}
