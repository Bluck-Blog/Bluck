import { RecoilRoot } from "recoil";
import Layout from "../components/Layout";
import styled from "styled-components";
import Nav from "../components/index/Nav";
import Footer from "../components/footer/Footer";
import { QueryClient, QueryClientProvider } from "react-query";

export const root = new QueryClient();

function MyApp({ Component, pageProps }) {
  return (
    <QueryClientProvider client={root}>
      <RecoilRoot>
        <Layout>
          <Wapper>
            <Nav />
            <div>
              <Component {...pageProps} />
              <Footer />
            </div>
          </Wapper>
        </Layout>
      </RecoilRoot>
    </QueryClientProvider>
  );
}

const Wapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

export default MyApp;
