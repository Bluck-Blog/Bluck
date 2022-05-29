// lib
import styled from "styled-components";
import SearchBox from "../common/SearchBox";
import FomBox from "./component/FomBox";
import RightSection from "./component/RightSection";
import Title from "../common/Title";

//components

//img

export default function LoginPage() {
  return (
    <Wrapper>
      {/* <Left>
        <Title title={"LOGIN"} />
        <RightSection />
      </Left> */}
      <FomBox />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  /* width: 84vw;
  height: 88vh;
  border-radius: 50px;
  background: ${(props) => props.theme.noticeColor};
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  padding: 0 1% 0 0; */
  width: 100%;
  height: 100%;
`;

const Left = styled.section`
  padding: 2% 1% 3% 3%;
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
