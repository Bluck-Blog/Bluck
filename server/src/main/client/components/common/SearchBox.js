//lib
import Image from "next/image";
import { useRecoilValue } from "recoil";

//components
import { darkModeHandle } from "../../state/atom";
import SelectBox from "../main/component/SelectBox";
import * as S from "../../styles/common/SearchBoxStyle";

//img
import WhiteLenz from "../../styles/img/whiteLenz.png";
import BlackLenz from "../../styles/img/blackLenz.png";

export default function SearchBox() {
  const filter = ["작성자", "제목", "태그"];
  const isDark = useRecoilValue(darkModeHandle);

  return (
    <S.Wrapper>
      <S.AbsolBox>
        <SelectBox list={filter} />
      </S.AbsolBox>
      <S.Search />
      <S.LenzBox>
        <Image
          width={20}
          height={20}
          src={isDark ? WhiteLenz : BlackLenz}
          alt="lenz"
        />
      </S.LenzBox>
    </S.Wrapper>
  );
}
