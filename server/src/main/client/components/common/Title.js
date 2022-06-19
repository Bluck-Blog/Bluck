// lib

//components
import * as S from "../../styles/common/TitleStyle";

//img

export default function Title({ title, size, tabletSize, mobileSize }) {
  return (
    <S.TitleText size={size} tabletSize={tabletSize} mobileSize={mobileSize}>
      {title}
    </S.TitleText>
  );
}
