import request from "@/utils/request";

// 分页查询
export const pageApi = (name, degree, clazzId, page, pageSize) =>
  request.get(
    `/students?&name=${name}&degree=${degree}&clazzId=${clazzId}&page=${page}&pageSize=${pageSize}`
  );
// 根据ID删除学员信息
export const deleteByIdApi = (ids) => request.delete(`/students/${ids}`);
//新增学生
export const addApi = (stu) => request.post("/students", stu);
//修改学生
export const updateApi = (stu) => request.put(`/students`, stu);
//根据ID查询学生
export const queryInfoApi = (id) => request.get(`/students/${id}`);
//违纪处理
export const handleViolationApi = (id, score) =>
  request.put(`/students/violation/${id}/${score}`);
