// componenets
import { validation } from "../../module/validation";

// lib
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useForm } from "react-hook-form";
// import { useEffect } from "react/cjs/react.development";

// img

export default function FindIdFormBox() {
  const FINDIDERRORMESSAGE = "*일치하는 정보가 없습니다.";

  const [isFindedId, setIsFindedId] = useState(false);

  useEffect(() => {
    console.log("isFindedId===");
    console.log(isFindedId);
  }, [isFindedId]);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    setError,
  } = useForm();

  const namePhoneNumberForFindIdValidation = (data) => {
    console.log("data");
    console.log(data);

    setIsFindedId((prev) => {
      console.log("prev===");
      console.log(prev);
      return !prev;
    });
  };

  return (
    <FindIDForm onSubmit={handleSubmit(namePhoneNumberForFindIdValidation)}>
      {isFindedId && <div>asfdasdfasdf</div>}
      {isFindedId ? (
        <div>asdfasfd</div>
      ) : (
        <>
          <IdBoxForFindId>
            <LabelForFindId>이름</LabelForFindId>
            <IdInputForFindId
              {...register("nameForFindId", {
                required: true,
              })}
              type="text"
              placeholder="이름을 입력해주세요."
            />
          </IdBoxForFindId>
          <IdBoxForFindId>
            <LabelForFindId>휴대폰</LabelForFindId>
            <PhoneInputForFindId
              {...register("phoneForFindId", {
                required: true,
                pattern: {
                  value: validation.phone,
                  message: FINDIDERRORMESSAGE,
                },
              })}
              type="number"
              placeholder="휴대폰 번호를 입력해주세요."
            />
          </IdBoxForFindId>
          <ErrText>{errors?.phone?.message}</ErrText>
          <ForFindIdBtn type="submit">아이디 찾기</ForFindIdBtn>
        </>
      )}
    </FindIDForm>
  );
}

const FindIDForm = styled.form`
  width: 35%;
  margin: 250px auto 0 auto;
`;

const IdInputForFindId = styled.input`
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

const PhoneInputForFindId = styled.input`
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

const IdBoxForFindId = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.theme.bgColor};
  border-radius: 10px;
  margin-top: 20px;
`;

const LabelForFindId = styled.p`
  width: 20%;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};
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

const ErrText = styled.p`
  font-size: 12px;
  color: red;
  margin-top: 10px;
`;
