import React from "react";
import graph from "../../assets/FutureWealth.png";

const Hero = () => {
  return (
    <section className="w-screen border-b border-brand-light min-h-[600px]">
      <div className="container m-auto flex flex-col md:flex-row max-w-1/2 items-center justify-center text-center py-8">
        <div className="w-full h-full lg:w-1/2 lg:py-6 flex flex-col justify-between items-center text-center p-4">
          <h1 className="text-white py-5">
            Don't worry about retirement - we'll help you. Click on the button
            below and get started with our free guidance.
          </h1>
          <button className="bg-white hover:bg-brand-light text-brand font-semibold hover:font-black py-2 px-4 border border-brand hover:border-transparent w-1/2">
            Get Started
          </button>
        </div>
        <div className="w-full lg:w-1/2 lg:py-6 p-4 order-first md:order-last">
          <img src={graph} alt="Graph" className="w-full rounded-[36px]" />
        </div>
      </div>
    </section>
  );
};

export default Hero;
