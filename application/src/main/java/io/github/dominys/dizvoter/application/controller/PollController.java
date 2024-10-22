package io.github.dominys.dizvoter.application.controller;

import io.github.dominys.dizvoter.api.models.ApiPage;
import io.github.dominys.dizvoter.api.models.ApiPoll;
import io.github.dominys.dizvoter.api.models.ApiPollDetails;
import io.github.dominys.dizvoter.api.models.ApiPollVote;
import io.github.dominys.dizvoter.api.models.ApiResponse;
import io.github.dominys.dizvoter.api.models.PollVotesPageRequestParams;
import io.github.dominys.dizvoter.api.models.PollsPageRequestParams;
import io.github.dominys.dizvoter.application.mapper.ApiPollMapper;
import io.github.dominys.dizvoter.businesslogic.api.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
public class PollController extends ApiExceptionHandler {

  private final PollService pollService;
  private final ApiPollMapper pollMapper;

  @GetMapping("/{id}")
  public ApiResponse<ApiPollDetails> getPoll(@PathVariable long id) {
    return pollService.findById(id)
        .map(pollMapper::toApiPoll)
        .map(ApiResponse::ofData)
        .orElseGet(ApiResponse::empty);
  }

  @GetMapping
  public ApiResponse<ApiPage<ApiPoll>> getPollsPage(
      @Valid
      PollsPageRequestParams requestParams
  ) {
    var page = pollService.readPage(requestParams.page(), requestParams.pageSize());
    return ApiResponse.ofData(pollMapper.toPollApiPage(page));
  }

  @PostMapping
  public ApiResponse<ApiPollDetails> createPoll(@RequestBody @Validated ApiPollDetails poll) {
    var createdPoll = pollService.createPoll(pollMapper.fromApiPoll(poll));
    return ApiResponse.ofData(pollMapper.toApiPoll(createdPoll));
  }

  @PostMapping("/{id}/votes/{optionId}")
  public ApiResponse<ApiPollDetails> vote(@PathVariable long id, @PathVariable long optionId) {
    pollService.vote(id, optionId, UUID.randomUUID().toString());
    return ApiResponse.ofData(pollMapper.toApiPoll(pollService.findById(id).orElseThrow()));
  }

  @GetMapping("/{id}/votes")
  public ApiResponse<ApiPage<ApiPollVote>> getVotesPage(
      @PathVariable long id,
      @Valid
      PollVotesPageRequestParams requestParams
  ) {
    var page = pollService.readVotesPage(id, requestParams.page(), requestParams.pageSize(),
        requestParams.voteOptionIds());
    return ApiResponse.ofData(pollMapper.toVotesApiPage(page));
  }

}
