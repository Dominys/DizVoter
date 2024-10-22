package io.github.dominys.dizvoter.client;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.dominys.dizvoter.api.models.ApiPage;
import io.github.dominys.dizvoter.api.models.ApiPoll;
import io.github.dominys.dizvoter.api.models.ApiPollDetails;
import io.github.dominys.dizvoter.api.models.ApiPollVote;
import io.github.dominys.dizvoter.api.models.ApiResponse;
import io.github.dominys.dizvoter.api.models.PollVotesPageRequestParams;
import io.github.dominys.dizvoter.api.models.PollsPageRequestParams;

@Headers({
        "Content-Type: application/json",
        "Accept: application/json"
})
public interface DizVoterPollsClient {

    @RequestLine("GET /api/polls/{id}")
    ApiResponse<ApiPollDetails> getPoll(@Param("id") long id);

    @RequestLine("GET /api/polls")
    ApiResponse<ApiPage<ApiPoll>> getPollsPage(@QueryMap PollsPageRequestParams requestParams);

    @RequestLine("POST /api/polls")
    ApiResponse<ApiPollDetails> createPoll(ApiPollDetails poll);

    @RequestLine("POST /api/polls/{id}/votes/{optionId}")
    ApiResponse<ApiPollDetails> vote(@Param("id") long pollId, @Param("optionId") long optionId);

    @RequestLine("GET /api/polls/{id}/votes")
    ApiResponse<ApiPage<ApiPollVote>> getVotesPage(@Param("id") long pollId, @QueryMap PollVotesPageRequestParams requestParams);

}