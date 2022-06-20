// lib
import Image from "next/image";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";

// components
import { content } from "../../../mok/contents";
import * as S from "../../../styles/detail/DetailSectionStyle";

export default function DetailSection() {
  const router = useRouter();
  const [contentDetail, setContentDetail] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fakeData = content.find(
      (blog) => blog.id === parseInt(router?.query?.contentId)
    );

    setContentDetail(() => {
      const keyArray = Object.keys(fakeData);
      if (keyArray.length > 0) {
        setIsLoading((prev) => false);
        return { ...fakeData };
      } else {
        return {};
      }
    });
  }, []);

  console.log(contentDetail);

  return (
    <>
      {isLoading ? (
        <p>로딩중</p>
      ) : (
        <S.Wrapper>
          <S.BannerBox>
            <Image
              alt="banner"
              layout="fill"
              src={contentDetail?.banner?.src}
            />
          </S.BannerBox>
          <S.AccountBox>
            <S.ProfileBox>
              <S.ProfilFigure>
                <Image
                  alt="banner"
                  layout="fill"
                  src={contentDetail?.img?.src}
                />
              </S.ProfilFigure>
              <S.UserNameAndDate>
                <S.UserName>{contentDetail?.autor}</S.UserName>
                <S.ContentsDate>{contentDetail?.date}</S.ContentsDate>
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

          <S.TagBox>
            {contentDetail?.tag.length > 0
              ? contentDetail?.tag.map((tag, index) => (
                  <S.TagText key={index}>{tag}</S.TagText>
                ))
              : null}
          </S.TagBox>
        </S.Wrapper>
      )}
    </>
  );
}
