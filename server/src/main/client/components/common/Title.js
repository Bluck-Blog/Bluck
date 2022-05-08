// lib
import styled from "styled-components";
//components

//img

export default function Title({ title }) {
  return <TitleText>{title}</TitleText>;
}

const TitleText = styled.p`
  font-size: 48px;
  font-weight: bold;
  /* margin-top: 20px; */
`;
