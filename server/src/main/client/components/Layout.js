import { useRecoilValue } from "recoil";
import { ThemeProvider } from "styled-components";
import { darkMode } from "../state/atom";
import { GlobalStyle } from "../styles/common/GlobalStyle";
import { darkTheme, lightTheme } from "../styles/common/Theme";
import Seo from "./Seo";

export default function Layout({ children }) {
  const isDark = useRecoilValue(darkMode);

  return (
    <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
      <GlobalStyle />
      <Seo />
      {children}
    </ThemeProvider>
  );
}
