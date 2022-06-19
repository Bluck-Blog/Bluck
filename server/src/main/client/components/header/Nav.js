//lib
import Image from "next/image";
import Link from "next/link";
import { useRecoilValue } from "recoil";

//components
import DarkModeBtn from "../common/DarkModeBtn";
import Notice from "./component/Notice";
import { darkMode, isLogged } from "../../state/atom";
import * as S from "../../styles/header/NavStyle";

//imgs
import WhitePerson from "../../styles/img/whiteProfile.png";
import BlackPerson from "../../styles/img/blackProfile.png";

export default function Nav() {
  const isDark = useRecoilValue(darkMode);
  const isLog = useRecoilValue(isLogged);

  return (
    <S.Navi>
      <Link href={"/"}>
        <S.Logo>BLUCK</S.Logo>
      </Link>
      <S.ProfilBox>
        <Image
          width={160}
          height={160}
          src={isDark ? BlackPerson : WhitePerson}
        />
      </S.ProfilBox>
      <S.Name>{isLog ? "발빠른토깽이 님" : "로그인을 해주세요"}</S.Name>
      <Notice />
      <S.LogBox>
        <Link href={"/login"}>
          <Login>LOGIN</Login>
        </Link>
        <Link href={"/signup"}>
          <S.LogOut>JOIN</S.LogOut>
        </Link>
      </S.LogBox>
      <DarkModeBtn />
    </S.Navi>
  );
}
