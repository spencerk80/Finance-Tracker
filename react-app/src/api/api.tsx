import axios, { AxiosResponse } from "axios";

//TODO add controller methods

interface User {
  email: string;
  fName: string;
  lName: string;
  role: string;
  verified: boolean;
}

interface Auth {
  jwt: string;
}

interface AuthRequest {
  email: string;
  password: string;
}

interface Category {
  name: string;
  description: string;
  type: string;
}

interface Deposit {
  category: string;
  user: User;
  amount: number;
  date: string;
  userNote: string;
}

interface Withdrawal {
  category: string;
  user: User;
  amount: number;
  date: string;
  userNote: string;
}

const instance = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 15000,
  headers: { "Content-Type": "application/json" },
  withCredentials: true,
});

const authInstance = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 15000,
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
  },
  withCredentials: true,
});

const responseBody = (response: AxiosResponse) => response.data;

//user controllers

const userRequests = {
  get: (url: string) => authInstance.get<User>(url).then(responseBody),
  post: (url: string, body: User) =>
    instance.post<User>(url, body).then(responseBody),
  delete: (url: string) => instance.delete<User>(url).then(responseBody),
};

export const Users = {
  getUsers: (): Promise<User[]> => userRequests.get("/users"),
  registerUser: (user: User): Promise<User> =>
    userRequests.post("/users/register", user),
};

//authentication requests aka login

const authRequests = {
  post: (url: string, body: any) =>
    instance.post<any>(url, body).then((response) => {
      localStorage.setItem("accessToken", JSON.stringify(response.data.jwt));
      localStorage.setItem("email", JSON.stringify(response.data.user.email));
      localStorage.setItem("role", JSON.stringify(response.data.user.role));
      localStorage.setItem("fName", JSON.stringify(response.data.user.fName));
    }),
  // post
};

export const Auths = {
  login: (authRequest: any): Promise<any> =>
    authRequests.post("/auth/login", authRequest),
  // logout
};

//category controllers

const categoryRequests = {
  getList: (url: string) => authInstance.get<Category>(url).then(responseBody),
  post: (url: string, body: Category) =>
    authInstance.post<Category>(url, body).then(responseBody),
  put: (url: string, body: Category) =>
    authInstance.put<Category>(url, body).then(responseBody),
  delete: (url: string) =>
    authInstance.delete<Category>(url).then(responseBody),
};

export const Categories = {
  getCategoryList: (): Promise<Category[]> =>
    categoryRequests.getList("/categories"),
  addCategory: (category: Category): Promise<Category> =>
    categoryRequests.post("/categories", category),
  updateCategory: (category: Category): Promise<Category> =>
    categoryRequests.put("/categories", category),
  deleteCategory: (name: string): Promise<Category> =>
    categoryRequests.delete(`/categories/name/${name}`),
};

const depositRequests = {
  //   get: (url: string) => authInstance.get<Category>(url).then(responseBody),
  post: (url: string, body: Deposit) =>
    authInstance.post<Deposit>(url, body).then(responseBody),
  put: (url: string, body: Deposit) =>
    authInstance.put<Deposit>(url, body).then(responseBody),
  delete: (url: string) => authInstance.delete<Deposit>(url).then(responseBody),
};

export const Deposits = {
  addDeposit: (deposit: Deposit): Promise<Deposit> =>
    depositRequests.post("/deposits", deposit),
  updateDeposit: (deposit: Deposit): Promise<Deposit> =>
    depositRequests.put("/deposits", deposit),
};

const withdrawalRequests = {
  //   get: (url: string) => authInstance.get<Category>(url).then(responseBody),
  post: (url: string, body: Withdrawal) =>
    authInstance.post<Withdrawal>(url, body).then(responseBody),
  put: (url: string, body: Withdrawal) =>
    authInstance.put<Withdrawal>(url, body).then(responseBody),
  delete: (url: string) =>
    authInstance.delete<Withdrawal>(url).then(responseBody),
};

export const Withdrawals = {
  addWithdrawal: (Withdrawal: Withdrawal): Promise<Withdrawal> =>
    withdrawalRequests.post("/Withdrawal", Withdrawal),
  updateWithdrawal: (Withdrawal: Withdrawal): Promise<Withdrawal> =>
    withdrawalRequests.put("/Withdrawal", Withdrawal),
};
