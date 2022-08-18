import React from "react";
import Hero from "./components/Hero/Hero";
import Navbar from "./components/Navbar/Navbar";
import HowItWorks from "./components/HowItWorks/HowItWorks";
import TheMission from "./components/TheMission/TheMission";
import TheTeam from "./components/TheTeam/TheTeam";
import CallToAction from "./components/CallToAction/CallToAction";
import Footer from "./components/Footer/Footer";

function App() {
  return (
    <div>
      <Navbar />;
      <Hero />
      <HowItWorks />
      <TheMission />
      <TheTeam />
      <CallToAction />
      <Footer />
    </div>
  );
}

export default App;
