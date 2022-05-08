// lib
import styled from "styled-components";
import Link from "next/link";

//components

//img
import LoginForm from "./LoginForm";

export default function FomBox() {
  return (
    <Section>
      <LoginForm />
      <FindBox>
        <Link href={"/findPw"}>
          <HrefText>비밀번호 찾기</HrefText>
        </Link>
        ·
        <Link href={"/findId"}>
          <HrefText>아이디 찾기</HrefText>
        </Link>
        ·
        <Link href={"/signup"}>
          <HrefText>회원가입</HrefText>
        </Link>
      </FindBox>
    </Section>
  );
}

const HrefText = styled.span`
  display: block;
  font-size: 11px;
  width: 20%;
  text-align: center;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;

const Section = styled.section`
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const FindBox = styled.div`
  width: 80%;
  margin: 0 auto;
  margin-top: 30px;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;
