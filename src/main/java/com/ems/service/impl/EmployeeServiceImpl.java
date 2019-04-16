package com.ems.service.impl;

import com.ems.mapper.EmployeeMapper;
import com.ems.pojo.Employee;
import com.ems.pojo.EmployeeExample;
import com.ems.service.EmployeeService;
import com.ems.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public PageInfo<Employee> findAll(Integer pageNum, Integer pageSize,String depId) {
        PageHelper.startPage(pageNum,pageSize);
        EmployeeExample example=new EmployeeExample();
        example.createCriteria().andDepartmentIdEqualTo(depId);
        List<Employee> employees = employeeMapper.selectByExample(example);
        PageInfo<Employee> pageInfo=new PageInfo<>(employees);
        return pageInfo;
    }

    @Override
    public Result delete(String id) {
        try {
            employeeMapper.deleteByPrimaryKey(id);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
    @Override
    public Result save(Employee employee) {
        try {
            employee.setId(UUID.randomUUID().toString().replace("-",""));
            employeeMapper.insert(employee);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @Override
    public Result update(Employee employee) {
        try {
            employeeMapper.updateByPrimaryKey(employee);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @Override
    public Employee findOne(Employee employee) {
        Employee employee1 = employeeMapper.selectByPrimaryKey(employee.getId());
        return employee1;
    }
}
