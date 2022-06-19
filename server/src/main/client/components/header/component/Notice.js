//lib
import Image from "next/image";
import { useState, useEffect } from "react";
import { useRecoilValue } from "recoil";

//component
import NoticeItem from "./NoticeItem";
import { darkMode, isLogged } from "../../../state/atom";
import * as S from "../../../styles/header/NoticeStyle";

//img
import WhiteBell from "../../../styles/img/whiteBell.png";
import BlackBell from "../../../styles/img/blackBell.png";

export default function Notice() {
  const [noticeList, setNoticeList] = useState([]);
  const isDark = useRecoilValue(darkMode);
  const isLog = useRecoilValue(isLogged);

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
    <S.NoticeWrapper isLog={isLog}>
      <S.ImgBox>
        <Image width={30} height={30} src={isDark ? WhiteBell : BlackBell} />
      </S.ImgBox>
      {isLog && (
        <S.List>
          {noticeList.map((item) => (
            <NoticeItem key={item.id} item={item} />
          ))}
        </S.List>
      )}
    </S.NoticeWrapper>
  );
}
