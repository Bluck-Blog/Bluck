// lib

// components
import * as S from "../../styles/detail/DetailPageStyle";
import DetailSection from "./component/DetailSection";

export default function DetailPage({ fakeData }) {
  return (
    <S.Wrapper>
      <DetailSection fakeData={fakeData} />
    </S.Wrapper>
  );
}
