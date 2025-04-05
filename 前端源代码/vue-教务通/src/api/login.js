import request from "@/utils/request";

//登陆
export const loginApi = (user) => request.post("/login", user);
