// lib
import { useState } from "react";

// components
import * as S from "../../../styles/detail/DetailSectionStyle";

// img

import DetailBanner from "./DetailBanner";
import DetailAccount from "./DetailAccount";
import DetailTagBox from "./DetailTagBox";
import DetailBody from "./DetailBody";
import DetailFooter from "./DetailFooter";
import DetailNextPrevButton from "./DetailNextPrevButton";

export default function DetailSection({ fakeData }) {
  const [contentDetail, setContentDetail] = useState({ ...fakeData });

  return (
    <S.ScrollWrapper>
      <S.Wrapper>
        {/* 배너이미지  */}
        <DetailBanner fakeData={contentDetail} />

        {/* 글쓴이 정보 */}
        <DetailAccount fakeData={contentDetail} />

        {/* 태그 정보 */}
        <DetailTagBox fakeData={contentDetail} />

        {/* 본문 */}
        <DetailBody fakeData={contentDetail} />

        {/* 코멘트 */}
        <DetailFooter fakeData={contentDetail} />

        {/* 버튼 박스 */}
        <DetailNextPrevButton />
      </S.Wrapper>
    </S.ScrollWrapper>
  );
}
