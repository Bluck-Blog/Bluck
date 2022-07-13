// components
import Layout from "../components/Layout";

// lib
import { RecoilRoot } from "recoil";

// img

function MyApp({ Component, pageProps }) {
  return (
    <RecoilRoot>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </RecoilRoot>
  );
}

export default MyApp;
