//lib
import styled from "styled-components";
import { useRouter } from "next/router";
//components

//img

export default function DetailContent() {
  const { route } = useRouter();

  return (
    <Wapper isContent={route === "/"}>
      {route === "/" && <Alert>내용을 클릭해주세요.</Alert>}
    </Wapper>
  );
}

const Wapper = styled.section`
  width: 100%;
  height: 77vh;
  background: ${(props) => props.theme.navBgColor};
  border-radius: 50px;
  ${(props) => {
    if (props.isContent) {
      return "display: flex; justify-content: canter; align-items: center;";
    }
  }}
`;

const Alert = styled.p`
  text-align: center;
  width: 100%;
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 18px;
`;
