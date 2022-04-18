//lib
import styled from "styled-components";
import { useState, useEffect } from "react";

//components

//img
import Poster from "../../../styles/img/profile.png";

export default function ContentCard() {
  return (
    <Wrapper>
      <Banner></Banner>
      <Body></Body>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 100%;
  height: 200px;
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
`;

const Banner = styled.div`
  width: 30%;
  height: 100%;
  border-right: 1px solid #aaa;
  background: yellow;
`;

const Body = styled.div`
  width: 70%;
  height: 100%;
  background: red;
`;
