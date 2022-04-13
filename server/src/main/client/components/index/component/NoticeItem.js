import styled from "styled-components";

export default function NoticeItem({ item }) {
  return <Item>{item.title}</Item>;
}

const Item = styled.li`
  width: 100%;
  font-size: 9px;
  margin: 12px 0;
  cursor: pointer;
`;
