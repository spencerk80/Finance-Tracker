import HappyPerson from "/home/jack/Documents/web-projects/Finance-Tracker/react-app/src/assets/HappyPerson.png";

const TheMission = () => {
  return (
    <section className="bg-brand-dark text-white container m-auto text-center py-8 border-b border-brand-light min-w-full h-max">
      <h1 className="mt-2 mb-4 max-w-sm m-auto text-white">The Mission</h1>
      <hr className="border-b w-8 m-auto my-6" />
      <h1 className="text-base">
        To change American's saving, spending, and investing habits from the
        listed statistics below through simple to use tools and personalized
        advice.
      </h1>
      <div className="container m-auto flex flex-col md:flex-row max-w-1/2 items-center justify-center py-8">
        <div className="w-full lg:min-w-1/2 lg:py-6 p-4 items-center justify-center">
          <img
            src={HappyPerson}
            alt="Graph"
            className="max-w-1/2 rounded-[36px] self-center"
          />
        </div>
        <div className="w-full h-full lg:min-w-1/2 lg:py-6 flex flex-col justify-between items-center text-center p-4 text-2xl font-medium">
          <div className="min-h-full min-w-full flex flex-wrap justify-between items-start">
            <div className="w-full min-sm:w-1/2 md:w-1/3 flex flex-col justify-between p-4">
              <h2 className="m-2 uppercase">Live Paycheck To Paycheck</h2>
              <p className="m-2">34%</p>
            </div>
            <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
              <h2 className="m-2 uppercase">Own Stock</h2>
              <p className="m-2">55%</p>
            </div>
            <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
              <h2 className="m-2 uppercase">Have Emergency Fund</h2>
              <p className="m-2">42%</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default TheMission;
