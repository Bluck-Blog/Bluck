// lib
import Image from "next/image";

// component

// style
import * as S from "../../../styles/detail/DetailBannerStyle";

export default function DetailBanner({ fakeData }) {
  const {
    banner: { src },
  } = fakeData;

  return (
    <S.BannerBox>
      <Image alt="banner" layout="fill" src={src} />
    </S.BannerBox>
  );
}
