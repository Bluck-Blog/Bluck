import axios from "axios";

export const baseURL = "http://localhost:8084/";

export const Axios = axios.create({
  baseURL,
  headers: {
    "Content-Type": "application/json",
  },
  timeout: 1000,
});

Axios.interceptors.request.use(function (config) {
  const accessToken = sessionStorage.getItem("accessToken");
  config.headers.Authorization = accessToken ? accessToken : null;

  return config;
});
