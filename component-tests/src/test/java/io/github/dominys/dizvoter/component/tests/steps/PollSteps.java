package io.github.dominys.dizvoter.component.tests.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.dominys.dizvoter.api.models.ApiPage;
import io.github.dominys.dizvoter.api.models.ApiPoll;
import io.github.dominys.dizvoter.api.models.ApiPollDetails;
import io.github.dominys.dizvoter.api.models.ApiPollVoteOption;
import io.github.dominys.dizvoter.api.models.PollsPageRequestParams;
import io.github.dominys.dizvoter.client.DizVoterPollsClient;
import io.github.dominys.dizvoter.component.tests.model.PollCreationModel;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.InstanceOfAssertFactories;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PollSteps {

  private final DizVoterPollsClient pollsClient;

  @Given("I create polls:")
  public void mockCreateMatchResponse(List<PollCreationModel> polls) {
    polls.stream()
        .map(p -> ApiPollDetails.builder()
            .name(p.name())
            .voteOptions(p.voteOptions().stream()
                .map(option -> ApiPollVoteOption.builder()
                    .name(option)
                    .build())
                .toList())
            .build())
        .forEach(pollsClient::createPoll);
  }

  @Then("I verify that polls page={int} for pageSize={int} has polls:")
  public void heartbeatMessageWithStatusUp(long page, long pageSize, List<String> expectedNames) {
    var response = pollsClient.getPollsPage(new PollsPageRequestParams(page, pageSize));
    var polls = response.data();
    assertThat(polls)
        .isNotNull()
        .extracting(ApiPage::data, as(InstanceOfAssertFactories.LIST))
        .extracting("name")
        .containsExactlyElementsOf(expectedNames);

    assertThat(polls.data())
        .extracting(ApiPoll::createTime)
        .isNotNull();
  }
}