//lib
import Image from "next/image";
import { useRecoilValue } from "recoil";
import { useRouter } from "next/router";

//components
import { darkMode } from "../../../state/atom";
import * as S from "../../../styles/main/ContentCardStyle";

//img
import Dog from "../../../styles/img/dog.png";
import BlackProfile from "../../../styles/img/blackProfile.png";
import WhiteCommend from "../../../styles/img/whiteCommend.png";
import WhiteLike from "../../../styles/img/whiteLike.png";
import WhiteFind from "../../../styles/img/whiteFind.png";
import WhiteHate from "../../../styles/img/whiteHate.png";
import BlackCommend from "../../../styles/img/blackCommend.png";
import BlackLike from "../../../styles/img/blackLike.png";
import BlackFind from "../../../styles/img/blackFind.png";
import BlackHate from "../../../styles/img/blackHate.png";

export default function ContentCard({ item }) {
  const isDark = useRecoilValue(darkMode);
  const router = useRouter();

  return (
    <S.Wrapper
      onClick={() => router.push(`/detail/${item.id}`)}
      isDark={isDark}
    >
      <S.Banner>
        <Image layout="fill" src={Dog} alt="photo" />
      </S.Banner>
      <S.Body>
        <S.ProfileBox>
          <Image
            height={"18px"}
            width={"18px"}
            src={isDark ? item.img : BlackProfile}
            alt="profile"
          />
          <S.Name>{item.autor}</S.Name>
        </S.ProfileBox>
        <S.Title>제목을 적어주세용</S.Title>
        <S.MainContent>{item.body}</S.MainContent>
        <S.ContentFooter>
          <S.Date>{item.date}</S.Date>
          <S.RightIcon>
            <S.IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteCommend : BlackCommend}
                alt="photo"
              />
              <S.Count>{item.commend}</S.Count>
            </S.IconBox>
            <S.IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteFind : BlackFind}
                alt="photo"
              />
              <S.Count>{item.find}</S.Count>
            </S.IconBox>
            <S.IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteLike : BlackLike}
                alt="photo"
              />
              <S.Count>{item.like}</S.Count>
            </S.IconBox>
            <S.IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteHate : BlackHate}
                alt="photo"
              />
              <S.Count>{item.hate}</S.Count>
            </S.IconBox>
          </S.RightIcon>
        </S.ContentFooter>
      </S.Body>
    </S.Wrapper>
  );
}
