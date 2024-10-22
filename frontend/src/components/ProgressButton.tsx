import React, {ReactElement} from "react";
import {Button, ButtonProps, styled} from "@mui/material";

export interface ProgressButtonProps extends ButtonProps {
    text: string
    percent?: number
    progressVisible?: boolean
}

const ProgressButton = (
    {text, percent, progressVisible, ...restProps}: ProgressButtonProps): ReactElement => {
    const StyledButton = styled(Button)(() => ({
        display: 'flex',
        border: '3px solid white',
        borderRadius: '10px',
        fontSize: 24,
        fontFamily: 'sans-serif',
        textTransform: 'none',
        "& .content": {
            zIndex: 1,
            display: 'flex',
            width: '100%'
        },
        "& .full-width": {
            width: '100%'
        },
        "& .main": {
            justifyContent: "center",
            display: 'flex',
            overflow: 'hidden'
        },
        "& .content .percent": {
            display: 'flex',
            justifyContent: 'end'
        },
        "& .progress-inner": {
            position: 'absolute',
            left: 0,
            top: 0,
            background: '#9468F4',
            width: `${percent}%`,
            height: '100%',
            borderRadius: '7px 4px 4px 7px'
        }
    }))

    const content = progressVisible
        ? <>
            <div className="content">
                <span className="full-width"/>
                <span className="full-width main">{text}</span>
                <span className="full-width percent">{percent}%</span>
            </div>
            <span className="progress-inner"></span>
        </>
        : <>
            <span className="full-width main">{text}</span>
        </>;
    return (
        <StyledButton {...restProps}>
            {content}
        </StyledButton>
    );
}

export default ProgressButton;