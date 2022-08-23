import { useState } from "react";
import { AiOutlineMenu, AiOutlineClose } from "react-icons/ai";
import { Link } from "react-scroll";
import { Route, Routes, Link as RouterLink } from "react-router-dom";

const Navbar = () => {
  const [nav, setNav] = useState<boolean>(false);
  const handleNav = () => {
    setNav(!nav);
  };

  return (
    <div
      id="navbar"
      className="w-full h-[90px] bg-brand border-b border-brand-light"
    >
      <div className="max-w-auto mx-auto px-4 flex justify-between items-center h-full">
        <div>
          <h1 className="text-white cursor-default">
            <RouterLink to="/">Budget Buddy</RouterLink>
          </h1>
        </div>
        <div className="hidden md:flex">
          <ul className="flex items-center text-white">
            <li>
              <RouterLink to="/">How It Works</RouterLink>
            </li>
            <li>
              <RouterLink to="/">The Mission</RouterLink>
            </li>
            <li>
              <RouterLink to="/">The Team</RouterLink>
            </li>
            <li>
              <RouterLink to="/login">Login</RouterLink>
            </li>
            <li>
              <RouterLink to="/register">Register</RouterLink>
            </li>
            <li>
              <RouterLink to="../../pages/Dashboard">Dashboard</RouterLink>
            </li>
          </ul>
        </div>

        {/* Hamburger Menu */}
        <div onClick={handleNav} className="block md:hidden">
          {nav ? (
            <AiOutlineClose size={30} className="text-white cursor-pointer" />
          ) : (
            <AiOutlineMenu size={30} className="text-white cursor-pointer" />
          )}
        </div>

        {/* Mobile Menu */}
        <div
          className={
            nav
              ? "w-full bg-brand text-white absolute top-[90px] left-0 h-auto flex justify-center text-center"
              : "absolute left-[-100%]"
          }
        >
          <ul>
            <li className="text-2xl">How It Works</li>
            <li className="text-2xl">The Mission</li>
            <li className="text-2xl">The Team</li>
            <li className="text-2xl">Login</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
