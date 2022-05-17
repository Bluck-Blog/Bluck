// lib
import { useState } from "react";
import styled from "styled-components";
import Image from "next/image";
import { useRecoilValue } from "recoil";

//components
import { darkMode } from "../../../state/atom";

//img
import Active from "../../../styles/img/activeCheck.png";
import Check from "../../../styles/img/check.png";
import BlackActive from "../../../styles/img/blackActiveCheck.png";
import BlackCheck from "../../../styles/img/blackCheck.png";
import { useForm } from "react-hook-form";

export default function LoginForm() {
  const isDark = useRecoilValue(darkMode);

  const [rememberId, setRememberId] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    setError,
  } = useForm();

  const onValid = (data) => {
    console.log("data");
    console.log(data);

    setError("pw");
  };

  return (
    <Form onSubmit={handleSubmit(onValid)}>
      <IdBox>
        <Label>아이디</Label>
        <IdInput
          {...register("id", {
            required: "*아이디 및 비밀번호가 잘못되었습니다.",
          })}
          type="text"
          placeholder="아이디를 입력해주세요."
        />
      </IdBox>
      <IdBox>
        <Label>비밀번호</Label>
        <PwInput
          {...register("pw", {
            required: "*아이디 및 비밀번호가 잘못되었습니다.",
          })}
          type="password"
          placeholder="비밀번호를 입력해주세요."
        />
      </IdBox>
      <ErrText>{errors?.pw?.message}</ErrText>
      <IdRememberBox onClick={() => setRememberId((prev) => !prev)}>
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
          width={20}
          height={20}
        />
        <IdRememberText>로그인 상태 유지</IdRememberText>
      </IdRememberBox>
      <LoginBtn type="submit">로그인</LoginBtn>
    </Form>
  );
}

const Form = styled.form`
  width: 80%;
`;

const IdInput = styled.input`
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

const PwInput = styled.input`
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

const IdBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.theme.bgColor};
  border-radius: 10px;
  margin-top: 20px;
`;

const Label = styled.p`
  width: 20%;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};
`;

const IdRememberBox = styled.div`
  margin-top: 15px;
  width: 40%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  cursor: pointer;
`;

const IdRememberText = styled.span`
  margin-left: 3%;
`;

const LoginBtn = styled.button`
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
