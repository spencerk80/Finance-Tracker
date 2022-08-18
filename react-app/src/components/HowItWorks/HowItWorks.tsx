const HowItWorks = () => {
  return (
    <section className="bg-white text-brand container m-auto text-center py-8 border-b border-brand-light min-w-full h-max">
      <h1 className="mt-2 mb-4 max-w-sm m-auto text-brand">How It Works</h1>
      <hr className="border-b w-8 m-auto my-6" />
      <div className="flex flex-wrap justify-between items-start">
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-14 h-14 rounded-full mr-4"></div>
          <h2 className="my-4 font-medium">Cras ornare tristique elit.</h2>
          <p className="leading-normal mb-4">
            Donec nec justo eget felis facilisis fermentum. Aliquam porttitor
            mauris sit amet orci. Aenean dignissim pellentesque felis.
          </p>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-14 h-14 rounded-full mr-4"></div>
          <h2 className="my-4 font-medium">
            Vivamus vestibulum ntulla nec ante.
          </h2>
          <p className="leading-normal mb-4">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua.
          </p>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-14 h-14 rounded-full mr-4"></div>
          <h2 className="my-4 font-medium">
            Praesent placerat risus quis eros.
          </h2>
          <p className="leading-normal mb-4">
            Morbi in sem quis dui placerat ornare. Pellentesque odio nisi,
            euismod in, pharetra a, ultricies in, diam. Sed arcu. Cras
            consequat.
          </p>
        </div>
        <div className="w-full sm:w-1/2 md:w-1/4 flex flex-col items-center justify-center p-4">
          <div className="w-14 h-14 rounded-full mr-4"></div>
          <h2 className="my-4 font-medium">
            Fusce pellentesque suscipit nibh.
          </h2>
          <p className="leading-normal mb-4">
            Praesent dapibus, neque id cursus faucibus, tortor neque egestas
            auguae, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam
            dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus.
          </p>
        </div>
      </div>
    </section>
  );
};

export default HowItWorks;
