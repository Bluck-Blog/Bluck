import { RecoilRoot } from "recoil";
import Layout from "../components/Layout";
import styled from "styled-components";
import Nav from "../components/index/Nav";
import Footer from "../components/footer/Footer";

function MyApp({ Component, pageProps }) {
  return (
    <RecoilRoot>
      <Layout>
        <Wapper>
          <Nav />
          <RightSection>
            <Component {...pageProps} />
            <Footer />
          </RightSection>
        </Wapper>
      </Layout>
    </RecoilRoot>
  );
}

const Wapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const RightSection = styled.div``;

export default MyApp;
