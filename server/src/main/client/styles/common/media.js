import { css } from "styled-components";

const size = {
  desktop: 1024,
  tablet: 768,
};

const media = Object.keys(size).reduce((acc, label) => {
  acc[label] = (...args) => css`
    @media (max-width: ${size[label]}px) {
      ${css(...args)}
    }
  `;

  return acc;
}, {});
