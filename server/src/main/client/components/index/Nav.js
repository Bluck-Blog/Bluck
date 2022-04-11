import styled from "styled-components";
import Image from "next/image";
import { useEffect, useState } from "react/cjs/react.development";

//imgs
import Person from "../../styles/img/profile.png";
import Bell from "../../styles/img/bell.png";
import Moon from "../../styles/img/moon.png";
import { useRecoilState } from "recoil";
import { darkMode } from "../../state/atom";

export default function Nav() {
  const [isDark, setIsDark] = useRecoilState(darkMode);
  const [noticeList, setNoticeList] = useState([]);
  useEffect(() => {
    const list = [
      { id: 1, title: "누구누구님이 좋아요를 눌렀습니다." },
      { id: 2, title: "누구누구님이 댓글을 달았습니다." },
      { id: 3, title: "누구누구님이 좋아요를 눌렀습니다." },
      { id: 4, title: "누구누구님이 댓글을 달았습니다." },
      { id: 5, title: "누구누구님이 좋아요를 눌렀습니다." },
    ];
    setNoticeList([...list]);
  }, []);

  const changeDarkMode = () => {
    setIsDark((prev) => !prev);
  };

  return (
    <Navi>
      <Logo>BLOCK</Logo>
      <ProfilBox>
        <Image width={200} height={200} src={Person} />
      </ProfilBox>
      <Name>발빠른토깽이 님</Name>
      <Notice>
        <Image width={30} height={30} src={Bell} />
        <List>
          {noticeList.map((item) => {
            return <Item key={item.id}>{item.title}</Item>;
          })}
        </List>
      </Notice>
      <LogBox>
        <Login>LOGIN</Login>
        <LogOut>JOIN</LogOut>
      </LogBox>
      <DarkMode onClick={changeDarkMode}>
        <Image width={25} height={25} src={Moon} />
        <Dark>다크모드</Dark>
      </DarkMode>
    </Navi>
  );
}

const Dark = styled.span`
  font-weight: bold;
  font-size: 20px;
`;

const DarkMode = styled.button`
  width: 9vw;
  height: 6vh;
  margin-top: 20px;
  border-radius: 5vw;
  border: 4px solid ${(props) => props.theme.textColor};
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: none;
`;

const Login = styled.span`
  cursor: pointer;
`;

const LogOut = styled.span`
  cursor: pointer;
`;

const LogBox = styled.div`
  width: 12vw;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-weight: bold;
  margin-top: 50px;
`;

const Navi = styled.nav`
  width: 15vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

const Logo = styled.figure`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 42px;
  margin-bottom: 50px;
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
  margin-bottom: 50px;
`;

const Name = styled.span`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 28px;
`;

const Notice = styled.div`
  margin-top: 50px;
  padding-top: 15px;
  background-color: rgba(255, 255, 255, 0.8);
  width: 12vw;
  height: 30vh;
  border-radius: 20px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
`;

const List = styled.ul`
  list-style-type: disc;
  margin-top: 10px;
`;

const Item = styled.li`
  width: 100%;
  font-size: 11px;
  margin: 12px 0;
  cursor: pointer;
`;
