// components
import { darkMode, loginState } from "../state/atom";
import { GlobalStyle } from "../styles/common/GlobalStyle";
import { darkTheme, lightTheme } from "../styles/common/Theme";
import Seo from "./Seo";
import Footer from "./footer/Footer";
import { useRecoilValue, useSetRecoilState } from "recoil";
import Header from "./header/Header";

// lib
import { ThemeProvider } from "styled-components";
import { useEffect } from "react";
import styled from "styled-components";
import { media } from "../styles/common/media";

// img

export default function Layout({ children }) {
  const isDark = useRecoilValue(darkMode);

  const isLogged = useSetRecoilState(loginState);

  useEffect(() => {
    const TOKEN = sessionStorage.getItem("accessToken");
    if (TOKEN) {
      isLogged((prev) => true);
    }
  }, []);

  return (
    <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
      <GlobalStyle />
      <Seo />
      <Wrapper>
        <WhiteMainBox>
          <Header />
          {children}
        </WhiteMainBox>
        <Footer />
      </Wrapper>
    </ThemeProvider>
  );
}

const Wrapper = styled.div`
  width: 100vw;
  height: 100vh;
  padding-top: 3vh;
`;

const WhiteMainBox = styled.section`
  width: 96vw;
  height: 89vh;
  background: ${(props) => props.theme.noticeColor};
  border-radius: 20px;
  overflow: hidden;
  margin: 0 auto;
  padding: 0 2% 0 2%;
`;
