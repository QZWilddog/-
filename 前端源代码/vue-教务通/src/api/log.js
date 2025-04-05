import request from '@/utils/request'
//分页条件查询日志
export const pageApi = (page, pageSize) => request.get(`/log/page?page=${page}&pageSize=${pageSize}`)