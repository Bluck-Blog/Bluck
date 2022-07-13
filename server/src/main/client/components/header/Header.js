// components
import { loginState } from "../../state/atom";
import LinkTag from "../common/LinkTag";
import * as S from "../../styles/header/HeaderStyle";

// lib
import Image from "next/image";
import { useRecoilState } from "recoil";

// img
import WhiteLogin from "../../styles/img/whiteLogin.png";
import BlackLogin from "../../styles/img/blackLogin.png";
import WhiteLogout from "../../styles/img/whiteLogout.png";
import BlackLogout from "../../styles/img/blackLogout.png";
import BlackJoin from "../../styles/img/blackJoin.png";
import WhiteJoin from "../../styles/img/whiteJoin.png";
import WhiteMyPage from "../../styles/img/whiteMyPage.png";
import BlackMyPage from "../../styles/img/blackMyPage.png";
import Link from "next/link";

export default function Header() {
  const [isLogged, setIsLogged] = useRecoilState(loginState);
  console.log(isLogged);

  const logOutHandle = () => {
    sessionStorage.removeItem("accessToken");
    setIsLogged((prev) => false);
  };

  return (
    <S.HeaderWrapper>
      <LinkTag
        title={"BLUCK"}
        tabletSize={26}
        mobileSize={18}
        size={30}
        link={"/"}
      />
      <S.BtnBox>
        {isLogged ? (
          <Link href={"/logout"}>
            <a>
              <S.IconFigure size={25} tabletSize={12} mobileSize={10}>
                <Image
                  layout="fill"
                  src={isLogged ? WhiteLogout : BlackLogout}
                  alt="logout"
                />
              </S.IconFigure>
            </a>
          </Link>
        ) : (
          <Link href={"/login"}>
            <a>
              <S.IconFigure size={25} tabletSize={20} mobileSize={15}>
                <Image
                  layout="fill"
                  src={isLogged ? WhiteLogin : BlackLogin}
                  alt="login"
                />
              </S.IconFigure>
            </a>
          </Link>
        )}
        {isLogged ? (
          <Link href={"/mypage"}>
            <a>
              <S.IconFigure size={25} tabletSize={12} mobileSize={10}>
                <Image
                  layout="fill"
                  src={isLogged ? WhiteMyPage : BlackMyPage}
                  alt="mypage"
                />
              </S.IconFigure>
            </a>
          </Link>
        ) : (
          <Link href={"/signup"}>
            <a>
              <S.IconFigure size={25} tabletSize={20} mobileSize={15}>
                <Image
                  layout="fill"
                  src={isLogged ? WhiteJoin : BlackJoin}
                  alt="join"
                />
              </S.IconFigure>
            </a>
          </Link>
        )}
      </S.BtnBox>
    </S.HeaderWrapper>
  );
}
