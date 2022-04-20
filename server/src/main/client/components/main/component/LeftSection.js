//lib
import styled from "styled-components";
import { useState, useEffect } from "react";
import SelectBox from "./SelectBox";

//components

//img
import ContentCard from "./ContentCard";

export default function LeftSection({ content }) {
  const filterList1 = [
    "인기순",
    "최신순",
    "댓글 수",
    "조회수",
    "좋아요",
    "싫어요",
  ];

  const filterList2 = ["오늘", "이번주", "이번달", "올해"];

  const [contentList, setContentList] = useState([]);

  useEffect(() => {
    setContentList([...content]);
  }, []);

  return (
    <Section>
      <Title>HOME</Title>
      <Menu>
        <SelectBox list={filterList1} />
        <SelectBox list={filterList2} />
      </Menu>
      <Content>
        {contentList.length > 0 &&
          contentList.map((item) => <ContentCard key={item.id} item={item} />)}
      </Content>
    </Section>
  );
}

const Section = styled.div`
  width: 50%;
  height: 100%;
  padding: 3%;
`;

const Title = styled.p`
  font-size: 48px;
  font-weight: bold;
  margin: 20px 0 80px 0;
`;

const Menu = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Content = styled.div`
  width: 100%;
  margin-top: 10px;
  /* height: 600px; */
  height: 60vh;
  overflow-y: auto;
  &::-webkit-scrollbar {
    width: 5px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #aaa;
    background-clip: padding-box;
    border: 1px solid transparet;
    border-top-left-radius: 5px;
    border-bottom-right-radius: 5px;
  }
  &::-webkit-scrollbar-track {
    /* background-color: grey; */
  }
`;
