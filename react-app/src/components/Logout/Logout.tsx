import React, { MouseEvent } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();

  const handleOnClick = (e: MouseEvent<HTMLButtonElement>) => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <div>
      <div className="flex flex-col justify-center items-center min-h-[83vh]">
        <section className="lg:min-w-[20%] lg:min-h-[20%] h-[150px] bg-brand-light text-brand flex flex-col justify-center items-center p-2 rounded-xl">
          <h1 className="text-brand p-4">Are you sure?</h1>
          <button className="p-4" onClick={handleOnClick}>
            Logout
          </button>
        </section>
      </div>
    </div>
  );
};

export default Logout;
