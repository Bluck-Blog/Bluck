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
  font-weight: 600;
  font-size: 12px;
  text-align: right;
  color: ${(props) => props.theme.textColor};

  ${media.mobile`
  font-size: 10px;
  `}
`;
