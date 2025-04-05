import request from "@/utils/request";

// 分页查询班级信息
export const queryPageApi = (name, begin, end, page, pageSize) =>
  request.get(
    `/clazzs?name=${name}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`
  );
//删除班级
export const delApi = (id) => request.delete(`/clazzs/${id}`);
//新增班级
export const addApi = (clazz) => request.post("/clazzs", clazz);
//根据ID查询班级
export const queryByIdApi = (id) => request.get(`/clazzs/${id}`);
//编辑班级
export const updateApi = (user) => request.put("/clazzs", user);
// 查询所有班级信息
export const queryAllApi = () => request.get("/clazzs/list");
