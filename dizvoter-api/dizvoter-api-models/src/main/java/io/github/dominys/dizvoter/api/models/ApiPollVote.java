package io.github.dominys.dizvoter.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ApiPollVote(
    Long id,
    Long voteOptionId,
    String userName,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createTime
) {
}
