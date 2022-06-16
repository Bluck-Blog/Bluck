// componenets
import { media } from "../../../styles/common/media";
import LinkTag from "../../common/LinkTag";

// lib
import styled from "styled-components";
import FindPwForm from "./FindPwForm";

// img

export default function FindPwFormBox() {
  return (
    <Section>
      <FindPwForm />
      <LinkBoxForm>
        <LinkTag
          title={"로그인"}
          tabletSize={10}
          mobileSize={9}
          size={11}
          link={"/login"}
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
      </LinkBoxForm>
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

const LinkBoxForm = styled.div`
  width: 80%;
  margin: 0 auto;
  margin-top: 30px;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;
