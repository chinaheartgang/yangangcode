package com.ems.service.impl;

import com.ems.mapper.DepartmentMapper;
import com.ems.pojo.Department;
import com.ems.service.DepartmentService;
import com.ems.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public PageInfo<Department> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> departments = departmentMapper.selectByExample(null);
        PageInfo<Department> page = new PageInfo<Department>(departments);
        return page;
    }

    @Override
    public Result addDepartment(Department department) {
        try {
            department.setRegistTime(new Date());
            departmentMapper.insert(department);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @Override
    public Department findOne(String depId) {
        Department department = departmentMapper.selectByPrimaryKey(depId);
        return department;
    }

    @Override
    public Result update(Department department) {
        try {
            departmentMapper.updateByPrimaryKey(department);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
}
