package io.github.dominys.dizvoter.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiPollDetails(
    Long id,
    @NotBlank
    String name,
    @NotNull
    @Size(min = 2, max = 7)
    List<@Valid @NotNull ApiPollVoteOption> voteOptions,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createTime
) {
}
