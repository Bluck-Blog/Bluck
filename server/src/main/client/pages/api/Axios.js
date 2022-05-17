import axios from "axios";

export const baseURL = "http://localhost:8080/";

export const Axios = axios.create({
  baseURL,
  headers: {
    "Content-Type": "application/json",
  },
  timeout: 1000,
});
