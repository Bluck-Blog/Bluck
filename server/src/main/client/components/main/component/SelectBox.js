//lib
import { useState, useEffect } from "react";
import styled from "styled-components";
import Image from "next/image";

//components

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
    <Filter1
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
    </Filter1>
  );
}

const Filter1 = styled.div`
  font-weight: bold;
  margin-right: 20px;
  font-size: 12px;
  width: 60px;
  cursor: pointer;
  position: relative;
  ul {
    display: ${(props) => (props.isShow ? "block" : "none")};
    position: absolute;
    left: 0;
    top: 150%;
    background: ${(props) => props.theme.textColor};
    opacity: 0.8;
    width: 100%;
    border-radius: 10px;
    li {
      color: white;
      text-align: center;
      margin: 10px 0;
      font-size: 10px;
    }
  }
`;
