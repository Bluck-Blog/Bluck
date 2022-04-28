// lib
import styled from "styled-components";

//components

//img

export default function Title() {
  return (
    <Left>
      <TitleText>LOGIN</TitleText>
    </Left>
  );
}

const Left = styled.section`
  padding: 3%;
  width: 30%;
  height: 100%;
`;
const TitleText = styled.p`
  font-size: 48px;
  font-weight: bold;
  margin-top: 20px;
`;
