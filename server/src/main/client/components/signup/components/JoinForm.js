// lib
import styled from "styled-components";
import Image from "next/image";
import { useRecoilValue } from "recoil";
import { useState } from "react";
import { validation } from "../../module/validation";
import { useForm } from "react-hook-form";

//components
import { POST } from "../../../pages/api/Post";
import { darkMode } from "../../../state/atom";

//img
import Active from "../../../styles/img/activeCheck.png";
import Check from "../../../styles/img/check.png";
import BlackActive from "../../../styles/img/blackActiveCheck.png";
import BlackCheck from "../../../styles/img/blackCheck.png";

export default function JoinForm() {
  const isDark = useRecoilValue(darkMode);

  const [check, setCheck] = useState(false);
  const [profile, setProfile] = useState("");

  const { data, isSuccess, mutate } =
    POST.useConfirmEmail("api/session/verify");

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
    mutate(formData, POST.mutateCallBack("confirmEmaill"));
  };

  return (
    <Section>
      <PhotoBtn photo={profile && profile} for="profilePhoto">
        {profile ? "" : "+"}
      </PhotoBtn>
      <FileInput
        onChange={uploadProfile}
        accept="image/*"
        type="file"
        id="profilePhoto"
      />
      <Form onSubmit={handleSubmit(joinSubmit)}>
        <InputBox>
          <Label>이메일</Label>
          <Input
            {...register("email", {
              required: "*이메일 형식이 잘못되었습니다.*",
              pattern: {
                value: validation.email,
                message: "*이메일 형식이 잘못되었습니다.*",
              },
            })}
            type="text"
            placeholder="이메일을 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.email?.message}</ErrorMsg>
        <ConfirmBox>
          <ConfirmBtn onClick={sentEmail}>인증번호</ConfirmBtn>
          <ConFirmInput type="text" placeholder="인증번호 입력해주세요." />
        </ConfirmBox>
        <InputBox>
          <Label>별명</Label>
          <Input
            type="text"
            {...register("nickname", {
              required: "*닉네임을 적어주세요.*",
            })}
            placeholder="별명을 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.nickname?.message}</ErrorMsg>
        <InputBox>
          <Label>비밀번호</Label>
          <Input
            {...register("password", {
              required: "*비밀번호 형식이 잘못되었습니다.*",
              pattern: {
                value: validation.password,
                message: "*비밀번호 형식이 잘못되었습니다.*",
              },
            })}
            type="text"
            placeholder="비밀번호를 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.password?.message}</ErrorMsg>
        <InputBox>
          <Label>재확인</Label>
          <Input
            {...register("passwordcheck", {
              required: "*비밀번호와 다릅니다.*",
              // pattern: {
              //   value: "",
              //   message: ""
              // }
            })}
            type="text"
            placeholder="비밀번호를 다시 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.passwordcheck?.message}</ErrorMsg>
        <InputBox>
          <Label>이름</Label>
          <Input
            {...register("name", {
              required: "*이름을 작성해주세요.*",
            })}
            type="text"
            placeholder="이름을 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.name?.message}</ErrorMsg>
        <InputBox>
          <Label>생년월일</Label>
          <Input
            {...register("birthday", {
              required: "*생년월일 형식이 잘못돼었습니다.*",
              pattern: {
                value: validation.birthday,
                message: "*생년월일 형식이 잘못돼었습니다.*",
              },
            })}
            type="text"
            placeholder="생년월일 6자리 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.birthday?.message}</ErrorMsg>
        <InputBox>
          <Label>휴대전화</Label>
          <Input
            {...register("phone", {
              required: "*휴대전화 번호 형식이 잘못돼었습니다.*",
              pattern: {
                value: validation.phone,
                message: "*휴대전화 번호 형식이 잘못돼었습니다.*",
              },
            })}
            type="text"
            placeholder="-없이 입력해주세요."
          />
        </InputBox>
        <ErrorMsg>{errors?.phone?.message}</ErrorMsg>
        <StateMsg>
          <Label>상태메시지</Label>
          <Input
            {...register("profileMessage", {
              required: false,
            })}
            type="text"
            placeholder="상태메시지 입력해주세요."
          />
        </StateMsg>
        <IntroBox>
          <IntroLabel>자기소개</IntroLabel>
          <IntroInput
            {...register("introduction", {
              required: false,
            })}
            type="text"
            placeholder="상태메시지 입력해주세요."
          />
        </IntroBox>
        <IdRememberBox onClick={() => setCheck((prev) => !prev)}>
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
            width={20}
            height={20}
          />
          <IdRememberText>개인정보 수집 이용에 대한 동의</IdRememberText>
        </IdRememberBox>
        <BtnBox>
          <Btn>취소</Btn>
          <Btn type="submit">회원가입</Btn>
        </BtnBox>
      </Form>
    </Section>
  );
}

const ErrorMsg = styled.span`
  color: red;
  font-size: 10px;
  padding-left: 5px;
  margin-bottom: 10px;
`;

const Btn = styled.button`
  width: 47%;
  height: 40px;
  background: none;
  border: none;
  border-radius: 10px;
  color: ${(props) => props.theme.textColor};
  border: 2px solid ${(props) => props.theme.textColor};
`;

const BtnBox = styled.div`
  width: 90%;
  margin: 40px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Section = styled.section`
  max-width: 768px;
  height: 80%;
  /* padding: 0 2%; */
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  overflow: hidden auto;

  &::-webkit-scrollbar {
    width: 5px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #aaa;
    background-clip: padding-box;
    border: 1px solid transparet;
    border-top-left-radius: 5px;
    border-bottom-right-radius: 5px;
  }
  &::-webkit-scrollbar-track {
    /* background-color: grey; */
  }
`;

const Form = styled.form`
  width: 70%;
  margin: 0 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  align-items: center;
`;

const PhotoBtn = styled.label`
  cursor: pointer;
  width: 190px;
  height: 190px;
  border: none;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: ${(props) => (props.photo ? null : props.theme.bgColor)};
  background-image: url(${(props) => props.photo});
  background-size: ${(props) => (props.photo ? "cover" : null)};
  background-position: ${(props) => (props.photo ? "center" : null)};
  background-repeat: no-repeat;
`;

const Label = styled.p`
  width: 20%;
  height: 40px;
  line-height: 2.8;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
`;

const IntroLabel = styled.p`
  width: 15%;
  height: 40px;
  line-height: 2.8;
  /* padding-left: 2%; */
  font-size: 14px;
  font-weight: bold;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
`;

const Input = styled.input`
  width: 80%;
  height: 40px;
  border: none;
  background: none;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
    background: ${(props) => props.theme.bgColor};
  }
`;

const InputBox = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

const StateMsg = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

const ConfirmBox = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

const ConfirmBtn = styled.button`
  cursor: pointer;
  border: none;
  background: none;
  font-size: 10px;
  font-weight: bold;
  margin-right: 2%;
  width: 20%;
  height: 40px;
  border: 2px solid ${(props) => props.theme.textColor};
  border-radius: 10px;
`;

const ConFirmInput = styled.input`
  width: 80%;
  height: 40px;
  border: none;
  background: none;
  border-radius: 10px;
  padding-left: 5%;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
    background: ${(props) => props.theme.bgColor};
  }
`;

const IntroInput = styled.textarea`
  width: 80%;
  height: 90%;
  border: none;
  border-radius: 10px;
  padding: 2%;
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
  }
`;

const IntroBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
  height: 200px;
  background: ${(props) => props.theme.bgColor};
`;

const IdRememberBox = styled.div`
  margin-top: 15px;
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  cursor: pointer;
`;

const IdRememberText = styled.span`
  font-size: 14px;
  margin-left: 1%;
`;

const FileInput = styled.input`
  display: none;
`;
