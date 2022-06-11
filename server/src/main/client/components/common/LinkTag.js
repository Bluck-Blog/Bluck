// components
import Title from "./Title";

// lib
import Link from "next/link";

export default function LinkTag({ title, size, link, tabletSize, mobileSize }) {
  return (
    <Link href={link}>
      <a>
        <Title
          tabletSize={tabletSize}
          mobileSize={mobileSize}
          title={title}
          size={size}
        />
      </a>
    </Link>
  );
}
