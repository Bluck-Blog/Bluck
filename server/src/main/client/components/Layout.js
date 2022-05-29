// components
import { darkMode } from "../state/atom";
import { GlobalStyle } from "../styles/common/GlobalStyle";
import { darkTheme, lightTheme } from "../styles/common/Theme";
import Seo from "./Seo";
import Footer from "./footer/Footer";

// lib
import { useRecoilValue } from "recoil";
import { ThemeProvider } from "styled-components";
import styled from "styled-components";
import Header from "./header/Header";

// img

export default function Layout({ children }) {
  const isDark = useRecoilValue(darkMode);

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
