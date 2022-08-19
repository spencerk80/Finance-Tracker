import Hero from "../../components/Hero/Hero";
import HowItWorks from "../../components/HowItWorks/HowItWorks";
import TheMission from "../../components/TheMission/TheMission";
import TheTeam from "../../components/TheTeam/TheTeam";
import CallToAction from "../../components/CallToAction/CallToAction";

function Home() {
  return (
    <div>
      <Hero />
      <HowItWorks />
      <TheMission />
      <TheTeam />
      <CallToAction />
    </div>
  );
}

export default Home;
