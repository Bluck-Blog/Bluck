// lib
import Image from "next/image";
import { useRecoilValue } from "recoil";
import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";

//components
import { validation } from "../../module/validation";
import { POST } from "../../../pages/api/Post";
import { darkMode } from "../../../state/atom";
import * as S from "../../../styles/signup/JoinFormStyle";

//img
import Active from "../../../styles/img/activeCheck.png";
import Check from "../../../styles/img/check.png";
import BlackActive from "../../../styles/img/blackActiveCheck.png";
import BlackCheck from "../../../styles/img/blackCheck.png";

export default function JoinForm() {
  const isDark = useRecoilValue(darkMode);

  const [check, setCheck] = useState(false);
  const [profile, setProfile] = useState("");

  const {
    register,
    handleSubmit,
    setError,
    getValues,
    formState: { errors },
  } = useForm();

  const joinSubmit = (data) => {
    console.log("data===");
    console.log(data);
    console.log(errors);
  };

  const uploadProfile = (e) => {
    const {
      target: { files },
    } = e;

    const photo = files[0];
    const reader = new FileReader();
    reader.onloadend = (finishEvent) => {
      const {
        currentTarget: { result },
      } = finishEvent;
      setProfile((prev) => result);
    };
    reader.readAsDataURL(photo);
  };

  const sentEmail = async (e) => {
    e.preventDefault();
    const email = getValues("email");
    const formData = new FormData();
    formData.append("email", email);

    const confirmEmailAPI = await POST.confirmEmail(formData);
    console.log("confirmEmailAPI===");
    console.log(confirmEmailAPI);

    // if (isEmailVerifySuccess) {
    //   setError("confirmState", { message: "인증번호가 잘못되었습니다." });
    // }
  };

  return (
    <S.Section>
      <S.PhotoBtn photo={profile && profile} for="profilePhoto">
        {profile ? "" : "+"}
      </S.PhotoBtn>
      <S.FileInput
        onChange={uploadProfile}
        accept="image/*"
        type="file"
        id="profilePhoto"
      />
      <S.Form onSubmit={handleSubmit(joinSubmit)}>
        <S.InputBox>
          <S.Label>이메일</S.Label>
          <S.Input
            {...register("email", {
              required: "*이메일을 입력해주세요.*",
              pattern: {
                value: validation.email,
                message: "*이메일 형식이 잘못되었습니다.*",
              },
            })}
            type="text"
            placeholder="이메일을 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.email?.message}</S.ErrorMsg>
        <S.ConfirmBox>
          <S.ConfirmBtn onClick={sentEmail}>인증번호</S.ConfirmBtn>
          <S.ConFirmInput type="text" placeholder="인증번호 입력해주세요." />
        </S.ConfirmBox>
        <S.ErrorMsg>{errors?.email?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>별명</S.Label>
          <S.Input
            type="text"
            {...register("nickname", {
              required: "*닉네임을 입력주세요.*",
            })}
            placeholder="별명을 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.nickname?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>비밀번호</S.Label>
          <S.Input
            {...register("password", {
              required: "*비밀번호를 입력해주세요.*",
              pattern: {
                value: validation.password,
                message: "*비밀번호 형식이 잘못되었습니다.*",
              },
            })}
            type="text"
            placeholder="비밀번호를 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.password?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>재확인</S.Label>
          <S.Input
            {...register("passwordcheck", {
              required: "*비밀번호 확인을 입력해주세요.*",
              // pattern: {
              //   value: "",
              //   message: "*비밀번호와 다릅니다.*",
              // },
            })}
            type="text"
            placeholder="비밀번호를 다시 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.passwordcheck?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>이름</S.Label>
          <S.Input
            {...register("name", {
              required: "*이름을 작성해주세요.*",
            })}
            type="text"
            placeholder="이름을 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.name?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>생년월일</S.Label>
          <S.Input
            {...register("birthday", {
              required: "*생년월일 입력해주세요.*",
              pattern: {
                value: validation.birthday,
                message: "*생년월일 형식이 잘못돼었습니다.*",
              },
            })}
            type="text"
            placeholder="생년월일 6자리 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.birthday?.message}</S.ErrorMsg>
        <S.InputBox>
          <S.Label>휴대전화</S.Label>
          <S.Input
            {...register("phone", {
              required: "*휴대전화 번호를 입력해주세요.*",
              pattern: {
                value: validation.phone,
                message: "*휴대전화 번호 형식이 잘못돼었습니다.*",
              },
            })}
            type="text"
            placeholder="-없이 입력해주세요."
          />
        </S.InputBox>
        <S.ErrorMsg>{errors?.phone?.message}</S.ErrorMsg>
        <S.StateMsg>
          <S.Label>상태메시지</S.Label>
          <S.Input
            {...register("profileMessage", {
              required: false,
            })}
            type="text"
            placeholder="상태메시지 입력해주세요."
          />
        </S.StateMsg>
        <S.IntroBox>
          <S.IntroLabel>자기소개</S.IntroLabel>
          <S.IntroInput
            {...register("introduction", {
              required: false,
            })}
            type="text"
            placeholder="상태메시지 입력해주세요."
          />
        </S.IntroBox>
        <S.IdRememberBox onClick={() => setCheck((prev) => !prev)}>
          <S.IdRememberImageBox>
            <Image
              src={
                isDark
                  ? check
                    ? BlackActive
                    : BlackCheck
                  : check
                  ? Active
                  : Check
              }
              layout="fill"
            />
          </S.IdRememberImageBox>
          <S.IdRememberText>개인정보 수집 이용에 대한 동의</S.IdRememberText>
        </S.IdRememberBox>
        <S.BtnBox>
          <S.Btn>취소</S.Btn>
          <S.Btn type="submit">회원가입</S.Btn>
        </S.BtnBox>
      </S.Form>
    </S.Section>
  );
}
