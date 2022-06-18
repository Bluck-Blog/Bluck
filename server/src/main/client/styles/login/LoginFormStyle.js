import Image from "next/image";
import styled from "styled-components";
import { media } from "../common/media";

export const Form = styled.form`
  width: 80%;
`;

export const IdInput = styled.input`
  width: 80%;
  height: 50px;
  border: none;
  background: none;
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
    background: none;
  }

  ${media.mobile`
    height: 40px; 
  `}
`;

export const PwInput = styled.input`
  width: 80%;
  height: 50px;
  border: none;
  background: none;
  color: ${(props) => props.theme.textColor};
  &:focus {
    outline: none;
    background: none;
  }

  ${media.mobile`
    height: 40px; 
  `}
`;

export const IdBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.theme.bgColor};
  border-radius: 10px;
  margin-top: 20px;
`;

export const Label = styled.p`
  width: 20%;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};

  ${media.mobile`
  padding-left: 3%;
  font-size: 10px;
  `}
`;

export const IdRememberBox = styled.div`
  margin-top: 15px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  cursor: pointer;

  ${media.tablet`
    width: 70%;
  `}
`;

export const IdRememberCheckImageBox = styled.figure`
  position: relative;
  width: 20px;
  height: 20px;

  ${media.mobile`
  width: 15px;
  height: 15px;
  `}
`;

export const IdRememberText = styled.span`
  margin-left: 3%;

  ${media.mobile`
    font-size: 10px;
  `}
`;

export const LoginBtn = styled.button`
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

  ${media.mobile`
    height: 35px;
    font-size: 12px;
    margin-top: 30px;
  `}
`;

export const ErrText = styled.p`
  font-size: 12px;
  color: red;
  margin-top: 10px;
`;
