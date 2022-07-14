// components
import { darkMode, loginState } from "../../state/atom";
import LinkTag from "../common/LinkTag";
import * as S from "../../styles/header/HeaderStyle";

// lib
import { useState } from "react";
import Image from "next/image";
import { useRecoilState, useRecoilValue } from "recoil";

// img
import WhiteLogin from "../../styles/img/whiteLogin.png";
import BlackLogin from "../../styles/img/blackLogin.png";
import WhiteLogout from "../../styles/img/whiteLogout.png";
import BlackLogout from "../../styles/img/blackLogout.png";
import BlackBell from "../../styles/img/blackBell.png";
import WhiteBell from "../../styles/img/WhiteBell.png";
import Link from "next/link";

export default function Header() {
  const [isLogged, setIsLogged] = useRecoilState(loginState);
  const isDark = useRecoilValue(darkMode);

  const [isAlarmModal, setIsAlarmModal] = useState(false);
  const [isLoginModal, setIsLoginModal] = useState(false);

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
              <S.IconFigure size={25} tabletSize={20} mobileSize={15}>
                <Image
                  style={{ cursor: "pointer" }}
                  layout="fill"
                  src={isDark ? WhiteLogout : BlackLogout}
                  alt="logout"
                />
              </S.IconFigure>
            </a>
          </Link>
        ) : (
          <Link href={"/login"}>
            <a>
              <S.IconFigure
                onMouseEnter={() => setIsLoginModal(true)}
                onMouseLeave={() => setIsLoginModal(false)}
                size={25}
                tabletSize={20}
                mobileSize={15}
              >
                <Image
                  style={{ cursor: "pointer" }}
                  layout="fill"
                  src={isDark ? WhiteLogin : BlackLogin}
                  alt="login"
                />
                {isLoginModal && <S.Modal isShow={isLoginModal}>여기</S.Modal>}
              </S.IconFigure>
            </a>
          </Link>
        )}
        <S.IconFigure
          onMouseEnter={() => setIsAlarmModal(true)}
          onMouseLeave={() => setIsAlarmModal(false)}
          size={25}
          tabletSize={20}
          mobileSize={15}
        >
          <Image
            style={{ cursor: "pointer" }}
            layout="fill"
            src={isDark ? WhiteBell : BlackBell}
            alt="bell"
          />
          {isAlarmModal && <S.Modal isShow={isAlarmModal}>여기</S.Modal>}
        </S.IconFigure>
      </S.BtnBox>
    </S.HeaderWrapper>
  );
}
