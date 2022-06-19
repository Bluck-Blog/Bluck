import styled from "styled-components";
import { media } from "../common/media";

export const NoticeWrapper = styled.div`
  margin-top: 40px;
  padding: 15px 10px;
  background-color: ${(props) =>
    props.isLog ? props.theme.noticeColor : null};
  width: 12vw;
  height: 30vh;
  border-radius: 20px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
`;

export const List = styled.ul`
  list-style-type: disc;
  margin-top: 10px;
  padding-left: 5px;
`;

export const ImgBox = styled.figure`
  width: 50px;
  height: 50px;
  background: ${(props) => props.theme.noticeColor};
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
`;
