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
      <Image width={20} height={20} src={isDark ? Sun : Moon} />
      <Dark>{isDark ? "라이트모드" : "다크모드"}</Dark>
    </DarkMode>
  );
}

const Dark = styled.span`
  font-weight: 600;
  font-size: 14px;
  color: ${(props) => props.theme.navText};
`;

const DarkMode = styled.button`
  width: 8vw;
  height: 100%;
  border-radius: 20px;
  border: 2px solid ${(props) => props.theme.navText};
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: none;
`;
