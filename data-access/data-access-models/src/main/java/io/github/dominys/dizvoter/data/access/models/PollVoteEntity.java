package io.github.dominys.dizvoter.data.access.models;

import java.time.LocalDateTime;

public record PollVoteEntity(
    Long id,
    Long voteOptionId,
    String userName,
    LocalDateTime createTime
) {
}
