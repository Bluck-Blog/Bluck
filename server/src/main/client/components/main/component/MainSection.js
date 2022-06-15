//lib
import styled from "styled-components";
import { useState, useEffect } from "react";

//components
import ContentCard from "./ContentCard";
import { media } from "../../../styles/common/media";
import SelectBox from "./SelectBox";
import SearchBox from "../../common/SearchBox";

//img

export default function MainSection({ content, isToken }) {
  console.log("isToken===");
  console.log(isToken);

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
      <Menu>
        <SelectBoxWrapper>
          <SelectBox list={filterList1} />
          <SelectBox list={filterList2} />
        </SelectBoxWrapper>
        <SearchBox />
      </Menu>
      <Content>
        {contentList.length > 0 &&
          contentList.map((item) => <ContentCard key={item.id} item={item} />)}
      </Content>
    </Section>
  );
}

const Section = styled.div`
  width: 100%;
  height: 100%;
`;

const SelectBoxWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Menu = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Content = styled.div`
  width: 100%;
  margin-top: 10px;
  height: 65vh;
  padding-right: 1%;
  overflow-y: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
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

  ${media.tablet`
    height: 72vh;
  `}
`;
