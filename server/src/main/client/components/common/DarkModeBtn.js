import styled from "styled-components";
import Image from "next/image";
import { useRecoilState } from "recoil";
import { darkMode, darkModeHandle } from "../../state/atom";

//img
import Moon from "../../styles/img/moon.png";
import Sun from "../../styles/img/sun.png";

export default function DarkModeBtn() {
  const [isDark, setIsDark] = useRecoilState(darkModeHandle);

  return (
    <DarkMode onClick={() => setIsDark(isDark)}>
      <Image width={25} height={25} src={isDark ? Sun : Moon} />
      <Dark>다크모드</Dark>
    </DarkMode>
  );
}

const Dark = styled.span`
  font-weight: bold;
  font-size: 20px;
  color: ${(props) => props.theme.navText};
`;

const DarkMode = styled.button`
  width: 9vw;
  height: 6vh;
  margin-top: 10px;
  border-radius: 5vw;
  border: 4px solid ${(props) => props.theme.navText};
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: none;
`;
