// lib
import styled from "styled-components";
import { media } from "../../styles/common/media";

//components

//img

export default function Title({ title, size, tabletSize, mobileSize }) {
  return (
    <TitleText size={size} tabletSize={tabletSize} mobileSize={mobileSize}>
      {title}
    </TitleText>
  );
}

const TitleText = styled.span`
  font-size: ${(props) => props.size}px;
  font-weight: bold;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};

  ${media.tablet`
    font-size: ${(props) => props.tabletSize}px;
  `}
`;
