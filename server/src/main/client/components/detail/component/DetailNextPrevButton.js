// lib
import React from "react";
import Image from "next/image";

// img
import BlackRightArrow from "../../../styles/img/blackRightArrow.png";
import BlackLeftArrow from "../../../styles/img/blackLeftArrow.png";
import WhiteRightArrow from "../../../styles/img/WhiteRightArrow.png";
import WhiteLeftArrow from "../../../styles/img/whiteLeftArrow.png";

// components
import { darkMode } from "../../../state/atom";
import * as S from "../../../styles/detail/DetailNextPrevButtonStyle";
import { useRecoilValue } from "recoil";

export default function DetailNextPrevButton() {
  const isDark = useRecoilValue(darkMode);

  return (
    <S.NextPrevButtonBox>
      <S.NextPrevButton isLeft={true}>
        <S.ArrowBox>
          <Image
            alt=""
            src={isDark ? BlackLeftArrow : WhiteLeftArrow}
            layout="fill"
          />
        </S.ArrowBox>
        <S.NextPrevTitleBox>
          <S.NextPrevTitle isTitle={true} isLeft={true}>
            이전 포스트
          </S.NextPrevTitle>
          <S.NextPrevTitle isTitle={false} isLeft={true}>
            제목이 보이ddddddd는 부분
          </S.NextPrevTitle>
        </S.NextPrevTitleBox>
      </S.NextPrevButton>
      <S.NextPrevButton isLeft={false}>
        <S.NextPrevTitleBox>
          <S.NextPrevTitle isTitle={true} isLeft={false}>
            다음 포스트
          </S.NextPrevTitle>
          <S.NextPrevTitle isTitle={false} isLeft={false}>
            제목이 보이는ddddddd 부분
          </S.NextPrevTitle>
        </S.NextPrevTitleBox>
        <S.ArrowBox>
          <Image
            alt=""
            src={isDark ? BlackRightArrow : WhiteRightArrow}
            layout="fill"
          />
        </S.ArrowBox>
      </S.NextPrevButton>
    </S.NextPrevButtonBox>
  );
}
