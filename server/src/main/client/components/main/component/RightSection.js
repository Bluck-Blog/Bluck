//lib
import styled from "styled-components";
import SelectBox from "./SelectBox";

//components

//img

export default function RightSection() {
  const filter = ["작성자", "제목", "태그"];

  return (
    <Wrapper>
      <SearchBox>
        <AbsolBox>
          <SelectBox list={filter} />
        </AbsolBox>
        <Search />
      </SearchBox>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  width: 50%;
  height: 100%;
`;

const SearchBox = styled.form`
  position: relative;
  width: 50%;
  height: 20px;
  border: 1px solid black;
  border-radius: 10px;
`;

const AbsolBox = styled.div`
    position: absolute;
    left: 0; top: 50%;
    transform: translate(0, -50%);
    z-index; 1;
`;

const Search = styled.input`
  border: none;
  width: 80%;
  height: 100%;
`;
