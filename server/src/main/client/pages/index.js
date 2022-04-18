// lib
import Head from "next/head";
import Image from "next/image";
import styled from "styled-components";
import Footer from "../components/footer/Footer";

//components
import Nav from "../components/index/Nav";
import Main from "../components/main/main";
import Poster from "../styles/img/profile.png";

export default function Home({ content }) {
  return (
    <Wapper>
      <Nav />
      <RightSection>
        <Main content={content} />
        <Footer />
      </RightSection>
    </Wapper>
  );
}

export async function getServerSideProps() {
  const content = [
    {
      id: 1,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라",
      date: "2022.03.08",
      autor: "발빠른토깽이",
      commend: 20,
      find: 20,
      like: 20,
      hate: 20,
      img: Poster,
    },
    {
      id: 2,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라",
      date: "2022.03.08",
      autor: "발빠른토깽이",
      commend: 20,
      find: 20,
      like: 20,
      hate: 20,
      img: Poster,
    },
    {
      id: 3,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라",
      date: "2022.03.08",
      autor: "발빠른토깽이",
      commend: 20,
      find: 20,
      like: 20,
      hate: 20,
      img: Poster,
    },
    {
      id: 4,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라",
      date: "2022.03.08",
      autor: "발빠른토깽이",
      commend: 20,
      find: 20,
      like: 20,
      hate: 20,
      img: Poster,
    },
    {
      id: 5,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라",
      date: "2022.03.08",
      autor: "발빠른토깽이",
      commend: 20,
      find: 20,
      like: 20,
      hate: 20,
      img: Poster,
    },
  ];

  return {
    props: {
      content,
    },
  };
}

const Wapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const RightSection = styled.div``;
