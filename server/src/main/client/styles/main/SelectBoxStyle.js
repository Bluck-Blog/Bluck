import styled from "styled-components";
import { media } from "../common/media";

export const Filter1 = styled.div`
  font-weight: bold;
  margin-right: 20px;
  font-size: 12px;
  width: 60px;
  cursor: pointer;
  position: relative;

  ${media.mobile`
  
    font-size: 8px;
  `}

  ul {
    display: ${(props) => (props.isShow ? "block" : "none")};
    position: absolute;
    left: 0;
    top: 150%;
    background: #1f295a;
    opacity: 0.8;
    width: 100%;
    border-radius: 10px;
    z-index: 2;
    li {
      color: white;
      text-align: center;
      margin: 10px 0;
      font-size: 10px;

      ${media.mobile`
        font-size: 8px;
      `}
    }
  }
`;
