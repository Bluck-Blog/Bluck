// lib
import styled from "styled-components";
import { useState } from "react";
//components
import Main from "../components/main/main";
import { GET } from "./api/Get";

//img
import Poster from "../styles/img/whiteProfile.png";
import { useQuery } from "react-query";
import { Axios } from "./api/Axios";

export default function Home({ content }) {
  const aaa = useQuery("posts", () =>
    fetch("http://localhost:8080/api/posts/72").then((res) => res.json())
  );

  // const aaa = GET.useAllPosts("posts", "/api/posts/72");
  console.log("aaa==");
  console.log(aaa);
  return <Main content={content} />;
}

export async function getServerSideProps() {
  const content = [
    {
      id: 1,
      title: "제목을 적어주세용",
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라",
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
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라",
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
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라",
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
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라",
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
      body: "내용입니다. 쏼라쏼라 쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라쏼라",
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
