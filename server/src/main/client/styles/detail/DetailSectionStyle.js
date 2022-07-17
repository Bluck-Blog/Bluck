import styled from "styled-components";
import { media } from "../common/media";

export const ScrollWrapper = styled.div`
  width: 60%;
  height: 75vh;
  margin: 0 auto;
  padding-right: 20px;
  overflow: auto;
  &::-webkit-scrollbar {
    width: 5px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #aaa;
    background-clip: padding-box;
    border: 1px solid transparet;
    border-radius: 5px;
    /* border-top-left-radius: 5px;
    border-bottom-right-radius: 5px; */
  }

  ${media.mobile`
    width: 95%;
    padding: 0;
  `}
`;

export const Wrapper = styled.div`
  border-radius: 40px;
  overflow: hidden;
  /* background-color: ${(props) => props.theme.bgColor}; */
`;
