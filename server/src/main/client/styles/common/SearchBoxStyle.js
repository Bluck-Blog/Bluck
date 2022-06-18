import styled from "styled-components";
import { media } from "./media";

export const Wrapper = styled.form`
  position: relative;
  width: 16vw;
  height: 30px;
  border-radius: 10px;
  background: ${(props) => props.theme.bgColor};
  float: right;
  margin-bottom: 15px;

  ${media.tablet`
  width: 25vw;
  `}

  ${media.mobile`
  width: 50vw;
  height: 20px;
  margin-bottom: 0px;
  `}
`;

export const AbsolBox = styled.div`
    position: absolute;
    left: 5%; top: 50%;
    transform: translate(0, -50%);
    z-index; 1;
`;

export const Search = styled.input`
  border: none;
  width: 50%;
  height: 100%;
  margin-left: 35%;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
  }

  ${media.mobile`
  font-size: 8px;
  `}
`;

export const LenzBox = styled.div`
  position: absolute;
  right: 4%;
  top: 50%;
  width: 15px;
  height: 15px;
  transform: translate(0, -50%);
  z-index; 1;
  cursor: pointer;
  
  ${media.mobile`
  width: 10px;
  height: 10px;
  top: 35%;
  `}
`;
