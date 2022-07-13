import styled from "styled-components";
import { media } from "../common/media";

export const HeaderWrapper = styled.header`
  width: 100%;
  /* height: 5%; */
  padding-top: 1%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1% 0;

  ${media.mobile`
  margin: 2.5% 0;
  `}
`;

export const BtnBox = styled.div`
  width: 7%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  ${media.tablet`
      width: 10%;  
  `}

  ${media.mobile`
      width: 14%;  
      margin-right: 2%;
  `}
`;

export const LogOutButton = styled.span`
  font-size: ${(props) => props.size}px;
  font-weight: bold;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};

  ${media.tablet`
    font-size: ${(props) => props.tabletSize}px;
  `}
`;

export const IconFigure = styled.figure`
  position: relative;
  cursor: pointer;
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;

  ${media.tablet`
  width: ${(props) => props.tabletSize}px;
  height: ${(props) => props.tabletSize}px;
  `}

  ${media.mobile`
  width: ${(props) => props.mobileSize}px;
  height: ${(props) => props.mobileSize}px;
  `}
`;
