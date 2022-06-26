// lib
import React from "react";
import Image from "next/image";

// component
import * as S from "../../../styles/detail/DetailFooterStyle";
import { useRecoilValue } from "recoil";
import { darkMode } from "../../../state/atom";

//img
import WhiteCommend from "../../../styles/img/whiteCommend.png";
import WhiteLike from "../../../styles/img/whiteLike.png";
import WhiteFind from "../../../styles/img/whiteFind.png";
import WhiteHate from "../../../styles/img/whiteHate.png";
import BlackCommend from "../../../styles/img/blackCommend.png";
import BlackLike from "../../../styles/img/blackLike.png";
import BlackFind from "../../../styles/img/blackFind.png";
import BlackHate from "../../../styles/img/blackHate.png";

export default function DetailFooter({ fakeData }) {
  const isDark = useRecoilValue(darkMode);
  const { commend, find, like, hate } = fakeData;

  return (
    <S.DetailFooter>
      <S.FooterIconBOx>
        <S.IconsBox>
          <Image
            src={isDark ? WhiteCommend : BlackCommend}
            alt="comment"
            layout="fill"
          />
        </S.IconsBox>
        <S.IconNumber>{commend}</S.IconNumber>
      </S.FooterIconBOx>
      <S.FooterIconBOx>
        <S.IconsBox>
          <Image
            src={isDark ? WhiteFind : BlackFind}
            alt="view"
            layout="fill"
          />
        </S.IconsBox>
        <S.IconNumber>{find}</S.IconNumber>
      </S.FooterIconBOx>
      <S.FooterIconBOx>
        <S.IconsBox>
          <Image
            src={isDark ? WhiteLike : BlackLike}
            alt="like"
            layout="fill"
          />
        </S.IconsBox>
        <S.IconNumber>{like}</S.IconNumber>
      </S.FooterIconBOx>
      <S.FooterIconBOx>
        <S.IconsBox>
          <Image
            src={isDark ? WhiteHate : BlackHate}
            alt="hate"
            layout="fill"
          />
        </S.IconsBox>
        <S.IconNumber>{hate}</S.IconNumber>
      </S.FooterIconBOx>
    </S.DetailFooter>
  );
}
