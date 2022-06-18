// components
import { loginState } from "../../state/atom";
import LinkTag from "../common/LinkTag";
import * as S from "../../styles/header/HeaderStyle";

// lib
import { useRecoilState } from "recoil";

// img

export default function Header() {
  const [isLogged, setIsLogged] = useRecoilState(loginState);

  const logOutHandle = () => {
    sessionStorage.removeItem("accessToken");
    setIsLogged((prev) => false);
  };

  return (
    <S.HeaderWrapper>
      <LinkTag
        title={"BLUCK"}
        tabletSize={36}
        mobileSize={24}
        size={42}
        link={"/"}
      />
      <S.BtnBox>
        {isLogged ? (
          <S.LogOutButton
            onClick={logOutHandle}
            tabletSize={12}
            mobileSize={10}
            size={14}
          >
            LOGOUT
          </S.LogOutButton>
        ) : (
          <LinkTag
            title={"LOG-IN"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/login"}
          />
        )}
        {isLogged ? (
          <LinkTag
            title={"MYPAGE"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/mypage"}
          />
        ) : (
          <LinkTag
            title={"JOIN"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/signup"}
          />
        )}
      </S.BtnBox>
    </S.HeaderWrapper>
  );
}
