package io.github.dominys.dizvoter.api.models;

import java.time.LocalDateTime;

public record ApiPoll(
    Long id,
    String name,
    LocalDateTime createTime
) {
}
