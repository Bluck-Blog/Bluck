//lib
import styled from "styled-components";
import SelectBox from "../main/component/SelectBox";
import Image from "next/image";
import { useRecoilValue } from "recoil";
import { darkModeHandle } from "../../state/atom";

//components

//img
import WhiteLenz from "../../styles/img/whiteLenz.png";
import BlackLenz from "../../styles/img/blackLenz.png";
import { media } from "../../styles/common/media";

export default function SearchBox() {
  const filter = ["작성자", "제목", "태그"];
  const isDark = useRecoilValue(darkModeHandle);

  return (
    <Wrapper>
      <AbsolBox>
        <SelectBox list={filter} />
      </AbsolBox>
      <Search />
      <LenzBox>
        <Image
          width={20}
          height={20}
          src={isDark ? WhiteLenz : BlackLenz}
          alt="lenz"
        />
      </LenzBox>
    </Wrapper>
  );
}

const Wrapper = styled.form`
  position: relative;
  width: 16vw;
  height: 30px;
  border-radius: 10px;
  background: ${(props) => props.theme.bgColor};
  float: right;
  margin-bottom: 15px;

  ${media.desktop`
      width: 25vw;
    `}
`;

const AbsolBox = styled.div`
    position: absolute;
    left: 5%; top: 50%;
    transform: translate(0, -50%);
    z-index; 1;
`;

const Search = styled.input`
  border: none;
  width: 50%;
  height: 100%;
  margin-left: 35%;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
  }
`;

const LenzBox = styled.div`
  position: absolute;
  right: 4%;
  top: 50%;
  width: 15px;
  height: 15px;
  transform: translate(0, -50%);
  z-index; 1;
  cursor: pointer;
`;
