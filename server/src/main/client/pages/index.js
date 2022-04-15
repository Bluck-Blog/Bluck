// lib
import Head from "next/head";
import Image from "next/image";
import styled from "styled-components";

//components
import Nav from "../components/index/Nav";
import Main from "../components/main/main";

export default function Home() {
  return (
    <Wapper>
      <Nav />
      <Main />
    </Wapper>
  );
}

const Wapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;
