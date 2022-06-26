// lib
import Image from "next/image";

// components
import * as S from "../../../styles/detail/DetailAccountStyle";

// img
import BlackProfile from "../../../styles/img/blackProfile.png";

export default function DetailAccount({ fakeData }) {
  const {
    img: { src },
    autor,
    date,
  } = fakeData;
  return (
    <S.AccountBox>
      <S.ProfileBox>
        <S.ProfilFigure>
          <Image alt="banner" layout="fill" src={src || BlackProfile} />
        </S.ProfilFigure>
        <S.UserNameAndDate>
          <S.UserName>{autor}</S.UserName>
          <S.ContentsDate>{date}</S.ContentsDate>
        </S.UserNameAndDate>
      </S.ProfileBox>

      <S.ShareBox>
        <S.IconsBox>
          <Image
            alt="clip"
            src={require("../../../styles/img/blackLink.png")}
            layout="fill"
          />
        </S.IconsBox>
        <S.IconsBox>
          <Image
            alt="share"
            src={require("../../../styles/img/blackShare.png")}
            layout="fill"
          />
        </S.IconsBox>
      </S.ShareBox>
    </S.AccountBox>
  );
}
