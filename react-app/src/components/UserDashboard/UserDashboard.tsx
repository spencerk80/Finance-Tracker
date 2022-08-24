import React, { useState } from "react";

const UserDashboard = () => {
  return (
    <div className="flex flex-col justify-center items-center min-h-[80vh]">
      <section className="lg:min-w-[20%] lg:min-h-[20%] h-[600px] bg-brand-light text-brand flex flex-col justify-between items-center p-2 rounded-xl">
        <h1 className="text-brand p-4">
          Welcome, {JSON.parse(localStorage.getItem("fName")!)}!
        </h1>
      </section>
    </div>
  );
};

export default UserDashboard;
