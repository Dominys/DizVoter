@ApiTest @SystemState
Feature: Verifying pols functionality

  Scenario: Verify that polls are created successfully

    When I create polls:
      | name       | voteOptions                      |
      | testPoll_1 | option_1_1,option_1_2            |
      | testPoll_2 | option_2_1,option_2_2,option_2_3 |

    Then I verify that polls page=0 for pageSize=10 has polls:
      | testPoll_1 |
      | testPoll_2 |
