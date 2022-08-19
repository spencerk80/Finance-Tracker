import { AiFillGithub, AiFillLinkedin } from "react-icons/ai";
import { RiDiscordFill } from "react-icons/ri";
import { Link } from "react-scroll";

const Footer = () => {
  return (
    <footer className="w-full md:min-h-[300px] bg-brand text-white flex flex-col justify-center items-center pb-4 md:pb-0">
      <div className="container overflow-hidden flex flex-col md:flex-row items-center justify-center text-center">
        <h1 className="m-8 cursor-pointer">
          <Link to="navbar" spy={true} smooth={true} duration={500}>
            Budget Buddy
          </Link>
        </h1>
        <div className="w-full my-8 leading-normal text-center justify-center items-center hidden md:flex flex-col">
          <p>
            Budget Buddy is about transforming financial lives through simple to
            use tools and personalized advice. We've built Budget Buddy around
            you - the average American looking to improve their lives. Our goal
            is to give you what you want through a better, easier, and
            personalized system to manage your money.
          </p>
        </div>
        <div className="flex flex-col text-center items-center justify-center w-full md:w-1/4">
          <div className="flex flex-row items-center justify-center">
            <div className="mx-2">
              <AiFillGithub
                size={30}
                className="text-white hover:text-brand-light"
              />
            </div>
            <div className="mx-2">
              <AiFillLinkedin
                size={30}
                className="text-white hover:text-brand-light"
              />
            </div>
            <div className="mx-2">
              <RiDiscordFill
                size={30}
                className="text-white hover:text-brand-light"
              />
            </div>
          </div>
        </div>
      </div>
      <div className="bg-brand text-white pt-4 mt-4 text-sm text-center">
        ©2022 BudgetBuddy.
      </div>
    </footer>
  );
};

export default Footer;
