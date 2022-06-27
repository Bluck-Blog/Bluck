// lib
import { useState, useEffect } from "react";
import Image from "next/image";
import { useSetRecoilState, useRecoilValue } from "recoil";
import { useForm } from "react-hook-form";
import { useRouter } from "next/router";

//components
import { validation } from "../../module/validation";
import { post, POST } from "../../../pages/api/Post";
import { darkMode, loginState } from "../../../state/atom";
import * as S from "../../../styles/login/LoginFormStyle";

//img
import Active from "../../../styles/img/activeCheck.png";
import Check from "../../../styles/img/check.png";
import BlackActive from "../../../styles/img/blackActiveCheck.png";
import BlackCheck from "../../../styles/img/blackCheck.png";

export default function LoginForm() {
  const { push } = useRouter();

  const ERRORMESSAGE = "*아이디 및 비밀번호 형식이 잘못되었습니다.";

  const isDark = useRecoilValue(darkMode);
  const [rememberId, setRememberId] = useState(false);
  const setIsLogged = useSetRecoilState(loginState);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    setError,
  } = useForm();

  const afterLoginHandle = () => {
    const { code } = data || {};

    if (isSuccess && code === 0) {
      const {
        body: { accessToken },
      } = data || {};

      sessionStorage.setItem("accessToken", accessToken);
      setIsLogged((prev) => true);
      push("/");
      return;
    }

    if (isSuccess && code === -2) {
      // 비밀번호 오류
      setError("pw", { message: "비밀번호가 잘못됐습니다." });
    }

    if (isSuccess && code === -3) {
      // 아이디 오류
      setError("id", { message: "아이디가 잘못됐습니다." });
    }
  };

  const onValid = async (data) => {
    const { id, pw } = data;

    if (rememberId) {
      localStorage.setItem("rememberID", id);
    }

    if (!rememberId) {
      localStorage.removeItem("rememberID");
    }

    const loginJson = {
      email: id,
      password: pw,
    };

    const loginAPI = await POST.useLogin(loginJson);
    console.log("loginAPI===");
    console.log(loginAPI);
  };

  useEffect(() => {
    const userIdinLocalStorage = localStorage.getItem("rememberID");

    if (userIdinLocalStorage) {
      setValue("id", userIdinLocalStorage);
      setRememberId((prev) => true);
    }
  }, []);

  return (
    <S.Form onSubmit={handleSubmit(onValid)}>
      <S.IdBox>
        <S.Label>아이디</S.Label>
        <S.IdInput
          {...register("id", {
            required: true,
            pattern: {
              value: validation.email,
              message: ERRORMESSAGE,
            },
          })}
          type="text"
          placeholder="아이디를 입력해주세요."
        />
      </S.IdBox>
      <S.IdBox>
        <S.Label>비밀번호</S.Label>
        <S.PwInput
          {...register("pw", {
            required: true,
            minLength: {
              value: 1,
              message: ERRORMESSAGE,
            },
          })}
          type="password"
          placeholder="비밀번호를 입력해주세요."
        />
      </S.IdBox>
      <S.ErrText>{errors?.id?.message}</S.ErrText>
      <S.ErrText>{errors?.pw?.message}</S.ErrText>
      <S.IdRememberBox onClick={() => setRememberId((prev) => !prev)}>
        <S.IdRememberCheckImageBox>
          <Image
            src={
              isDark
                ? rememberId
                  ? BlackActive
                  : BlackCheck
                : rememberId
                ? Active
                : Check
            }
            layout="fill"
          />
        </S.IdRememberCheckImageBox>
        <S.IdRememberText>로그인 상태 유지</S.IdRememberText>
      </S.IdRememberBox>
      <S.LoginBtn type="submit">로그인</S.LoginBtn>
    </S.Form>
  );
}
