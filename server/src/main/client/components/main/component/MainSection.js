//lib
import { useState, useEffect } from "react";

//components
import ContentCard from "./ContentCard";
import SelectBox from "./SelectBox";
import SearchBox from "../../common/SearchBox";
import * as S from "../../../styles/main/MainSectionStyle";

//img

export default function MainSection({ content }) {
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
    setContentList((prev) => [...content]);
  }, []);

  return (
    <S.Section>
      <S.Menu>
        <S.SelectBoxWrapper>
          <SelectBox list={filterList1} />
          <SelectBox list={filterList2} />
        </S.SelectBoxWrapper>
        <SearchBox />
      </S.Menu>
      <S.Content>
        {contentList.length > 0 &&
          contentList.map((item) => <ContentCard key={item.id} item={item} />)}
      </S.Content>
    </S.Section>
  );
}
