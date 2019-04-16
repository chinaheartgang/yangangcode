package com.ems.service;

import com.ems.pojo.Employee;
import com.ems.util.Result;
import com.github.pagehelper.PageInfo;

public interface EmployeeService {
    PageInfo<Employee> findAll(Integer pageNum,Integer pageSize,String depId);
    Result delete(String empId);
    public Result save(Employee employee);
    public Result update(Employee employee);
    Employee findOne(Employee employee);
}
