// components
import DarkModetn from "../common/DarkModeBtn";

// lib
import styled from "styled-components";

// img

export default function Footer() {
  return (
    <Wrapper>
      <DarkModetn />
      <div>
        <Text>Made by 추동욱 / 이지현 / 정인아</Text>
        <Text>Copyright © 2022 bluck. All rights reserved.</Text>
      </div>
    </Wrapper>
  );
}

const Wrapper = styled.footer`
  width: 96vw;
  height: 5vh;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
`;

const Text = styled.p`
  font-weight: 600;
  font-size: 12px;
  text-align: right;
  color: ${(props) => props.theme.textColor};
`;
