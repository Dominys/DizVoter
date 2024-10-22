package io.github.dominys.dizvoter.component.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.github.dominys.dizvoter.component.tests.model.PollCreationModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ParameterTypes {

  private final ObjectMapper objectMapper;

  @DataTableType
  public PollCreationModel toPollCreationModel(Map<String, String> row) {
    return new PollCreationModel(row.get("name"), List.of(row.get("voteOptions").split(",")));
  }

}
