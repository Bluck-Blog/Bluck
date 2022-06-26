import styled from "styled-components";
import { media } from "../common/media";

export const TagBox = styled.div`
  width: 95%;
  margin: 30px auto;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;

  ${media.mobile`
  margin: 15px auto;
  `}
`;

export const TagText = styled.p`
  font-size: 14px;
  height: 28px;
  border-radius: 10px;
  padding: 0 5px;
  margin-right: 10px;
  background-color: ${(props) => props.theme.textColor};
  opacity: 0.8;
  color: ${(props) => props.theme.bgColor};
  display: flex;
  justify-content: center;
  align-items: center;

  ${media.mobile`
  font-size: 12px;
  padding: 0 2px;
  height: 24px;
  border-radius: 6px;
  `}
`;
