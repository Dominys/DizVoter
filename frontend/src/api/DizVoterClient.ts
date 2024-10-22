import axios from "axios";

const client = axios.create({
    baseURL : 'http://localhost:8080/api',
    headers: {
        'Content-Type': "application/json",
        'Access-Control-Allow-Origin': '*',
        timeout : 1000,
    },
    fetchOptions: {
        mode: 'no-cors',
    }
});

export default client;