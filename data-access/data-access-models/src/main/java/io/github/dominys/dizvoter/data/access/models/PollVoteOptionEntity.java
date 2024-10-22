package io.github.dominys.dizvoter.data.access.models;

public record PollVoteOptionEntity(
    Long id,
    Long pollId,
    String name,
    Long votes
) {
}
