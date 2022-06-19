//lib

//components
import DetailContent from "./DetailContent";
import SearchBox from "../../common/SearchBox";
import * as S from "../../../styles/main/RightSectionStyle";

//img

export default function RightSection() {
  return (
    <S.Wrapper>
      <SearchBox />
      <DetailContent />
    </S.Wrapper>
  );
}
