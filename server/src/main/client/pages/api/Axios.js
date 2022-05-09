import axios from "axios";

// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
// export const baseURl = "http://localhost:8080/";
export const baseURl = "http://192.168.1.125:8080/";

export const Axios = axios.create({
  baseURl,
  headers: {
    "Content-Type": "application/json",
  },
  timeout: 1000,
});
