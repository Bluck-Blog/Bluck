// componenets

// lib
import styled from "styled-components";
import FindIdFormBox from "./component/FIndidFormBox";

// img

export default function FindIdPage() {
  return (
    <Wrapper>
      <FindIdFormBox />
    </Wrapper>
  );
}

const Wrapper = styled.section`
  width: 100%;
  height: 100%;
`;
