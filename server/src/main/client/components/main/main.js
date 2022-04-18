// lib
import styled from "styled-components";
import Image from "next/image";
import { useState, useEffect } from "react";
// components

//img
import Arrow from "../../styles/img/aroow.png";
import SelectBox from "./component/SelectBox";
import LeftSection from "./component/LeftSection";

export default function Main({ content }) {
  return (
    <Wrapper>
      <LeftSection content={content} />
      <RightSection></RightSection>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  width: 84vw;
  height: 88vh;
  background: white;
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
`;

const RightSection = styled.div`
  width: 50%;
  height: 100%;
  /* background: yellow; */
`;
