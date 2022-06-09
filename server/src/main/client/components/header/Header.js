// components
import { media } from "../../styles/common/media";

// lib
import styled from "styled-components";
import LinkTag from "../common/LinkTag";

// img

export default function Header() {
  return (
    <HeaderWrapper>
      <LinkTag
        title={"BLUCK"}
        tabletSize={36}
        mobileSize={24}
        size={42}
        link={"/"}
      />
      <BtnBox>
        <LinkTag
          title={"LOG-IN"}
          tabletSize={12}
          mobileSize={10}
          size={14}
          link={"/login"}
        />
        <LinkTag
          title={"JOIN"}
          tabletSize={12}
          mobileSize={10}
          size={14}
          link={"/signup"}
        />
      </BtnBox>
    </HeaderWrapper>
  );
}

const HeaderWrapper = styled.header`
  width: 100%;
  height: 10%;
  padding-top: 1%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2%;
`;

const BtnBox = styled.div`
  width: 8%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  ${media.tablet`
      width: 15%;  
    `}
`;
