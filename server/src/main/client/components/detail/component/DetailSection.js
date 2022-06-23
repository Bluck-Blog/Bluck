// lib
import Image from "next/image";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";

// components
import { darkMode } from "../../../state/atom";
import { content } from "../../../mok/contents";
import * as S from "../../../styles/detail/DetailSectionStyle";

// img
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
import BlackRightArrow from "../../../styles/img/blackRightArrow.png";
import BlackLeftArrow from "../../../styles/img/blackLeftArrow.png";
import WhiteRightArrow from "../../../styles/img/WhiteRightArrow.png";
import WhiteLeftArrow from "../../../styles/img/whiteLeftArrow.png";

export default function DetailSection() {
  const router = useRouter();
  const isDark = useRecoilValue(darkMode);
  const [contentDetail, setContentDetail] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fakeData = content.find(
      (blog) => blog.id === parseInt(router?.query?.contentId)
    );

    setContentDetail(() => {
      if (typeof fakeData === null) {
        return;
      }
      const keyArray = Object.keys(fakeData);
      if (keyArray.length > 0) {
        setIsLoading((prev) => false);
        return { ...fakeData };
      } else {
        return {};
      }
    });
  }, []);

  return (
    <>
      {isLoading ? (
        <p>로딩중</p>
      ) : (
        <S.ScrollWrapper>
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
                    src={contentDetail?.img?.src || BlackProfile}
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

            <S.BodyWrapper>
              Aliquip fugiat eu consectetur esse irure adipisicing. Anim
              adipisicing nostrud ullamco magna. Incididunt reprehenderit velit
              do culpa. Elit anim consequat fugiat sint aliquip aliquip pariatur
              sunt labore magna cillum. Deserunt mollit culpa excepteur elit
              Lorem culpa eiusmod amet eiusmod in qui. Esse est magna aliqua ut
              dolore in proident. Veniam amet laborum aliquip proident velit
              eiusmod ex fugiat deserunt reprehenderit. Lorem consequat minim
              ipsum aliquip ea veniam in nisi proident. Adipisicing nostrud eu
              irure laborum consectetur excepteur et est enim adipisicing
              consectetur veniam labore eiusmod. Laborum velit labore minim eu
              exercitation deserunt ex. Aliquip reprehenderit qui esse velit. Ea
              non non labore adipisicing dolor exercitation culpa do id non. Ut
              ex officia esse sint incididunt cupidatat aliquip anim excepteur
              Lorem ullamco. Esse minim minim voluptate non culpa quis duis
              dolor laborum ea nisi sit. Aliquip amet voluptate aliquip officia
              eiusmod enim. Sunt sit anim culpa nostrud enim veniam nulla
              aliquip pariatur reprehenderit et Lorem enim. Ullamco ea cupidatat
              exercitation consectetur dolore irure pariatur ad tempor. Aute in
              ipsum duis voluptate. Lorem consequat elit officia ea veniam
              incididunt amet eiusmod deserunt mollit. Velit ad consequat
              exercitation irure sunt et aute et ullamco aliqua laboris commodo
              nisi. Aliquip exercitation consequat cillum aliquip mollit labore.
              Duis exercitation dolor dolore consequat excepteur ex officia.
              Sunt veniam velit cupidatat Lorem officia irure eu laborum duis
              aliquip. Ea cupidatat velit id eiusmod reprehenderit voluptate.
              Irure labore in exercitation magna amet proident do. Minim duis
              velit in commodo veniam est elit dolore Lorem est nisi laborum
              non. Sit nisi eiusmod duis excepteur adipisicing est occaecat.
              Enim occaecat deserunt irure consectetur ex cupidatat eiusmod est
              irure. Ad culpa pariatur aute tempor proident cillum quis sunt
              enim consectetur nulla labore qui id. Enim non cupidatat laboris
              irure irure duis. Proident in Lorem ad cillum sint nulla
              voluptate. Ad proident eu duis proident laboris incididunt
              proident cillum est non proident aliquip proident. Eiusmod magna
              magna nostrud anim sunt magna. Lorem ea cillum voluptate ipsum ut
              et nostrud occaecat nisi nostrud pariatur Lorem. Anim ullamco ex
              dolor non irure ut officia duis et veniam. Cupidatat deserunt
              irure mollit enim non proident. Sit excepteur nulla sunt minim ea
              adipisicing nulla id. Reprehenderit adipisicing reprehenderit
              irure mollit cupidatat laborum aute culpa tempor. Commodo do
              aliquip velit Lorem mollit nulla amet aliquip culpa sint nulla
              laboris culpa cillum. Nulla sit aliqua nostrud id est culpa enim
              fugiat non aute nulla velit commodo irure. Duis nostrud deserunt
              excepteur deserunt eiusmod aute. Cillum veniam ullamco deserunt
              voluptate sit in. In reprehenderit et consequat sunt veniam ut
              sunt. Ea proident sint eu quis ipsum enim aliquip anim ut laboris
              in cillum officia. Aliqua exercitation ad veniam irure commodo et
              incididunt laboris ipsum consectetur pariatur. Culpa dolore sunt
              mollit reprehenderit voluptate enim aliqua aliqua pariatur officia
              eu qui sint commodo. Proident ut labore irure adipisicing sit id
              consectetur enim enim do. Consequat labore tempor fugiat aliqua
              nulla magna officia laboris elit nulla amet voluptate cillum. Ad
              exercitation aliquip fugiat mollit eu velit in ipsum. Adipisicing
              dolore commodo non ullamco cillum qui ut incididunt non Lorem
              velit adipisicing exercitation. Incididunt magna magna laborum
              culpa ullamco tempor commodo ad irure exercitation occaecat enim
              incididunt dolor. Veniam laborum do ut laboris deserunt eiusmod ea
              aliquip. Cillum ut adipisicing pariatur eu pariatur non officia
              excepteur ea amet dolor. deserunt voluptate sit in. In
              reprehenderit et consequat sunt veniam ut sunt. Ea proident sint
              eu quis ipsum enim aliquip anim ut laboris in cillum officia.
              Aliqua exercitation ad veniam irure commodo et incididunt laboris
              ipsum consectetur pariatur. Culpa dolore sunt mollit reprehenderit
              voluptate enim aliqua aliqua pariatur officia eu qui sint commodo.
              Proident ut labore irure adipisicing sit id consectetur enim enim
              do. Consequat labore tempor fugiat aliqua nulla magna officia
              laboris elit nulla amet voluptate cillum. Ad exercitation aliquip
              fugiat mollit eu velit in ipsum. Adipisicing dolore commodo non
              ullamco cillum qui ut incididunt non Lorem velit adipisicing
              exercitation. Incididunt magna magna laborum culpa ullamco tempor
              commodo ad irure exercitation occaecat enim incididunt dolor.
              Veniam laborum do ut laboris deserunt eiusmod ea aliquip. Cillum
              ut adipisicing pariatur eu pariatur non officia excepteur ea amet
              dolor. deserunt voluptate sit in. In reprehenderit et consequat
              sunt veniam ut sunt. Ea proident sint eu quis ipsum enim aliquip
              anim ut laboris in cillum officia. Aliqua exercitation ad veniam
              irure commodo et incididunt laboris ipsum consectetur pariatur.
              Culpa dolore sunt mollit reprehenderit voluptate enim aliqua
              aliqua pariatur officia eu qui sint commodo. Proident ut labore
              irure adipisicing sit id consectetur enim enim do. Consequat
              labore tempor fugiat aliqua nulla magna officia laboris elit nulla
              amet voluptate cillum. Ad exercitation aliquip fugiat mollit eu
              velit in ipsum. Adipisicing dolore commodo non ullamco cillum qui
              ut incididunt non Lorem velit adipisicing exercitation. Incididunt
              magna magna laborum culpa ullamco tempor commodo ad irure
              exercitation occaecat enim incididunt dolor. Veniam laborum do ut
              laboris deserunt eiusmod ea aliquip. Cillum ut adipisicing
              pariatur eu pariatur non officia excepteur ea amet dolor.
            </S.BodyWrapper>

            <S.DetailFooter>
              <S.FooterIconBOx>
                <S.IconsBox>
                  <Image
                    src={isDark ? WhiteCommend : BlackCommend}
                    alt="comment"
                    layout="fill"
                  />
                </S.IconsBox>
                <S.IconNumber>{contentDetail.commend}</S.IconNumber>
              </S.FooterIconBOx>
              <S.FooterIconBOx>
                <S.IconsBox>
                  <Image
                    src={isDark ? WhiteFind : BlackFind}
                    alt="view"
                    layout="fill"
                  />
                </S.IconsBox>
                <S.IconNumber>{contentDetail.find}</S.IconNumber>
              </S.FooterIconBOx>
              <S.FooterIconBOx>
                <S.IconsBox>
                  <Image
                    src={isDark ? WhiteLike : BlackLike}
                    alt="like"
                    layout="fill"
                  />
                </S.IconsBox>
                <S.IconNumber>{contentDetail.like}</S.IconNumber>
              </S.FooterIconBOx>
              <S.FooterIconBOx>
                <S.IconsBox>
                  <Image
                    src={isDark ? WhiteHate : BlackHate}
                    alt="hate"
                    layout="fill"
                  />
                </S.IconsBox>
                <S.IconNumber>{contentDetail.hate}</S.IconNumber>
              </S.FooterIconBOx>
            </S.DetailFooter>

            <S.NextPrevButtonBox>
              <S.NextPrevButton isLeft={true}>
                <S.ArrowBox>
                  <Image
                    alt=""
                    src={isDark ? BlackLeftArrow : WhiteLeftArrow}
                    layout="fill"
                  />
                </S.ArrowBox>
                <S.NextPrevTitleBox>
                  <S.NextPrevTitle isTitle={true} isLeft={true}>
                    이전 포스트
                  </S.NextPrevTitle>
                  <S.NextPrevTitle isTitle={false} isLeft={true}>
                    제목이 보이ddddddd는 부분
                  </S.NextPrevTitle>
                </S.NextPrevTitleBox>
              </S.NextPrevButton>
              <S.NextPrevButton isLeft={false}>
                <S.NextPrevTitleBox>
                  <S.NextPrevTitle isTitle={true} isLeft={false}>
                    다음 포스트
                  </S.NextPrevTitle>
                  <S.NextPrevTitle isTitle={false} isLeft={false}>
                    제목이 보이는ddddddd 부분
                  </S.NextPrevTitle>
                </S.NextPrevTitleBox>
                <S.ArrowBox>
                  <Image
                    alt=""
                    src={isDark ? BlackRightArrow : WhiteRightArrow}
                    layout="fill"
                  />
                </S.ArrowBox>
              </S.NextPrevButton>
            </S.NextPrevButtonBox>
          </S.Wrapper>
        </S.ScrollWrapper>
      )}
    </>
  );
}
