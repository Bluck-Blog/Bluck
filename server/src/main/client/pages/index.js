// lib
import { useSetRecoilState } from "recoil";

//components
import MainPage from "../components/main/MainPage";
import { GET } from "./api/Get";

//img
import Poster from "../styles/img/whiteProfile.png";

export default function Home(props) {
  return <MainPage content={props.content} />;
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
    {
      id: 6,
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
      id: 7,
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
      id: 8,
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
      id: 9,
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
      id: 10,
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

  try {
    const data = await GET.useAllPosts();

    return {
      props: {
        // 나중에 data로 바꾸기
        content,
      },
    };
  } catch (err) {
    return {
      props: {
        content,
      },
    };
  }
}
