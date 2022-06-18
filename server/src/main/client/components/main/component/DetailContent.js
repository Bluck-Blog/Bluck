//lib
import { useRouter } from "next/router";

//components
import * as S from "../../../styles/main/DetailContentStyle";

//img

export default function DetailContent() {
  const { route } = useRouter();

  return (
    <S.Wapper isContent={route === "/"}>
      {route === "/" && <S.Alert>내용을 클릭해주세요.</S.Alert>}
    </S.Wapper>
  );
}
