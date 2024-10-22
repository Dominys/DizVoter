export interface ApiPoll {
    id: number;
    name: string;
    createTime: string;
}

export interface ApiPollDetails {
    id: number;
    name: string;
    voteOptions: ApiPollVoteOption[];
    createTime: string;
}

export interface ApiPollVoteOption {
    id: number;
    name: string;
    votes: number;
}
