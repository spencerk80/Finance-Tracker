import {
  GiReceiveMoney,
  GiPayMoney,
  GiMoneyStack,
  GiGiftOfKnowledge,
} from "react-icons/gi";

const HowItWorks = () => {
  return (
    <section
      id="HowItWorks"
      className="bg-white text-brand container m-auto flex flex-col text-center items-center justify-center py-8 border-b border-brand-light min-w-full min-h-[600px]"
    >
      <h1 className="mt-2 mb-4 max-w-sm m-auto text-brand">How It Works</h1>
      <hr className="border-b w-8 m-auto my-6" />
      <div className="flex flex-wrap justify-between items-start">
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-auto h-auto">
            <GiReceiveMoney size={100} />
          </div>
          <h1 className="m-4 text-brand ">Input Your Income</h1>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-auto h-auto">
            <GiPayMoney size={100} />
          </div>
          <h1 className="m-4 text-brand ">Input Your Expenses</h1>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-auto h-auto">
            <GiGiftOfKnowledge size={100} />
          </div>
          <h1 className="m-4 text-brand ">Receive Personalized Guidance</h1>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-auto h-auto">
            <GiMoneyStack size={100} />
          </div>
          <h1 className="m-4 text-brand ">Achieve Financial Freedom</h1>
        </div>
      </div>
    </section>
  );
};

export default HowItWorks;
