import client from "./DizVoterClient";
import {type ApiPage, ApiResponse} from "./models/ApiContainers";
import {type ApiPoll, ApiPollDetails} from "./models/PollModels";

export const getPollsPage = async (page: number, pageSize: number): Promise<ApiResponse<ApiPage<ApiPoll>>> => {
    return client.get<ApiResponse<ApiPage<ApiPoll>>>(`/polls`, {
        params: { page: page, pageSize: pageSize }
    })
        .then(value => value.data);
};

export const getPoll = async (pollId: number): Promise<ApiResponse<ApiPollDetails>> => {
    return client.get<ApiResponse<ApiPollDetails>>(`/polls/${pollId}`)
        .then(value => value.data);
};

export const makeVote = async (pollId: number, optionId: number): Promise<ApiResponse<ApiPollDetails>> => {
    return client.post<ApiResponse<ApiPollDetails>>(`/polls/${pollId}/votes/${optionId}`, {
        params: { id: pollId, optionId: optionId }
    })
        .then(value => value.data);
};