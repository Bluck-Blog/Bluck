import styled from "styled-components";
import { media } from "../common/media";

export const Section = styled.section`
  width: 40%;
  height: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  ${media.tablet`
    width: 75%;
  `}

  ${media.mobile`
    width: 100%;
  `}
`;

export const LinkBoxForm = styled.div`
  width: 80%;
  margin: 0 auto;
  margin-top: 30px;
  display: flex;
  justify-content: space-around;
  align-items: center;

  ${media.mobile`
  margin: 0 auto;
  margin-top: 20px;
  `}
`;
