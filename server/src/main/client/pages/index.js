import Head from "next/head";
import Image from "next/image";
import { useRecoilState } from "recoil";
import Nav from "../components/index/Nav";
import { darkMode } from "../state/atom";

export default function Home() {
  const [state, setState] = useRecoilState(darkMode);

  const darkModeHandle = () => {
    setState((prev) => !prev);
  };

  return (
    <>
      <Nav />
    </>
  );
}
