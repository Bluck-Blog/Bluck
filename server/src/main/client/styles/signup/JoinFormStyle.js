import styled from "styled-components";
import { media } from "../common/media";

export const ErrorMsg = styled.span`
  color: red;
  font-size: 10px;
  padding-left: 5px;
  margin-bottom: 10px;
`;

export const Btn = styled.button`
  width: 47%;
  height: 40px;
  background: none;
  border: none;
  border-radius: 10px;
  color: ${(props) => props.theme.textColor};
  border: 2px solid ${(props) => props.theme.textColor};
`;

export const BtnBox = styled.div`
  width: 90%;
  margin: 40px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Section = styled.section`
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

export const Form = styled.form`
  width: 70%;
  margin: 0 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  align-items: center;
`;

export const PhotoBtn = styled.label`
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

export const Label = styled.p`
  width: 20%;
  height: 40px;
  line-height: 2.8;
  padding-left: 2%;
  font-size: 14px;
  font-weight: bold;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
`;

export const IntroLabel = styled.p`
  width: 15%;
  height: 40px;
  line-height: 2.8;
  /* padding-left: 2%; */
  font-size: 14px;
  font-weight: bold;
  background: ${(props) => props.theme.bgColor};
  color: ${(props) => props.theme.textColor};
`;

export const Input = styled.input`
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

export const InputBox = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

export const StateMsg = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  overflow: hidden;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

export const ConfirmBox = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 100%;
`;

export const ConfirmBtn = styled.button`
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

export const ConFirmInput = styled.input`
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

export const IntroInput = styled.textarea`
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

export const IntroBox = styled.div`
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

export const IdRememberBox = styled.div`
  margin-top: 15px;
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  cursor: pointer;
`;

export const IdRememberText = styled.span`
  font-size: 14px;
  margin-left: 1%;
`;

export const FileInput = styled.input`
  display: none;
`;
