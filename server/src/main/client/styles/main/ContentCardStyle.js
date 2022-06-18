import styled from "styled-components";
import { media } from "../common/media";

export const Wrapper = styled.div`
  width: 49%;
  height: 185px;
  margin-bottom: 20px;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
  transition: 0.3s;
  &:hover {
    box-shadow: ${(props) =>
      props.isDark
        ? "2px 3px 3px 0px rgba(255, 255, 255, 0.1)"
        : "2px 3px 3px 0px rgba(0, 0, 0, 0.1)"};
  }

  ${media.tablet`
      width: 99%;
      height: 175px;
    `}
`;

export const Banner = styled.figure`
  width: 30%;
  height: 100%;
  border-right: 1px solid #aaa;

  & .thumnail {
    height: 185px;
  }
`;

export const Body = styled.div`
  width: 70%;
  height: 100%;
  padding: 0 4% 0 2%;
  background: ${(props) => props.theme.ctBgColor};
`;

export const ProfileBox = styled.div`
  margin-top: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

export const Name = styled.span`
  margin-left: 1%;
  font-size: 12px;
`;

export const Title = styled.p`
  font-weight: bold;
  margin: 25px 0;
`;

export const MainContent = styled.p`
  margin: 20px 0;
  font-size: 14px;
`;

export const ContentFooter = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 7%;
`;

export const RightIcon = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const IconBox = styled.figure`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 5px;
`;

export const Count = styled.figcaption`
  margin-left: 10px;
  font-size: 14px;
`;

export const Date = styled.span`
  font-size: 14px;
`;
