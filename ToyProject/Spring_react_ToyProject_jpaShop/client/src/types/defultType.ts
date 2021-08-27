export const basicRoutePathName = ["make", "find", "modify", "admin"] as const;
export interface IRoutePathNameOfInputElement {
  name: string;
  label: string;
  inputType: "number" | "text" | "password" | "email" | "link" | "button";
  inputText: string;
  placeholder?: string;
}
export interface IRoutePathNameComponentToEle {
  pathName: typeof basicRoutePathName[number];
  categoryName: string;
  formTagInInputEl: IRoutePathNameOfInputElement[];
}
export const routeToMappingData: IRoutePathNameComponentToEle[] = [
  {
    pathName: "make",
    categoryName: "학번 확인",
    formTagInInputEl: [
      {
        name: "studentCode",
        label: "학번 입력",
        inputText: "",
        inputType: "number",
      },
      {
        name: "name",
        label: "이름 입력",
        inputText: "",
        inputType: "text",
      },
    ],
  },
  {
    pathName: "admin",
    categoryName: "어드민 로그인",

    formTagInInputEl: [
      {
        name: "name",
        label: "이름 입력",
        inputText: "",
        inputType: "number",
      },
      {
        name: "password",
        label: "비밀 번호 입력",
        inputText: "",
        inputType: "password",
      },
    ],
  },
  {
    pathName: "find",
    categoryName: "계정 찾기",
    formTagInInputEl: [
      {
        name: "name",
        label: "이름 입력",
        inputText: "",
        inputType: "text",
      },
      {
        name: "studentCode",
        label: "학번 입력",
        inputText: "",
        inputType: "number",
      },
    ],
  },
  {
    pathName: "modify",
    categoryName: "계정 로그인",
    formTagInInputEl: [
      {
        name: "name",
        label: "이름 입력",
        inputText: "",
        inputType: "text",
      },
      {
        name: "studentCode",
        label: "학번 입력",
        inputText: "",
        inputType: "number",
      },
    ],
  },
];
