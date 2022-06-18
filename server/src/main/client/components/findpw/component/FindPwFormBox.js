// componenets
import LinkTag from "../../common/LinkTag";
import FindPwForm from "./FindPwForm";
import * as S from "../../../styles/findpw/FindPwFormBoxStyle";

// lib

// img

export default function FindPwFormBox() {
  return (
    <S.Section>
      <FindPwForm />
      <S.LinkBoxForm>
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
      </S.LinkBoxForm>
    </S.Section>
  );
}
