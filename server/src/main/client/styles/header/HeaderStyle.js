import styled from "styled-components";
import { media } from "../common/media";

export const HeaderWrapper = styled.header`
  width: 100%;
  height: 10%;
  padding-top: 1%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2%;
`;

export const BtnBox = styled.div`
  width: 10%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  ${media.tablet`
      width: 15%;  
  `}

  ${media.mobile`
      width: 25%;  
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
