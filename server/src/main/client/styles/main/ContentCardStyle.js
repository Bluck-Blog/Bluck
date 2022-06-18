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

  ${media.mobile`
  width: 90%;
  margin: 10px auto;
  display: block;
  border: 1px solid #aaa;
  height: 250px;
  `}
`;

export const Banner = styled.figure`
  width: 30%;
  height: 100%;
  position: relative;
  border-right: 1px solid #aaa;

  ${media.mobile`
  width: 100%;
  height: 40%;
  border: none;
  border-bottom: 1px solid #aaa;
  overflow: hidden;
  `}
`;

export const Body = styled.div`
  width: 70%;
  height: 100%;
  padding: 0 4% 0 2%;
  background: ${(props) => props.theme.ctBgColor};

  ${media.mobile`
  width: 100%;
  padding: 0 4% 0 4%;
  `}
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

  ${media.mobile`
  margin: 15px 0;
  `}
`;

export const MainContent = styled.p`
  margin: 20px 0;
  font-size: 14px;

  ${media.mobile`
  font-size: 10px;
  margin: 10px 0 20px 0;
  `}
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
