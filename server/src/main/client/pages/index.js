// lib

//components
import MainPage from "../components/main/MainPage";
import { GET } from "./api/Get";
import { content } from "../mok/contents";

//img

export default function Home(props) {
  return <MainPage content={props.content} />;
}

export async function getServerSideProps() {
  const fakeData = [...content];

  try {
    const data = await GET.selectAllPosts();

    return {
      props: {
        // 나중에 data로 바꾸기
        content: fakeData,
      },
    };
  } catch (err) {
    return {
      props: {
        content: fakeData,
      },
    };
  }
}
