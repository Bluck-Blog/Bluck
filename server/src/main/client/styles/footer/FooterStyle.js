import styled from "styled-components";
import { media } from "../common/media";

export const Wrapper = styled.footer`
  width: 96vw;
  height: 5vh;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
`;

export const Text = styled.p`
  font-weight: 500;
  line-height: 1.5;
  font-size: 10px;
  text-align: right;
  color: ${(props) => props.theme.textColor};
`;
