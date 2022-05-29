// components
import Title from "./Title";

// lib
import Link from "next/link";

export default function LinkTag({ title, size, link }) {
  return (
    <Link href={link}>
      <a>
        <Title title={title} size={size} />
      </a>
    </Link>
  );
}
