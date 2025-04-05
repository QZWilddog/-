import request from '@/utils/request'

//统计员工职位
export const queryEmpJobDataApi = () => request.get('/report/empJobData')
//统计员工性别
export const queryEmpGenderDataApi = () => request.get('report/empGenderData')
//统计学员学历
export const queryStudentDegreeData = () => request.get('/report/studentDegreeData')
//统计班级人数
export const queryStudentCountData = () => request.get('/report/studentCountData')