import { atom, selector } from "recoil";

export const darkMode = atom({
  key: "darkMode",
  default: false,
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
