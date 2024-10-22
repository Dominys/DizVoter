package io.github.dominys.dizvoter.data.access.models;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record PollEntity(
    @With
    Long id,
    String name,
    LocalDateTime createTime
) {
}
