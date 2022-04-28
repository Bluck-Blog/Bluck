// lib
import styled from "styled-components";

//components
import SearchBox from "../../common/SearchBox";

//img

export default function RightSection() {
  return (
    <Right>
      <SearchBox />
    </Right>
  );
}

const Right = styled.section`
  width: 30%;
  height: 100%;
  padding-top: 1.3%;
`;
