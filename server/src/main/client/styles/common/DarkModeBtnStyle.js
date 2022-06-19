import styled from "styled-components";
import { media } from "./media";

export const Dark = styled.span`
  font-weight: 600;
  font-size: 14px;
  color: ${(props) => props.theme.navText};

  ${media.tablet`
  font-size: 12px;
  `}
`;

export const DarkModeImageBox = styled.figure`
  position: relative;
  width: 20px;
  height: 20px;

  ${media.mobile`
  width: 15px;
  height: 15px;
  `}
`;

export const DarkMode = styled.button`
  /* width: 8vw; */
  width: 130px;
  height: 100%;
  border-radius: 20px;
  border: 2px solid ${(props) => props.theme.navText};
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: none;

  ${media.tablet`
  width: 130px;
  height: 80%;
  `}

  ${media.mobile`
    height: 90%;
  `}
`;
