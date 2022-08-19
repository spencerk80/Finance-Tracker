const CallToAction = () => {
  return (
    <section className="bg-brand-dark text-white container m-auto flex flex-col items-center justify-center text-center py-8 border-b border-brand-light min-w-full min-h-[300px]">
      <h1 className="text-4xl">Ready To Get Started?</h1>
      <p className="p-8">
        Financial Freedom is closer thank you think. Get started now.
      </p>
      <button className="bg-white hover:bg-brand-light text-brand font-semibold hover:font-black py-4 px-8 border border-brand hover:border-transparent w-auto">
        Get Started
      </button>
    </section>
  );
};

export default CallToAction;
