package io.github.dominys.dizvoter.component.tests.model;

import java.util.List;

public record PollCreationModel(
    String name,
    List<String> voteOptions
) {
}
