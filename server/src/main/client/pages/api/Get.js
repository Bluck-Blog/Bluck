import { useQuery } from "react-query";
import { Axios } from "./Axios";

export const get = async (path) => {
  const data = await Axios.get(path);
  return data;
};

export const GET = {
  useAllPosts: async () => await get("api/posts/72"),
};
