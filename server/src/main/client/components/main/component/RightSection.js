//lib
import styled from "styled-components";
import DetailContent from "./DetailContent";
import SearchBox from "./SearchBox";

//components

//img

export default function RightSection() {
  return (
    <Wrapper>
      <SearchBox />
      <DetailContent />
    </Wrapper>
  );
}

const Wrapper = styled.section`
  width: 50%;
  height: 95%;
  padding: 0 3% 0 0;
  /* display: flex;
  justify-content: space-between;
  align-items: flex-end;
  flex-direction: column; */
`;
