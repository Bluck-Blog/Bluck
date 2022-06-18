// componenets
import { validation } from "../../module/validation";
import * as S from "../../../styles/findId/FindIdFormBoxStyle";
import LinkTag from "../../common/LinkTag";
import FindIDForm from "./FIndIDForm";

// lib

// img

export default function FindIdFormBox() {
  return (
    <S.Section>
      <FindIDForm />
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
      </S.LinkBoxForm>
    </S.Section>
  );
}
