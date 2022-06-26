import styled from "styled-components";
import { media } from "../common/media";

export const BannerBox = styled.figure`
  width: 100%;
  height: 300px;
  position: relative;

  ${media.mobile`
    height: 200px;
  `}
`;
