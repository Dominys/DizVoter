import {ReactElement, useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {getPoll, makeVote} from "../api/PollsClient";
import {ApiPollDetails} from "../api/models/PollModels";
import {Stack} from "@mui/material";
import ProgressButton from "./ProgressButton";

const PollPage = (): ReactElement => {
    const {pollId} = useParams() as { pollId: string };
    const [pollDetails, setPollDetails] = useState<ApiPollDetails>();
    const [selectedOption, setSelectedOption] = useState<number>();
    const [voted, setVoted] = useState(false);

    useEffect(() => {
        getPoll(parseInt(pollId))
            .then(response => {
                setPollDetails(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [
        pollId
    ]);

    const handleOptionSelect = (optionId: number) => {
        setSelectedOption(optionId);
    };

    const handleSubmitVote = () => {
        if (selectedOption) {
            makeVote(parseInt(pollId), selectedOption)
                .then(response => {
                    setPollDetails(response.data);
                    setVoted(true);
                })
                .catch(error => {
                    console.error(error);
                });
        }
    };

    const totalVotes = (pollDetails?.voteOptions ?? [])
        .map(option => option.votes)
        .reduce((acc, val) => acc + val, 0);

    const optionButtons = pollDetails?.voteOptions
        .map(option => {
            const percent = totalVotes === 0 ? 0 : Math.round(100 * option.votes / totalVotes);
            return <ProgressButton
                key={`${option.id}`}
                text={option.name}
                sx={{
                    marginTop: '30px',
                    height: '80px',
                    backgroundColor: !voted && selectedOption === option.id ? "rgba(223,79,250,0.60)" : "rgba(90, 93, 94, 0.31)",
                    fontStyle: 'bold',
                    fontWeight: 'bold',
                    color: 'white',
                }}
                onClick={() => handleOptionSelect(option.id)}
                progressVisible={voted}
                percent={percent}
            />
        });

    const submitButton = voted
        ? null
        : <ProgressButton
            text="Submit"
            onClick={() => handleSubmitVote()}
            sx={{
                marginTop: '30px',
                height: '80px',
                fontStyle: 'bold',
                fontWeight: 'bold',
                backgroundColor: 'white',
                color: "#9468F4"
            }}/>;
    return (
        <>
            <h1>{pollDetails?.name}?</h1>
            <Stack width={"25%"}>
                {optionButtons}
                {submitButton}
            </Stack>

        </>
    );
}

export default PollPage;