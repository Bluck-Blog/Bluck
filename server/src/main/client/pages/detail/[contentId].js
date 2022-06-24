// lib
import { useRouter } from "next/router";
import DetailPage from "../../components/detail/DetailPage";
import { content } from "../../mok/contents";

// components

export default function Detail({ fakeData }) {
  return <DetailPage fakeData={fakeData} />;
}

export async function getServerSideProps(context) {
  const {
    query: { contentId },
  } = context;

  const fakeData = content.find((blog) => blog.id === parseInt(contentId));

  return {
    props: {
      fakeData,
    },
  };
}
