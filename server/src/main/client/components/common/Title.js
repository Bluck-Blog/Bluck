// lib
import styled from "styled-components";

//components

//img

export default function Title({ title, size }) {
  return <TitleText size={size}>{title}</TitleText>;
}

const TitleText = styled.span`
  font-size: ${(props) => props.size}px;
  font-weight: bold;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;
