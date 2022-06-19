import styled from "styled-components";
import { media } from "../common/media";

export const Wapper = styled.section`
  width: 100%;
  height: 77vh;
  background: ${(props) => props.theme.bgColor};
  border-radius: 50px;
  ${(props) => {
    if (props.isContent) {
      return "display: flex; justify-content: canter; align-items: center;";
    }
  }}
`;

export const Alert = styled.p`
  text-align: center;
  width: 100%;
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 18px;
`;
