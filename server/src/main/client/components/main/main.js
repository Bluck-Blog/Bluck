// lib
import styled from "styled-components";

// components
import LeftSection from "./component/LeftSection";

//img
import RightSection from "./component/RightSection";

export default function Main({ content }) {
  return (
    <Wrapper>
      <LeftSection content={content} />
      {/* <RightSection /> */}
    </Wrapper>
  );
}

const Wrapper = styled.section`
  width: 84vw;
  height: 88vh;
  background: ${(props) => props.theme.noticeColor};
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
`;
