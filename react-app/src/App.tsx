import React from "react";
import Hero from "./components/Hero/Hero";
import Navbar from "./components/Navbar/Navbar";
import HowItWorks from "./components/HowItWorks/HowItWorks";
import TheMission from "./components/TheMission/TheMission";

function App() {
  return (
    <div>
      <Navbar />;
      <Hero />
      <HowItWorks />
      <TheMission />
    </div>
  );
}

export default App;
