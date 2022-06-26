import styled from "styled-components";
import { media } from "../common/media";

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

  ${media.mobile`
    width: 25px;
    height: 25px;
  `}
`;

export const UserNameAndDate = styled.div`
  margin-left: 15px;
`;

export const UserName = styled.p`
  font-size: 14px;
  font-weight: 600;
  color: ${(props) => props.theme.MainTextColor};

  ${media.mobile`
  font-size: 12px;
  `}
`;

export const ContentsDate = styled.p`
  font-size: 14px;
  font-weight: 600;
  margin: 5px 0;
  color: ${(props) => props.theme.MainTextColor};

  ${media.mobile`
  font-size: 10px;
  margin-bottom: 0;
  margin-top: 2px;
  `}
`;

export const ShareBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  ${media.mobile`
    margin-right: 10px;
  `}
`;

export const IconsBox = styled.figure`
  width: 20px;
  height: 20px;
  position: relative;
  margin-left: 13px;
  cursor: pointer;

  ${media.mobile`
  width: 15px;
  height: 15px;
  `}
`;
