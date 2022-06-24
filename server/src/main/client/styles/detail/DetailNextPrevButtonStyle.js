import styled from "styled-components";
import { media } from "../common/media";

export const NextPrevButtonBox = styled.div`
  border-top: 2px solid ${(props) => props.theme.navBgColor};
  padding: 50px 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

export const NextPrevButton = styled.button`
  width: 30%;
  height: 80px;
  border: none;
  border-radius: 20px;
  background-color: ${(props) => props.theme.textColor};
  display: flex;
  justify-content: ${(props) => (props.isLeft ? "flex-start" : "flex-end")};
  align-items: center;
  cursor: pointer;

  ${media.tablet`
  width: 45%;
  height: 60px;
  `}
`;

export const ArrowBox = styled.figure`
  width: 50px;
  height: 50px;
  position: relative;

  ${media.tablet`
  width: 40px;
  height: 40px;
  `}
`;

export const NextPrevTitleBox = styled.div``;

export const NextPrevTitle = styled.p`
  text-align: ${(props) => (props.isLeft ? "left" : "right")};
  color: ${(props) => props.theme.bgColor};
  font-size: ${(props) => (props.isTitle ? "14px" : "18px")};
  width: 180px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;

  ${media.tablet`
    font-size: ${(props) => (props.isTitle ? "10px" : "12px")};
    width: 90px;
  `}
`;
