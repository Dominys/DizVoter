import React from "react";
import "./App.css";
import {Navigate, Route, Routes} from "react-router-dom";
import UnknownDestinationPage from "./components/UnknownDestinationPage";
import PollsPage from "./components/PollsPage";
import PollPage from "./components/PollPage";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>DizVoter Logo</h1>
            </header>
            <div className="App-content">
                <Routes>
                    <Route path="/" element={<Navigate to="/ui/polls"/>}/>
                    <Route path="/ui" element={<Navigate to="/ui/polls"/>}/>
                    <Route path="/ui/polls" element={<PollsPage/>}/>
                    <Route path="/ui/polls/:pollId" element={<PollPage/>}/>
                    {/*<Route path="/ui/event/:eventId" element={<EventFeedMessagesPage/>}/>*/}
                    {/*<Route path="/ui/readerJob" element={<SportReaderJobsTable/>}/>*/}
                    {/*<Route path="/ui/readerJob/:jobId" element={<ReaderJobPage/>}/>*/}
                    <Route path="*" element={<UnknownDestinationPage/>}/>
                </Routes>
            </div>
        </div>
    );
}

export default App;
