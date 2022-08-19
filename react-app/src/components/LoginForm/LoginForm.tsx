function Login() {
  return (
    <div>
      <div className="flex flex-col justify-center items-center min-h-[83vh]">
        <section className="lg:min-w-[20%] lg:min-h-[20%] bg-brand-light text-brand flex flex-col justify-center items-center p-2 rounded-xl">
          <h1 className="text-brand mb-2">Login</h1>
          <form>
            <label htmlFor="email">Email:</label>
            <input type="text" id="email" required />
            <label htmlFor="password">Password:</label>
            <input type="password" id="password" required />
            <button className="bg-white hover:bg-brand-light text-brand font-semibold hover:font-black m-4 p-2 self-center border border-brand hover:border-transparent w-1/2">
              Login
            </button>
          </form>
        </section>
      </div>
    </div>
  );
}

export default Login;
