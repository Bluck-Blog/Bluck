// lib
import styled from "styled-components";
import Image from "next/image";
import { useRecoilValue } from "recoil";
import { useState } from "react";

//components
import { darkMode } from "../../../state/atom";

//img
import Active from "../../../styles/img/activeCheck.png";
import Check from "../../../styles/img/check.png";
import BlackActive from "../../../styles/img/blackActiveCheck.png";
import BlackCheck from "../../../styles/img/blackCheck.png";

export default function JoinForm() {
  const isDark = useRecoilValue(darkMode);

  const [check, setCheck] = useState(false);
  return (
    <Section>
      <PhotoBtn type="file" />
      <Form>
        <InputBox>
          <Label>아이디</Label>
          <Input type="text" placeholder="아이디를 입력" />
        </InputBox>
        <InputBox>
          <Label>별명</Label>
          <Input type="text" placeholder="별명를 입력" />
        </InputBox>
        <InputBox>
          <Label>비밀번호</Label>
          <Input type="text" placeholder="비밀번호를 입력" />
        </InputBox>
        <InputBox>
          <Label>재확인</Label>
          <Input type="text" placeholder="비밀번호를 다시 입력" />
        </InputBox>
        <InputBox>
          <Label>이름</Label>
          <Input type="text" placeholder="이름를 입력" />
        </InputBox>
        <InputBox>
          <Label>생년월일</Label>
          <Input type="text" placeholder="생년월일 6자리 입력" />
        </InputBox>
        <InputBox>
          <Label>휴대전화</Label>
          <Input type="text" placeholder="-없이 입력" />
        </InputBox>
        <ConfirmBox>
          <ConfirmBtn>인증번호</ConfirmBtn>
          <ConFirmInput type="text" placeholder="인증번호 입력" />
        </ConfirmBox>
        <StateMsg>
          <Label>상태메시지</Label>
          <Input type="text" placeholder="상태메시지 입력" />
        </StateMsg>
        <IntroBox>
          <IntroLabel>자기소개</IntroLabel>
          <IntroInput type="text" placeholder="상태메시지 입력" />
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
          <Btn>회원가입</Btn>
        </BtnBox>
      </Form>
    </Section>
  );
}

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
  width: 100%;
  margin-top: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Section = styled.section`
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
`;

const Form = styled.form`
  width: 50%;
  margin-left: 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  align-items: center;
`;

const PhotoBtn = styled.input`
  width: 120px;
  height: 120px;
  background: ${(props) => props.theme.bgColor};
  border: none;
  border-radius: 10px;
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
  width: 10%;
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
  width: 49%;
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
  width: 49%;
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
  width: 85%;
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
