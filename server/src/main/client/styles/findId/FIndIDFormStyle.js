import styled from "styled-components";
import { media } from "../common/media";

export const FormWrapper = styled.form`
  width: 80%;
`;

export const NameText = styled.p`
  text-align: center;
  font-size: 28px;
`;

export const IdInputForFindId = styled.input`
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

export const PhoneInputForFindId = styled.input`
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

export const IdBoxForFindId = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.theme.bgColor};
  border-radius: 10px;
  margin-top: 20px;
`;

export const LabelForFindId = styled.p`
  width: 20%;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  color: ${(props) => props.theme.textColor};

  ${media.mobile`
  padding-left: 4%;
  font-size: 10px;
  `}
`;

export const ForFindIdBtn = styled.button`
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
