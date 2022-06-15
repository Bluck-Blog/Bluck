import { atom, selector } from "recoil";

export const darkMode = atom({
  key: "darkMode",
  default: false,
});

export const loginState = atom({
  key: "isLogged",
  default: false,
});

export const userData = atom({
  key: "userData",
  default: {
    id: "",
  },
});

export const darkModeHandle = selector({
  key: "darkModeHandle",
  get: ({ get }) => {
    const def = get(darkMode);
    return def;
  },
  set: ({ set }, newValue) => {
    if (typeof newValue === "boolean") {
      set(darkMode, !newValue);
    }
  },
});
