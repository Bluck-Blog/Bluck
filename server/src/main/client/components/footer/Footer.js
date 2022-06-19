// components
import DarkModetn from "../common/DarkModeBtn";
import * as S from "../../styles/footer/FooterStyle";

// lib

// img

export default function Footer() {
  return (
    <S.Wrapper>
      <DarkModetn />
      <div>
        <S.Text>Made by 추동욱 / 이지현 / 정인아</S.Text>
        <S.Text>Copyright © 2022 bluck. All rights reserved.</S.Text>
      </div>
    </S.Wrapper>
  );
}
