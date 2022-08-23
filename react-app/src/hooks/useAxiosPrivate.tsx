import { useEffect } from "react";
import useAuth from "./useAuth";
import axios from "axios";

const axiosPrivate = axios.create({
  baseURL: "http://localhost:8080/",
  headers: { "Content-Type": "application/json" },
  withCredentials: true,
});

const useAxiosPrivate = () => {
  const { auth } = useAuth();

  useEffect(() => {
    const requestIntercept = axiosPrivate.interceptors.request.use(
      (config) => {
        if (!config.headers!["Authorization"]) {
          config!.headers!["Authorization"] = `Bearer ${auth?.jwt}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );

    return () => {
      axiosPrivate.interceptors.request.eject(requestIntercept);
    };
  }, [auth]);

  return axiosPrivate;
};

export default useAxiosPrivate;
