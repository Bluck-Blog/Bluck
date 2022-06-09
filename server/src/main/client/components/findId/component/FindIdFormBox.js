// componenets
import { validation } from "../../module/validation";
import { media } from "../../../styles/common/media";

// lib
import styled from "styled-components";
import LinkTag from "../../common/LinkTag";
import FindIDForm from "./FIndIDForm";

// img

export default function FindIdFormBox() {
  return (
    <Section>
      <FindIDForm />
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
          title={"비밀번호 찾기"}
          tabletSize={10}
          mobileSize={9}
          size={11}
          link={"/findpw"}
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
