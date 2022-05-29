// components
import Title from "../common/Title";

// lib
import styled from "styled-components";
import Link from "next/link";
import LinkTag from "../common/LinkTag";

// img

export default function Header() {
  console.log("here");
  return (
    <HeaderWrapper>
      <LinkTag title={"BLUCK"} size={42} link={"/"} />
      <BtnBox>
        <LinkTag title={"LOG-IN"} size={14} link={"/login"} />
        <LinkTag title={"JOIN"} size={14} link={"/signup"} />
      </BtnBox>
    </HeaderWrapper>
  );
}

const HeaderWrapper = styled.header`
  width: 100%;
  height: 10%;
  padding-top: 1%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2%;
`;

const BtnBox = styled.div`
  width: 8%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
