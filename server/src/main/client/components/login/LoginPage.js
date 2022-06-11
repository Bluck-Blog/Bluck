// lib
import styled from "styled-components";
import { media } from "../../styles/common/media";
import FomBox from "./component/FomBox";

//components

//img

export default function LoginPage() {
  return (
    <Wrapper>
      <FomBox />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
