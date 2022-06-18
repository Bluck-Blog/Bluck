import styled from "styled-components";

export const Login = styled.span`
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;

export const LogOut = styled.span`
  cursor: pointer;
  color: ${(props) => props.theme.textColor};
`;

export const LogBox = styled.div`
  width: 12vw;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-weight: bold;
  margin-top: 40px;
`;

export const Navi = styled.nav`
  width: 15vw;
  height: 100vh;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
`;

export const Logo = styled.a`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 42px;
  margin: 20px 0 30px 0;
  cursor: pointer;
`;

export const ProfilBox = styled.figure`
  width: 180px;
  height: 180px;
  background: ${(props) => props.theme.textColor};
  border-radius: 100px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
`;

export const Name = styled.span`
  color: ${(props) => props.theme.textColor};
  font-weight: bold;
  font-size: 24px;
`;
