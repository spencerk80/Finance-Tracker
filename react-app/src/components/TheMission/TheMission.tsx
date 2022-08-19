import HappyPerson from "../../assets/HappyPerson.png";

const TheMission = () => {
  return (
    <section
      id="TheMission"
      className="bg-brand-dark text-white container m-auto text-center flex flex-col justify-center items-center py-8 border-b border-brand-light min-w-full min-h-[600px]"
    >
      <h1 className="mt-2 mb-4 max-w-sm m-auto text-white">The Mission</h1>
      <hr className="border-b w-8 m-auto my-6" />
      <h1 className="text-xl max-w-sm  md:max-w-lg">
        Americans have daunting financial statistics.
      </h1>
      <br />
      <p className="leading-loose mt-4">
        34% live paycheck to paycheck. <br />
        55% own stock. <br />
        42% have emergency fund.
      </p>
      <h1 className="mt-4">We'd like to change that.</h1>
      <div className="container m-auto flex flex-col md:flex-row max-w-1/2 items-center justify-center py-8">
        <div className="w-[60%] lg:min-w-1/2 lg:py-6 p-4 items-center justify-center order-last md:order-first">
          <img
            src={HappyPerson}
            alt="Steve"
            className="max-w-1/2 self-center"
          />
        </div>
        <div className="w-full h-full lg:min-w-1/2 lg:py-6 flex flex-col justify-center items-center text-center p-4 text-2xl font-medium">
          <div className="min-h-full min-w-full flex flex-col md:flex-row justify-center items-center">
            <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col justify-center p-4">
              <h2 className="m-2 uppercase">Improve Savings</h2>
            </div>
            <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
              <h2 className="m-2 uppercase">Improve Investing</h2>
            </div>
            <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
              <h2 className="m-2 uppercase">Improve Life</h2>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default TheMission;
