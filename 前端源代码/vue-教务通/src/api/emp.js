import request from "@/utils/request";

// 查询员工列表数据   这是分页查询所以要见名知意
export const queryPageApi = (name, gender, begin, end, page, pageSize) =>
  request.get(
    `/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`
  );

// 添加员工数据
export const saveEmp = (emp) => request.post("/emps", emp);

// 根据ID查询
export const queryInfoApi = (id) => request.get(`/emps/${id}`);

// 修改员工
export const updateApi = (emp) => request.put("/emps", emp);

// 删除员工
export const deleteApi = (id) => request.delete(`/emps?ids=${id}`);

//查询所有员工  因为别的页面和这个数据库页面有数据粘连所以需要这个
export const queryAllApi = () =>
  request.get(`/emps/list?apifoxApiId=274364177`);
