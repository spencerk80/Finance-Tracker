import { AxiosResponse } from "axios";
import { useRef, useState, useEffect, useMemo, useContext } from "react";
import { useLocation, useNavigate, Link as RouterLink } from "react-router-dom";
import { Auths } from "../../api/api";
import { useGlobalState } from "../../context/GlobalStateProvider";

function Login() {
  const { setState } = useGlobalState();
  const navigate = useNavigate();
  const location = useLocation();
  const navigatePathname = useMemo(() => {
    const state = location.state as { from: Location };
    if (state && state.from) return state.from;
    else return "/dashboard";
  }, [location]);

  const emailRef = useRef<HTMLInputElement>(null);
  const errRef = useRef<HTMLParagraphElement>(null);

  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");

  useEffect(() => {
    if (emailRef.current) emailRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg("");
  }, [email, pwd]);

  const authRequest = {
    email: email,
    password: pwd,
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    Auths.login(authRequest)
      .then((response) => {
        setEmail("");
        setPwd("");
        navigate(navigatePathname, { replace: true });
        return response;
      })
      .catch((err) => {
        console.log(err);
      });
    if (errRef.current) errRef.current.focus();
  };

  return (
    <div>
      <div className="flex flex-col justify-center items-center min-h-[83vh]">
        <section className="lg:min-w-[20%] lg:min-h-[20%] bg-brand-light text-brand flex flex-col justify-center items-center p-2 rounded-xl">
          <p
            ref={errRef}
            className={
              errMsg
                ? "bg-brand-light text-danger font-bold p-2 m-2"
                : "absolute left-[100%]"
            }
          >
            {errMsg}
          </p>
          <h1 className="text-brand mb-2">Login</h1>
          <form onSubmit={handleSubmit}>
            <label htmlFor="email">Email:</label>
            <input
              type="text"
              id="email"
              ref={emailRef}
              autoComplete="off"
              onChange={(e) => setEmail(e.target.value)}
              value={email}
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              onChange={(e) => setPwd(e.target.value)}
              value={pwd}
              required
            />
            <button className="bg-white hover:bg-brand-light text-brand font-semibold hover:font-black m-4 p-2 self-center border border-brand hover:border-transparent w-1/2">
              Login
            </button>
            <p>
              Need to register?
              <br />
              <span className="">
                {/*put router link here*/}
                <RouterLink to="/register">Register</RouterLink>
              </span>
            </p>
          </form>
        </section>
      </div>
    </div>
  );
}

export default Login;
