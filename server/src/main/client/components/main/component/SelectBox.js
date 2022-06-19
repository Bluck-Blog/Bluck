//lib
import { useState, useEffect } from "react";
import Image from "next/image";

//components
import * as S from "../../../styles/main/SelectBoxStyle";

//img
import Arrow from "../../../styles/img/aroow.png";

export default function SelectBox({ list }) {
  const [isShowFilter, setIsShowFilter] = useState(false);
  const [filterList, setFilterList] = useState([]);
  const [filter, setFilter] = useState("");

  useEffect(() => {
    const arr = [...list];
    setFilterList((prev) => [...arr]);
    setFilter((prev) => arr[0]);
  }, []);

  const filter1Hanlde = (e, item) => {
    e.stopPropagation();
    setFilter((prev) => item);
    setIsShowFilter((prev) => !prev);
  };

  return (
    <S.Filter1
      isShow={isShowFilter}
      onClick={() => setIsShowFilter((prev) => !prev)}
    >
      {filter} <Image width="12px" src={Arrow} alt="arrow" />
      <ul>
        {filterList.length > 0 &&
          filterList.map((item, index) => (
            <li key={index} onClick={(e) => filter1Hanlde(e, item)}>
              {item}
            </li>
          ))}
      </ul>
    </S.Filter1>
  );
}
