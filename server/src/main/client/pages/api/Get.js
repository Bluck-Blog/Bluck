import { Axios } from "./Axios";

export const get = async (path) => {
  const data = await Axios.get(path);
  return data;
};

export const GET = {
  selectAllPosts: async () => await Axios.get("api/posts"),
  sentAuthEmail: async (data) => await Axios.get("api/session/verify", data),
};
