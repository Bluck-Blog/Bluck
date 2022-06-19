import styled from "styled-components";
import { media } from "./media";

export const TitleText = styled.span`
  font-size: ${(props) => props.size}px;
  font-weight: bold;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};

  ${media.tablet`
    font-size: ${(props) => props.tabletSize}px;
  `}

  ${media.mobile`
  font-size: ${(props) => props.mobileSize}px;
  `}
`;
