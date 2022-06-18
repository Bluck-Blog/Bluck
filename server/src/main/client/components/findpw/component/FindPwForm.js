// components
import { validation } from "../../module/validation";
import * as S from "../../../styles/findpw/FindPwFormStyle";

// lib
import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";

// img

export default function FindPwForm() {
  const [isFindPw, setIsFindPw] = useState(false);
  const {
    register,
    setValue,
    formState: { errors },
    setError,
    handleSubmit,
  } = useForm();

  const SubmitHandleForfindPw = ({ nameForFindPw, emailForFindPw }) => {
    console.log("nameForFindPw, emailForFindPw");
    console.log(nameForFindPw, emailForFindPw);

    setIsFindPw((prev) => true);
  };
  return (
    <>
      {isFindPw ? (
        <S.SuccessText>이메일로 로그인링크가 발송되었습니다.</S.SuccessText>
      ) : (
        <S.FormWrapper onSubmit={handleSubmit(SubmitHandleForfindPw)}>
          <S.IdBoxForFindPw>
            <S.LabelForFindPw>이름</S.LabelForFindPw>
            <S.PhoneInputForFindPw
              {...register("nameForFindPw", {
                required: "이름을 입력해주세요.",
              })}
              type="text"
              placeholder="이름을 입력해주세요."
            />
          </S.IdBoxForFindPw>
          <S.IdBoxForFindPw>
            <S.LabelForFindPw>아이디</S.LabelForFindPw>
            <S.PhoneInputForFindPw
              {...register("emailForFindPw", {
                required: "이메일을 입력해주세요.",
                pattern: {
                  value: validation.email,
                  message: "아이디 형식이 잘못되었습니다.",
                },
              })}
              type="text"
              placeholder="아이디을 입력해주세요."
            />
          </S.IdBoxForFindPw>
          <S.ErrText>
            {errors?.nameForFindPw?.message || errors?.emailForFindPw?.message}
          </S.ErrText>
          <S.ForFindIdBtn type="submit">비밀번호 찾기</S.ForFindIdBtn>
        </S.FormWrapper>
      )}
    </>
  );
}
