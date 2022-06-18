// lib
import Image from "next/image";
import { useRecoilState } from "recoil";

// component
import { darkModeHandle } from "../../state/atom";
import * as S from "../../styles/common/DarkModeBtnStyle";

//img
import Moon from "../../styles/img/moon.png";
import Sun from "../../styles/img/sun.png";

export default function DarkModeBtn() {
  const [isDark, setIsDark] = useRecoilState(darkModeHandle);

  return (
    <S.DarkMode onClick={() => setIsDark(isDark)}>
      <S.DarkModeImageBox>
        <Image layout="fill" src={isDark ? Sun : Moon} />
      </S.DarkModeImageBox>
      <S.Dark>{isDark ? "라이트모드" : "다크모드"}</S.Dark>
    </S.DarkMode>
  );
}
