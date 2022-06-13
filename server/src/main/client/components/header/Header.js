// components
import { media } from "../../styles/common/media";
import { loginState } from "../../state/atom";
import LinkTag from "../common/LinkTag";

// lib
import styled from "styled-components";
import { useRecoilState } from "recoil";

// img

export default function Header() {
  const [isLogged, setIsLogged] = useRecoilState(loginState);

  const logOutHandle = () => {
    sessionStorage.removeItem("accessToken");
    setIsLogged((prev) => false);
  };

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
        {isLogged ? (
          <LogOutButton
            onClick={logOutHandle}
            tabletSize={12}
            mobileSize={10}
            size={14}
          >
            LOGOUT
          </LogOutButton>
        ) : (
          <LinkTag
            title={"LOG-IN"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/login"}
          />
        )}
        {isLogged ? (
          <LinkTag
            title={"MYPAGE"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/mypage"}
          />
        ) : (
          <LinkTag
            title={"JOIN"}
            tabletSize={12}
            mobileSize={10}
            size={14}
            link={"/signup"}
          />
        )}
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
  width: 10%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  ${media.tablet`
      width: 15%;  
    `}
`;

const LogOutButton = styled.span`
  font-size: ${(props) => props.size}px;
  font-weight: bold;
  cursor: pointer;
  color: ${(props) => props.theme.textColor};

  ${media.tablet`
    font-size: ${(props) => props.tabletSize}px;
  `}
`;
