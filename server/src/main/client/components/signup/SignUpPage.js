// lib
import styled from "styled-components";
import SearchBox from "../common/SearchBox";
import Title from "../common/Title";
import JoinForm from "./components/JoinForm";

//components

//img

export default function SignUpPage() {
  return (
    <Wrapper>
      <Left>
        <Title title={"JOIN"} />
        <SearchBox />
      </Left>
      <JoinForm />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 84vw;
  height: 88vh;
  border-radius: 50px;
  background: ${(props) => props.theme.noticeColor};
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  padding: 0 1% 0 0;
`;

const Left = styled.section`
  /* padding: 3%; */
  padding: 2% 1% 3% 3%;
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
