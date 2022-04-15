import styled from "styled-components";
import Image from "next/image";
import { useRecoilState } from "recoil";
import { darkMode } from "../../state/atom";

//img
import Moon from "../../styles/img/moon.png";

export default function DarkModeBtn() {
  const [isDark, setIsDark] = useRecoilState(darkMode);

  const changeDarkMode = () => {
    setIsDark((prev) => !prev);
  };

  return (
    <DarkMode onClick={changeDarkMode}>
      <Image width={25} height={25} src={Moon} />
      <Dark>다크모드</Dark>
    </DarkMode>
  );
}

const Dark = styled.span`
  font-weight: bold;
  font-size: 20px;
`;

const DarkMode = styled.button`
  width: 9vw;
  height: 6vh;
  margin-top: 10px;
  border-radius: 5vw;
  border: 4px solid #1f295a;
  display: flex;
  justify-content: space-around;
  align-items: center;
  background: none;
`;
