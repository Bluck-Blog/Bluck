// components
import { validation } from "../../module/validation";
import * as S from "../../../styles/findId/FIndIDFormStyle";

// lib
import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";

export default function FindIDForm() {
  const FINDIDERRORMESSAGE = "*일치하는 정보가 없습니다.";

  const [isFindedId, setIsFindedId] = useState(false);
  const [userIdFromDB, setUserIdFromDB] = useState("");

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

    setIsFindedId((prev) => !prev);
    setUserIdFromDB((prev) => "dlsdk0601@gmail.com");
  };

  return (
    <S.FormWrapper onSubmit={handleSubmit(namePhoneNumberForFindIdValidation)}>
      {isFindedId ? (
        <S.NameText>
          회원님의 아이디는 <br /> {userIdFromDB}입니다.
        </S.NameText>
      ) : (
        <>
          <S.IdBoxForFindId>
            <S.LabelForFindId>이름</S.LabelForFindId>
            <S.IdInputForFindId
              {...register("nameForFindId", {
                required: true,
              })}
              type="text"
              placeholder="이름을 입력해주세요."
            />
          </S.IdBoxForFindId>
          <S.IdBoxForFindId>
            <S.LabelForFindId>휴대폰</S.LabelForFindId>
            <S.PhoneInputForFindId
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
          </S.IdBoxForFindId>
          <S.ErrText>{errors?.phone?.message}</S.ErrText>
          <S.ForFindIdBtn type="submit">아이디 찾기</S.ForFindIdBtn>
        </>
      )}
    </S.FormWrapper>
  );
}
