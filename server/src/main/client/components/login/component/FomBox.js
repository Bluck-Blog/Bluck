// lib
import styled from "styled-components";
import Link from "next/link";

//components

//img
import LoginForm from "./LoginForm";
import LinkTag from "../../common/LinkTag";
import { media } from "../../../styles/common/media";

export default function FomBox() {
  return (
    <Section>
      <LoginForm />
      <FindBox>
        <LinkTag
          title={"비밀번호 찾기"}
          tabletSize={10}
          mobileSize={9}
          size={11}
          link={"/findpw"}
        />
        ·
        <LinkTag
          title={"아이디 찾기"}
          tabletSize={10}
          mobileSize={9}
          size={11}
          link={"/findId"}
        />
        ·
        <LinkTag
          title={"회원가입"}
          tabletSize={10}
          mobileSize={9}
          size={11}
          link={"/signup"}
        />
      </FindBox>
    </Section>
  );
}

const Section = styled.section`
  width: 40%;
  height: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  ${media.tablet`
    width: 75%;
  `}
`;

const FindBox = styled.div`
  width: 80%;
  margin: 0 auto;
  margin-top: 30px;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;
