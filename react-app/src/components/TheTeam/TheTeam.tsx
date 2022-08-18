import HappyPerson from "/home/jack/Documents/web-projects/Finance-Tracker/react-app/src/assets/HappyPerson.png";
import Jack from "/home/jack/Documents/web-projects/Finance-Tracker/react-app/src/assets/JackPic.jpg";

const TheTeam = () => {
  return (
    <section
      id="TheTeam"
      className="bg-white text-brand container m-auto flex flex-col text-center items-stretch justify-center py-8 border-b border-brand-light min-w-full min-h-[600px]"
    >
      <h1 className="mt-2 mb-4 max-w-sm m-auto text-brand">The Team</h1>
      <hr className="border-b w-8 m-auto my-6" />
      <div className="flex flex-wrap justify-between items-center">
        <div className="w-full sm:w-full md:w-1/3 flex flex-col items-center justify-center p-4">
          <div className="w-[250px] h-[250px] m-4">
            <a href="https://www.linkedin.com/in/jack-vandyke/">
              <img
                src={Jack}
                alt="Jack"
                className="rounded-full cursor-pointer"
              />
            </a>
          </div>
          <h1 className="m-4 text-brand text-2xl">Jack</h1>
          <p className="leading-normal mb-4">
            Donec nec justo eget felis facilisis fermentum. Aliquam porttitor
            mauris sit amet orci. Aenean dignissim pellentesque felis.
          </p>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
          <div className="w-[250px] h-[250px] m-4">
            <img src={HappyPerson} alt="HappyPerson" className="rounded-full" />
          </div>
          <h1 className="m-4 text-brand text-2xl">Miguel</h1>
          <p className="leading-normal mb-4">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua.
          </p>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/3 flex flex-col items-center justify-center p-4">
          <div className="w-[250px] h-[250px] m-4">
            <img src={HappyPerson} alt="HappyPerson" className="rounded-full" />
          </div>
          <h1 className="m-4 text-brand text-2xl">Kris</h1>
          <p className="leading-normal mb-4">
            Morbi in sem quis dui placerat ornare. Pellentesque odio nisi,
            euismod in, pharetra a, ultricies in, diam. Sed arcu. Cras
            consequat.
          </p>
        </div>
      </div>
    </section>
  );
};

export default TheTeam;
