import { useRef, useState, useEffect, useMemo } from "react";
import { useLocation, useNavigate, Link as RouterLink } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import axios from "axios";

const LOGIN_URL = "http://localhost:8080/auth/login";

function Login() {
  const { setAuth } = useAuth();

  const navigate = useNavigate();
  const location = useLocation();
  const navigatePathname = useMemo(() => {
    const state = location.state as { from: Location };
    if (state && state.from) return state.from;
    else return "/";
  }, [location]);

  const userRef = useRef<HTMLInputElement>(null);
  const errRef = useRef<HTMLParagraphElement>(null);

  const [user, setUser] = useState("");
  const [pwd, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");

  useEffect(() => {
    if (userRef.current) userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg("");
  }, [user, pwd]);

  interface LoginUserResponse {
    email: String;
    password: String;
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const { data } = await axios.post<LoginUserResponse>(
        LOGIN_URL,
        JSON.stringify({ email: user, password: pwd }),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: true,
        }
      );
      console.log(JSON.stringify(data));
      setAuth({ data });
      setUser("");
      setPwd("");
      navigate(navigatePathname, { replace: true });
      return data;
    } catch (err) {
      if (axios.isAxiosError(err)) {
        console.log(err.message);
        return err.message;
      } else {
        setErrMsg("Login Failed");
      }
      if (errRef.current) errRef.current.focus();
    }
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
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setUser(e.target.value)}
              value={user}
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
