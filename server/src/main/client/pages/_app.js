// components
import Layout from "../components/Layout";

// lib
import { QueryClient, QueryClientProvider } from "react-query";
import { RecoilRoot } from "recoil";
import styled from "styled-components";

// img

export const root = new QueryClient();

function MyApp({ Component, pageProps }) {
  return (
    <QueryClientProvider client={root}>
      <RecoilRoot>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </RecoilRoot>
    </QueryClientProvider>
  );
}

export default MyApp;
