//lib
import styled from "styled-components";
import Image from "next/image";
import { useState, useEffect } from "react";

//components

//img
import Dog from "../../../styles/img/dog.png";
import Commend from "../../../styles/img/commend.png";
import Like from "../../../styles/img/like.png";
import Find from "../../../styles/img/find.png";
import Hate from "../../../styles/img/hate.png";

export default function ContentCard({ item }) {
  return (
    <Wrapper>
      <Banner>
        <Image src={Dog} alt="photo" />
      </Banner>
      <Body>
        <ProfileBox>
          <Photo height={"18px"} width={"18px"} src={item.img} alt="profile" />
          <Name>{item.autor}</Name>
        </ProfileBox>
        <Title>제목을 적어주세용</Title>
        <MainContent>{item.body}</MainContent>
        <ContentFooter>
          <Date>{item.date}</Date>
          <RightIcon>
            <IconBox>
              <Image src={Commend} alt="photo" />
              <Count>{item.commend}</Count>
            </IconBox>
            <IconBox>
              <Image src={Find} alt="photo" />
              <Count>{item.find}</Count>
            </IconBox>
            <IconBox>
              <Image src={Like} alt="photo" />
              <Count>{item.like}</Count>
            </IconBox>
            <IconBox>
              <Image src={Hate} alt="photo" />
              <Count>{item.hate}</Count>
            </IconBox>
          </RightIcon>
        </ContentFooter>
      </Body>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 99%;
  height: 200px;
  margin-bottom: 20px;
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  border: 1px solid black;
  cursor: pointer;
`;

const Banner = styled.div`
  width: 30%;
  height: 100%;
  border-right: 1px solid #aaa;
`;

const Photo = styled(Image)`
  width: 100%;
  height: 100%;
`;

const Body = styled.div`
  width: 70%;
  height: 100%;
  padding: 0 4% 0 2%;
`;

const ProfileBox = styled.div`
  margin-top: 10px;
`;

const Name = styled.span`
  margin-left: 3%;
  font-size: 16px;
`;

const Title = styled.p`
  font-weight: bold;
  margin: 25px 0;
`;

const MainContent = styled.p`
  margin: 20px 0;
`;

const ContentFooter = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10%;
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
