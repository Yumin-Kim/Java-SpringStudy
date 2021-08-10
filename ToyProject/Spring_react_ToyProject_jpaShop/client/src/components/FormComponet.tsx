import React from "react";
import InputBox from "./InputBox";

// const data = [
//   {
//     text: "이름";
//   type: string;
//   placeholder: string;
//   name: string;
// }
// ]

const FormComponent = () => {
  return (
    <form action="">
      <p>
        <label> 이름 : </label> <input type="text" name="name" />
      </p>
      <p>
        <label> 나이 : </label> <input type="text" name="name" />
      </p>
      <p>
        <label> 주소 : </label> <input type="text" name="name" />
      </p>
      <p>
        <label> 주소 : </label> <input type="text" name="name" />
      </p>
      
    </form>
  );
};

export default FormComponent;
