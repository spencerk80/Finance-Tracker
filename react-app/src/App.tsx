import React from "react";
import Home from "./pages/Home/Home";
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import Login from "./components/LoginForm/LoginForm";
import Register from "./components/RegisterForm/RegisterForm";
import RequireAuth from "./components/RequireAuth/RequireAuth";

import {
  Route,
  Routes,
  Link as RouterLink,
  BrowserRouter,
  Router,
} from "react-router-dom";

function App() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* <Route element={<RequireAuth />}> */}
        {/* DASHBOARD ROUTES TO BE PROTECTED */}
        {/* </Route> */}
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
