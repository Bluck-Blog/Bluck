import { useQuery } from "react-query";
import { Axios } from "./Axios";

const get = async (path) => {
  const { data } = await Axios.get(path);
  return data;
};

export const GET = {
  useAllPosts: (key, path) =>
    useQuery(key, () => get(path), {
      onError: (err) => console.log(err),
    }),
};
