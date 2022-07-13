import styled from "styled-components";
import { media } from "../common/media";

export const ErrorMsg = styled.span`
  color: red;
  font-size: 10px;
  padding-left: 5px;
  margin-bottom: 10px;

  ${media.mobile`
  margin-bottom: 5px;
  `}
`;

export const Btn = styled.button`
  width: 47%;
  height: 40px;
  background: none;
  border: none;
  border-radius: 10px;
  color: ${(props) => props.theme.textColor};
  border: 2px solid ${(props) => props.theme.textColor};

  ${media.mobile`
  height: 30px;
  font-size: 10px;
  `}
`;

export const BtnBox = styled.div`
  width: 90%;
  margin: 40px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;

  ${media.mobile`
  width: 100%;
  margin: 20px 0;
  `}
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

  ${media.mobile`
  display: block;
  `}

  &::-webkit-scrollbar {
    width: 5px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #aaa;
    background-clip: padding-box;
    border: 1px solid transparet;
    border-radius: 5px;
  }
`;

export const Form = styled.form`
  width: 70%;
  margin: 0 20px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  align-items: center;

  ${media.mobile`
    width: 95%;
    margin: 0 auto;
  `}
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

  ${media.mobile`
    margin: 20px auto;
    width: 100px;
    height: 100px;
  `}
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

  ${media.mobile`
  font-size: 10px;
  padding-left: 4%;
  line-height: 4;
  `}
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

  ${media.mobile`
  font-size: 10px;
  `}
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

  ${media.mobile`
  margin-bottom: 5px;
  `}
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

  ${media.mobile`
  margin-bottom: 5px;
  `}
`;

export const ConfirmBtn = styled.button`
  cursor: pointer;
  border: none;
  background: none;
  font-size: 10px;
  font-weight: bold;
  margin-left: 2%;
  width: 20%;
  height: 40px;
  border: 2px solid ${(props) => props.theme.textColor};
  border-radius: 10px;
`;

export const ConFirmInput = styled.input`
  width: 60%;
  height: 40px;
  border: none;
  background: none;
  border-radius: 0 10px 10px 0;
  /* padding-left: 5%; */
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

  ${media.mobile`
  margin-top: 10px;
  `}
`;

export const IdRememberText = styled.span`
  font-size: 14px;
  margin-left: 1%;

  ${media.mobile`
  font-size: 10px;
  `}
`;

export const FileInput = styled.input`
  display: none;
`;

export const IdRememberImageBox = styled.figure`
  position: relative;
  width: 20px;
  height: 20px;

  ${media.mobile`
  width: 10px;
  height: 10px;
  `}
`;
