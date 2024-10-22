import {ReactElement, useEffect, useState} from "react";
import {Stack} from "@mui/material";
import {getPollsPage} from "../api/PollsClient";
import type {ApiPoll} from "../api/models/PollModels";
import {ApiPage} from "../api/models/ApiContainers";
import ProgressButton from "./ProgressButton";
import {useNavigate} from "react-router-dom";

const PollsPage = (): ReactElement => {
    const navigate = useNavigate();
    const [pollPage, setPollPage] = useState<ApiPage<ApiPoll>>();

    useEffect(() => {
        getPollsPage(0, 5)
            .then(response => {
                setPollPage(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    const pollsButtons = pollPage?.data
        .map(poll => {
            return <ProgressButton
                key={`${poll.id}`}
                text={poll.name}
                sx={{
                    marginTop: '30px',
                    height: '80px',
                    backgroundColor: "rgba(90, 93, 94, 0.31)",
                    fontStyle: 'bold',
                    fontWeight: 'bold',
                    color: 'white',
                }}
                onClick={() => navigate(`/ui/polls/${poll.id}`)}
            />
        });

    return (
        <>
            <h1>Latest polls</h1>
            <Stack width={"25%"}>
                {pollsButtons}
            </Stack>
        </>
    );
}

export default PollsPage;