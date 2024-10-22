package io.github.dominys.dizvoter.api.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PollsPageRequestParams(
    @NotNull
    @PositiveOrZero
    Long page,

    @NotNull
    @Min(1)
    @Max(100)
    Long pageSize
){

}
