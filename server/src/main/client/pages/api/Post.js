import { useMutation } from "react-query";
import { root } from "../_app";
import { Axios } from "./Axios";

export const post = async (path, body) => {
  const { data } = await Axios.post(path, body);
  return data;
};

export const POST = {
  confirmEmail: (path) => Axios.post(path, data),
  useLogin: (path) => useMutation((data) => post(path, data)),
  mutateCallBack: (key) => ({
    onSuccess: () => {
      const keyArr = [key];
      return root.invalidateQueries(keyArr);
    },
    onError: (err) => console.log(err),
  }),
};
