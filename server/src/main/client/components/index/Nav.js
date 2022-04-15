//lib
import styled from "styled-components";
import Image from "next/image";
import Link from "next/link";

//components
import DarkModeBtn from "../common/DarkModeBtn";
import Notice from "./component/Notice";

//imgs
import Person from "../../styles/img/profile.png";

export default function Nav() {
  return (
    <Navi>
      <Link href={"/"}>
        <Logo>BLUCK</Logo>
      </Link>
      <ProfilBox>
        <Image width={200} height={200} src={Person} />
      </ProfilBox>
      <Name>발빠른토깽이 님</Name>
      <Notice />
      <LogBox>
        <Login>LOGIN</Login>
        <LogOut>JOIN</LogOut>
      </LogBox>
      <DarkModeBtn />
    </Navi>
  );
}

const Login = styled.span`
  cursor: pointer;
  color: #1f295a;
`;

const LogOut = styled.span`
  cursor: pointer;
  color: #1f295a;
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
  color: #1f295a;
  font-weight: bold;
  font-size: 42px;
  margin: 20px 0 30px 0;
`;

const ProfilBox = styled.figure`
  width: 180px;
  height: 180px;
  background: #1f295a;
  border-radius: 100px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
`;

const Name = styled.span`
  color: #1f295a;
  font-weight: bold;
  font-size: 28px;
`;
