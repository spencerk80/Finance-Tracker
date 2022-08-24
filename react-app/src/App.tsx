import Home from "./pages/Home/Home";
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";
import RequireAuth from "./components/RequireAuth/RequireAuth";
import Dashboard from "./pages/Dashboard/Dashboard";
import { Route, Routes } from "react-router-dom";
import { useState } from "react";
import { GlobalStateProvider } from "./context/GlobalStateProvider";

function App() {
  return (
    <div>
      <GlobalStateProvider>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route element={<RequireAuth />}>
            {/* {/* DASHBOARD ROUTES TO BE PROTECTED */}
            <Route path="/dashboard" element={<Dashboard />} />
          </Route>
        </Routes>
        <Footer />
      </GlobalStateProvider>
    </div>
  );
}

export default App;
