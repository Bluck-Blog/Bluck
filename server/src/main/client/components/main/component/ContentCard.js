//lib
import styled from "styled-components";
import Image from "next/image";
import { useState, useEffect } from "react";
import { useRecoilValue } from "recoil";

//components
import { darkMode } from "../../../state/atom";

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

  return (
    <Wrapper>
      <Banner>
        <Image
          width="100%"
          height="120%"
          layout="responsive"
          src={Dog}
          alt="photo"
        />
      </Banner>
      <Body>
        <ProfileBox>
          <Image
            height={18}
            width={18}
            src={isDark ? item.img : BlackProfile}
            alt="profile"
          />
          <Name>{item.autor}</Name>
        </ProfileBox>
        <Title>제목을 적어주세용</Title>
        <MainContent>{item.body}</MainContent>
        <ContentFooter>
          <Date>{item.date}</Date>
          <RightIcon>
            <IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteCommend : BlackCommend}
                alt="photo"
              />
              <Count>{item.commend}</Count>
            </IconBox>
            <IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteFind : BlackFind}
                alt="photo"
              />
              <Count>{item.find}</Count>
            </IconBox>
            <IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteLike : BlackLike}
                alt="photo"
              />
              <Count>{item.like}</Count>
            </IconBox>
            <IconBox>
              <Image
                width={"14px"}
                height={"14px"}
                src={isDark ? WhiteHate : BlackHate}
                alt="photo"
              />
              <Count>{item.hate}</Count>
            </IconBox>
          </RightIcon>
        </ContentFooter>
      </Body>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 49%;
  height: 185px;
  margin-bottom: 20px;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
  transition: 0.3s;
  &:hover {
    box-shadow: 2px 3px 3px 0px rgba(0, 0, 0, 0.1);
  }
`;

const Banner = styled.div`
  width: 30%;
  height: 100%;
  border-right: 1px solid #aaa;
`;

const Body = styled.div`
  width: 70%;
  height: 100%;
  padding: 0 4% 0 2%;
  background: ${(props) => props.theme.ctBgColor};
`;

const ProfileBox = styled.div`
  margin-top: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Name = styled.span`
  margin-left: 1%;
  font-size: 12px;
`;

const Title = styled.p`
  font-weight: bold;
  margin: 25px 0;
`;

const MainContent = styled.p`
  margin: 20px 0;
  font-size: 14px;
`;

const ContentFooter = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 7%;
`;

const RightIcon = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const IconBox = styled.figure`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 5px;
`;

const Count = styled.figcaption`
  margin-left: 10px;
  font-size: 14px;
`;

const Date = styled.span`
  font-size: 14px;
`;
