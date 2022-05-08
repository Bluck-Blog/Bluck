//lib
import styled from "styled-components";
import Image from "next/image";
import Link from "next/link";
import { useRecoilValue } from "recoil";
//components
import DarkModeBtn from "../common/DarkModeBtn";
import Notice from "./component/Notice";
import { darkMode, isLogged } from "../../state/atom";

//imgs
import WhitePerson from "../../styles/img/whiteProfile.png";
import BlackPerson from "../../styles/img/blackProfile.png";

export default function Nav() {
  const isDark = useRecoilValue(darkMode);
  const isLog = useRecoilValue(isLogged);

  return (
    <Navi>
      <Link href={"/"}>
        <Logo>BLUCK</Logo>
      </Link>
      <ProfilBox>
        <Image
          width={160}
          height={160}
          src={isDark ? BlackPerson : WhitePerson}
        />
      </ProfilBox>
      <Name>{isLog ? "발빠른토깽이 님" : "로그인을 해주세요"}</Name>
      <Notice />
      <LogBox>
        <Link href={"/login"}>
          <Login>LOGIN</Login>
        </Link>
        <Link href={"/signup"}>
          <LogOut>JOIN</LogOut>
        </Link>
      </LogBox>
      <DarkModeBtn />
    </Navi>
  );
}

const Login = styled.span`
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;

const LogOut = styled.span`
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;

const LogBox = styled.div`
  width: 12vw;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-weight: bold;
  margin-top: 40px;
`;

const Navi = styled.nav`
  width: 15vw;
  height: 100vh;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
`;

const Logo = styled.a`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 42px;
  margin: 20px 0 30px 0;
`;

const ProfilBox = styled.figure`
  width: 180px;
  height: 180px;
  background: ${(props) => props.theme.textColor};
  border-radius: 100px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
`;

const Name = styled.span`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 24px;
`;
