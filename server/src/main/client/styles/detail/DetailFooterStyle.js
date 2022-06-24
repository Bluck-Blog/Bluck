import styled from "styled-components";
import { media } from "../common/media";

export const DetailFooter = styled.ul`
  width: 90%;
  margin: 30px auto 30px auto;
  display: flex;
  justify-content: flex-end;
  align-items: center;

  ${media.mobile`
  width: 90%;
  margin: 15px auto 10px auto;
  `}
`;

export const FooterIconBOx = styled.li`
  width: 10%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const IconNumber = styled.span`
  font-size: 15px;

  ${media.mobile`
  font-size: 12px;
  `}
`;

export const IconsBox = styled.figure`
  width: 20px;
  height: 20px;
  position: relative;
  margin-left: 13px;
  cursor: pointer;

  ${media.mobile`
  width: 15px;
  height: 15px;
  `}
`;
