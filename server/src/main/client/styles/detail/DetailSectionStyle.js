import styled from "styled-components";
import { media } from "../common/media";

export const ScrollWrapper = styled.div`
  width: 60%;
  height: 75vh;
  margin: 0 auto;
  padding-right: 20px;
  overflow: auto;
  &::-webkit-scrollbar {
    width: 5px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #aaa;
    background-clip: padding-box;
    border: 1px solid transparet;
    border-radius: 5px;
    /* border-top-left-radius: 5px;
    border-bottom-right-radius: 5px; */
  }

  ${media.mobile`
    width: 95%;
    padding: 0;
  `}
`;

export const Wrapper = styled.div`
  border-radius: 40px;
  overflow: hidden;
  background-color: ${(props) => props.theme.bgColor};
`;

export const BannerBox = styled.figure`
  width: 100%;
  height: 300px;
  position: relative;

  ${media.mobile`
    height: 200px;
  `}
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

export const TagBox = styled.div`
  width: 95%;
  margin: 30px auto;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;

  ${media.mobile`
  margin: 15px auto;
  `}
`;

export const TagText = styled.p`
  font-size: 14px;
  height: 28px;
  border-radius: 10px;
  padding: 0 5px;
  margin-right: 10px;
  background-color: ${(props) => props.theme.textColor};
  opacity: 0.8;
  color: ${(props) => props.theme.bgColor};
  display: flex;
  justify-content: center;
  align-items: center;

  ${media.mobile`
  font-size: 12px;
  padding: 0 2px;
  height: 24px;
  border-radius: 6px;
  `}
`;

export const BodyWrapper = styled.div`
  width: 95%;
  height: 100%;
  margin: 0 auto;

  ${media.mobile`
    width: 95%;
  `}
`;

export const DetailFooter = styled.ul`
  width: 90%;
  margin: 30px auto 30px auto;
  display: flex;
  justify-content: flex-end;
  align-items: center;

  ${media.mobile`
  width: 90%;
  margin: 15px auto 10px auto;
  `}
`;

export const FooterIconBOx = styled.li`
  width: 10%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const IconNumber = styled.span`
  font-size: 15px;

  ${media.mobile`
  font-size: 12px;
  `}
`;

export const NextPrevButtonBox = styled.div`
  border-top: 2px solid ${(props) => props.theme.navBgColor};
  padding: 50px 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

export const NextPrevButton = styled.button`
  width: 30%;
  height: 80px;
  border: none;
  border-radius: 20px;
  background-color: ${(props) => props.theme.textColor};
  display: flex;
  justify-content: ${(props) => (props.isLeft ? "flex-start" : "flex-end")};
  align-items: center;
  cursor: pointer;

  ${media.tablet`
  width: 45%;
  height: 60px;
  `}
`;

export const ArrowBox = styled.figure`
  width: 50px;
  height: 50px;
  position: relative;

  ${media.tablet`
  width: 40px;
  height: 40px;
  `}
`;

export const NextPrevTitleBox = styled.div``;

export const NextPrevTitle = styled.p`
  text-align: ${(props) => (props.isLeft ? "left" : "right")};
  color: ${(props) => props.theme.bgColor};
  font-size: ${(props) => (props.isTitle ? "14px" : "18px")};
  width: 180px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;

  ${media.tablet`
    font-size: ${(props) => (props.isTitle ? "10px" : "12px")};
    width: 90px;
  `}
`;
