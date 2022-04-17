import styled from "styled-components";

export default function Footer() {
  return (
    <Wrapper>
      <Text>Made by 추동욱 / 이지현 / 정인아</Text>
      <Text>Copyright © 2022 bluck. All rights reserved.</Text>
    </Wrapper>
  );
}

const Wrapper = styled.footer`
  width: 100%;
  height: 3vh;
  text-align: right;
  color: ${(props) => props.theme.textColor};
`;

const Text = styled.p`
  margin-top: 15px;
  font-weight: 600;
  font-size: 12px;
`;
