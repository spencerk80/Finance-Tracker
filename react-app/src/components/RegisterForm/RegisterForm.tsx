import { useRef, useState, useEffect } from "react";
import { FaCheck, FaTimes } from "react-icons/fa";
import { Route, Routes, Link as RouterLink } from "react-router-dom";
import axios, { Axios, AxiosResponse } from "axios";
import { ServerResponse } from "http";

const EMAIL_REGEX =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,30}))$/;
const PWD_REGEX =
  /^(?=.+[a-z])(?=.+[A-Z])(?=.+\d)[a-zA-Z\d!@#$%^&*()_+-='";:,<.>\\/?]{8,32}$/;
const NAME_REGEX = /^[ a-zA-Z\-\’]+$/;
const REGISTER_URL = "http://localhost:8080/users/register";

const Register = () => {
  const emailRef = useRef<HTMLInputElement>(null);
  const errRef = useRef<HTMLParagraphElement>(null);

  const [email, setEmail] = useState("");
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  const [fName, setFName] = useState("");
  const [validFName, setValidFName] = useState(false);
  const [fNameFocus, setFNameFocus] = useState(false);

  const [lName, setLName] = useState("");
  const [validLName, setValidLName] = useState(false);
  const [LNameFocus, setLNameFocus] = useState(false);

  const [password, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [passwordFocus, setPwdFocus] = useState(false);

  const [passwordConfirm, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    if (emailRef.current) emailRef.current.focus();
  }, []);

  useEffect(() => {
    const result = EMAIL_REGEX.test(email);
    setValidEmail(result);
  }, [email]);

  useEffect(() => {
    const result = NAME_REGEX.test(fName);
    setValidFName(result);
  }, [fName]);

  useEffect(() => {
    const result = NAME_REGEX.test(lName);
    setValidLName(result);
  }, [lName]);

  useEffect(() => {
    const result = PWD_REGEX.test(password);
    setValidPwd(result);
    const match = password === passwordConfirm;
    setValidMatch(match);
  }, [password, passwordConfirm]);

  useEffect(() => {
    setErrMsg("");
  }, [email, password, passwordConfirm]);

  interface CreateUserResponse {
    email: string;
    password: string;
    fname: string;
    lname: string;
    passwordConfirm: string;
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // if button enabled with JS hack
    const v1 = EMAIL_REGEX.test(email);
    const v2 = PWD_REGEX.test(password);
    if (!v1 || !v2) {
      setErrMsg("Invalid Entry");
      return;
    }
    try {
      const { data } = await axios.post<CreateUserResponse>(
        REGISTER_URL,
        JSON.stringify({
          email: email,
          password: password,
          fname: fName,
          lname: lName,
          passwordConfirm: passwordConfirm,
        }),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: true,
        }
      );
      console.log(data);
      console.log(JSON.stringify(data));
      setSuccess(true);
      //clear state and controlled inputs
      //need value attrib on inputs for this
      setEmail("");
      setFName("");
      setLName("");
      setPwd("");
      setMatchPwd("");
    } catch (err) {
      if (axios.isAxiosError(err)) {
        console.log(err.cause);
        console.log(err.response?.data);
      } else {
        setErrMsg("Registration Failed");
      }
      if (errRef.current) errRef.current.focus();
    }
  };

  return (
    <>
      {success ? (
        <section>
          <h1>Success!</h1>
          <p>
            <RouterLink to="/login">Sign In</RouterLink>
          </p>
        </section>
      ) : (
        <div className="flex flex-col justify-center items-center h-[1100px]">
          <section className="lg:min-w-[20%] lg:min-h-[20%] h-[1000px] bg-brand-light text-brand flex flex-col justify-center items-center p-2 rounded-xl">
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
            <h1 className="text-brand mb-2">Register</h1>
            <form onSubmit={handleSubmit}>
              <label htmlFor="email">
                Email:
                <FaCheck
                  className={validEmail ? "text-success m-1" : "hidden"}
                />
                <FaTimes
                  className={
                    validEmail || !email ? "hidden" : "text-danger m-1"
                  }
                />
              </label>
              <input
                type="text"
                id="email"
                ref={emailRef}
                autoComplete="off"
                onChange={(e) => setEmail(e.target.value)}
                value={email}
                required
                onFocus={() => setEmailFocus(true)}
                onBlur={() => setEmailFocus(false)}
              />
              <label htmlFor="fName">
                First Name:
                <FaCheck
                  className={validFName ? "text-success m-1" : "hidden"}
                />
                <FaTimes
                  className={
                    validFName || !fName ? "hidden" : "text-danger m-1"
                  }
                />
              </label>
              <input
                type="text"
                id="fName"
                autoComplete="off"
                onChange={(e) => setFName(e.target.value)}
                value={fName}
                required
                onFocus={() => setFNameFocus(true)}
                onBlur={() => setFNameFocus(false)}
              />
              <label htmlFor="lName">
                Last Name:
                <FaCheck
                  className={validLName ? "text-success m-1" : "hidden"}
                />
                <FaTimes
                  className={
                    validLName || !lName ? "hidden" : "text-danger m-1"
                  }
                />
              </label>
              <input
                type="text"
                id="lName"
                autoComplete="off"
                onChange={(e) => setLName(e.target.value)}
                value={lName}
                required
                onFocus={() => setLNameFocus(true)}
                onBlur={() => setLNameFocus(false)}
              />
              <label htmlFor="password">
                Password:
                <FaCheck className={validPwd ? "text-success m-1" : "hidden"} />
                <FaTimes
                  className={
                    validPwd || !password ? "hidden" : "text-danger m-1"
                  }
                />
              </label>
              <input
                type="password"
                id="password"
                onChange={(e) => setPwd(e.target.value)}
                value={password}
                required
                onFocus={() => setPwdFocus(true)}
                onBlur={() => setPwdFocus(false)}
              />
              <label htmlFor="confirm_password">
                Confirm Password:
                <FaCheck
                  className={validMatch ? "text-success m-1" : "hidden"}
                />
                <FaTimes
                  className={
                    validMatch || !passwordConfirm
                      ? "hidden"
                      : "text-danger m-1"
                  }
                />
              </label>
              <input
                type="password"
                id="confirm_password"
                onChange={(e) => setMatchPwd(e.target.value)}
                value={passwordConfirm}
                required
                onFocus={() => setMatchFocus(true)}
                onBlur={() => setMatchFocus(false)}
              />
              <button
                disabled={
                  !validEmail ||
                  !validFName ||
                  !validLName ||
                  !validPwd ||
                  !validMatch
                    ? true
                    : false
                }
                className="bg-white hover:bg-brand-light text-brand font-semibold hover:font-black m-4 p-2 self-center border border-brand hover:border-transparent w-1/2"
              >
                Sign Up
              </button>
              <p className="mb-4">
                Already registered?
                <br />
                <span className="">
                  {/*put router link here*/}
                  <RouterLink to="/login">Login</RouterLink>
                </span>
              </p>
            </form>
          </section>
        </div>
      )}
    </>
  );
};

export default Register;
