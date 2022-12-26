import React from "react";

type ButtonIconType = {
  text: string;
};

const ButtonIcon = ({ text }: ButtonIconType) => {
  return (
    <button className="btn btn-primary btn-icon">
      <p>{text}</p>
    </button>
  );
};

export default ButtonIcon;
