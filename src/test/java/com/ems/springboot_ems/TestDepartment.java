package com.ems.springboot_ems;

import com.ems.pojo.Department;
import com.ems.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDepartment {
    @Resource
    private DepartmentService departmentService;

    @Test
    public void testFindAll() {
        PageInfo<Department> pageInfo = departmentService.findAll(1, 3);
        List<Department> list = pageInfo.getList();
        for (Department department : list) {
            System.out.println(department);
        }
    }
}
