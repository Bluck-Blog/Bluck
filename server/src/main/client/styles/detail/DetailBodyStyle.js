import styled from "styled-components";
import { media } from "../common/media";

export const BodyWrapper = styled.div`
  width: 95%;
  height: 100%;
  margin: 0 auto;

  ${media.mobile`
    width: 95%;
  `}
`;
