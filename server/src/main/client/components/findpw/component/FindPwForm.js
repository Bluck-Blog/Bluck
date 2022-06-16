// components

// lib
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useForm } from "react-hook-form";
import { validation } from "../../module/validation";

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
        <SuccessText>이메일로 로그인링크가 발송되었습니다.</SuccessText>
      ) : (
        <FormWrapper onSubmit={handleSubmit(SubmitHandleForfindPw)}>
          <IdBoxForFindPw>
            <LabelForFindPw>이름</LabelForFindPw>
            <PhoneInputForFindPw
              {...register("nameForFindPw", {
                required: "이름을 입력해주세요.",
              })}
              type="text"
              placeholder="이름을 입력해주세요."
            />
          </IdBoxForFindPw>
          <IdBoxForFindPw>
            <LabelForFindPw>아이디</LabelForFindPw>
            <PhoneInputForFindPw
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
          </IdBoxForFindPw>
          <ErrText>
            {errors?.nameForFindPw?.message || errors?.emailForFindPw?.message}
          </ErrText>
          <ForFindIdBtn type="submit">비밀번호 찾기</ForFindIdBtn>
        </FormWrapper>
      )}
    </>
  );
}

const FormWrapper = styled.form`
  width: 80%;
`;

const IdBoxForFindPw = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.theme.bgColor};
  border-radius: 10px;
  margin-top: 20px;
`;

const LabelForFindPw = styled.p`
  width: 20%;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};
`;

const PhoneInputForFindPw = styled.input`
  width: 80%;
  height: 50px;
  border: none;
  background: none;
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
    background: none;
  }
`;

const ErrText = styled.p`
  font-size: 12px;
  color: red;
  margin-top: 10px;
`;

const ForFindIdBtn = styled.button`
  width: 100%;
  height: 55px;
  margin-top: 50px;
  font-size: 16px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};
  background: none;
  border: 2px solid ${(props) => props.theme.textColor};
  border-radius: 15px;
  cursor: pointer;
`;

const SuccessText = styled.p`
  text-align: center;
  font-size: 28px;
  margin-bottom: 30px;
  color: ${(props) => props.theme.textColor};
`;
