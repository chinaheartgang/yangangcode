package com.ems.service;

import com.ems.pojo.Department;
import com.ems.util.Result;
import com.github.pagehelper.PageInfo;



public interface DepartmentService {
    PageInfo<Department> findAll(Integer pageNum, Integer pageSize);
    Result addDepartment(Department department);
    Department findOne(String depId);
    Result update(Department department);
}
