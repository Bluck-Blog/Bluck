import axios from "axios";

export const baseURL = process.env.NEXT_PUBLIC_BASEURL;

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

Axios.interceptors.response.use(function (res) {
  const { data } = res;

  return data;
});
