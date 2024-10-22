package io.github.dominys.dizvoter.application.mapper;

import io.github.dominys.dizvoter.api.models.ApiPage;
import io.github.dominys.dizvoter.api.models.ApiPoll;
import io.github.dominys.dizvoter.api.models.ApiPollDetails;
import io.github.dominys.dizvoter.api.models.ApiPollVote;
import io.github.dominys.dizvoter.api.models.ApiPollVoteOption;
import io.github.dominys.dizvoter.businesslogic.api.model.CreatePollDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.EntityPageDTO;
import io.github.dominys.dizvoter.businesslogic.api.model.PollDTO;
import io.github.dominys.dizvoter.data.access.models.PollEntity;
import io.github.dominys.dizvoter.data.access.models.PollVoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ApiPollMapper {

  @Mapping(source = "voteOptions", target = "voteNames")
  CreatePollDTO fromApiPoll(ApiPollDetails poll);

  default String voteToName(ApiPollVoteOption apiPollVoteOption) {
    return apiPollVoteOption.name();
  }

  @Mapping(source = "poll", target = ".")
  ApiPollDetails toApiPoll(PollDTO poll);

  ApiPage<ApiPoll> toPollApiPage(EntityPageDTO<PollEntity> page);

  ApiPage<ApiPollVote> toVotesApiPage(EntityPageDTO<PollVoteEntity> page);

}
