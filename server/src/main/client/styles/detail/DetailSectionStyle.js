import styled from "styled-components";

export const Wrapper = styled.div`
  width: 80%;
  height: 75vh;
  margin: 0 auto;
  border-radius: 40px 40px 0 0;
  overflow: hidden;
  background-color: #f9f9f9;

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
`;

export const BannerBox = styled.figure`
  width: 100%;
  height: 300px;
  position: relative;
`;

export const AccountBox = styled.div`
  width: 95%;
  margin: 20px auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const ProfileBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const ProfilFigure = styled.figure`
  width: 35px;
  height: 35px;
  position: relative;
`;

export const UserNameAndDate = styled.div`
  margin-left: 15px;
`;

export const UserName = styled.p`
  font-size: 14px;
  font-weight: 600;
  color: ${(props) => props.theme.MainTextColor};
`;

export const ContentsDate = styled.p`
  font-size: 14px;
  font-weight: 600;
  margin: 5px 0;
  color: ${(props) => props.theme.MainTextColor};
`;

export const ShareBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const IconsBox = styled.figure`
  width: 25px;
  height: 25px;
  position: relative;
  margin-left: 13px;
  cursor: pointer;
`;

export const TagBox = styled.div`
  width: 95%;
  margin: 30px auto;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: nowrap;
`;

export const TagText = styled.p`
  font-size: 14px;
  height: 28px;
  border-radius: 10px;
  padding: 0 5px;
  margin-right: 10px;
  background-color: ${(props) => props.theme.textColor};
  opacity: 0.8;
  color: ${(props) => props.theme.white};
  display: flex;
  justify-content: center;
  align-items: center;
`;
