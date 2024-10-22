package io.github.dominys.dizvoter.api.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record PollVotesPageRequestParams(
    @NotNull
    @PositiveOrZero
    Long page,

    @NotNull
    @Min(1)
    @Max(100)
    Long pageSize,

    @Size(min = 1, max = 7)
    Set<@NotNull Long> voteOptionIds
){

}
