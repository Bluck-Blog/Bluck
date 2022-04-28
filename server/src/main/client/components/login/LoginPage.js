// lib
import styled from "styled-components";
import SearchBox from "../common/SearchBox";
import FomBox from "./component/FomBox";
import RightSection from "./component/RightSection";
import Title from "./component/Title";

//components

//img

export default function LoginPage() {
  return (
    <Wrapper>
      <Title />
      <FomBox />
      <RightSection />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 84vw;
  height: 88vh;
  border-radius: 50px;
  background: ${(props) => props.theme.noticeColor};
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 3% 0 0;
`;
