// lib

// components
import * as S from "../../../styles/detail/DetailTagBoxStyle";

export default function DetailTagBox({ fakeData }) {
  const { tag } = fakeData;
  return (
    <S.TagBox>
      {tag.length > 0
        ? tag.map((tag, index) => <S.TagText key={index}>{tag}</S.TagText>)
        : null}
    </S.TagBox>
  );
}
