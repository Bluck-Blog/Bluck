// lib

//components
import * as S from "../../../styles/login/FormBoxStyle";

//img
import LoginForm from "./LoginForm";
import LinkTag from "../../common/LinkTag";

export default function FomBox() {
  return (
    <S.Section>
      <LoginForm />
      <S.FindBox>
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
      </S.FindBox>
    </S.Section>
  );
}
