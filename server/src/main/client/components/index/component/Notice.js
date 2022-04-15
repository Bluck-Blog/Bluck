//lib
import Image from "next/image";
import styled from "styled-components";
import { useState, useEffect } from "react";

//component
import NoticeItem from "./NoticeItem";

//img
import Bell from "../../../styles/img/bell.png";

export default function Notice() {
  const [noticeList, setNoticeList] = useState([]);

  useEffect(() => {
    const list = [
      { id: 1, title: "누구누구님이 좋아요를 눌렀습니다." },
      { id: 2, title: "누구누구님이 댓글을 달았습니다." },
      { id: 3, title: "누구누구님이 좋아요를 눌렀습니다." },
      { id: 4, title: "누구누구님이 댓글을 달았습니다." },
      { id: 5, title: "누구누구님이 좋아요를 눌렀습니다." },
    ];
    setNoticeList([...list]);
  }, []);

  return (
    <NoticeWrapper>
      <Image width={30} height={30} src={Bell} />
      <List>
        {noticeList.map((item) => (
          <NoticeItem key={item.id} item={item} />
        ))}
      </List>
    </NoticeWrapper>
  );
}

const NoticeWrapper = styled.div`
  margin-top: 40px;
  padding: 15px 10px;
  background-color: ${(props) => props.theme.ctBgColor};
  width: 12vw;
  height: 30vh;
  border-radius: 20px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
`;

const List = styled.ul`
  list-style-type: disc;
  margin-top: 10px;
  padding-left: 5px;
`;