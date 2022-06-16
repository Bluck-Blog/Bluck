// componenets

// lib
import styled from "styled-components";
import FindPwFormBox from "./component/FindPwFormBox";

// img

export default function FindPwPage() {
  return (
    <Wrapper>
      <FindPwFormBox />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
