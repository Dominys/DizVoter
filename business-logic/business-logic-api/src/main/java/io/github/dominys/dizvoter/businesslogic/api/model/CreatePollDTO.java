package io.github.dominys.dizvoter.businesslogic.api.model;

import java.util.List;

public record CreatePollDTO(
    String name,
    List<String> voteNames
) {
}
