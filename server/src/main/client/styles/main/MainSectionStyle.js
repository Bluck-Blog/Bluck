import styled from "styled-components";
import { media } from "../common/media";

export const Section = styled.div`
  width: 100%;
  height: 100%;
`;

export const SelectBoxWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Menu = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Content = styled.div`
  width: 100%;
  margin-top: 10px;
  height: 67vh;
  padding-right: 1%;
  overflow-y: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
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
  &::-webkit-scrollbar-track {
    /* background-color: grey; */
  }

  ${media.tablet`
    height: 72vh;
  `}
`;
