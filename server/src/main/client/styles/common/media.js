import { css } from "styled-components";

const size = {
  tablet: 1024,
  mobile: 768,
};

export const media = Object.keys(size).reduce((acc, label) => {
  acc[label] = (...args) => css`
    @media (max-width: ${size[label]}px) {
      ${css(...args)}
    }
  `;

  return acc;
}, {});
