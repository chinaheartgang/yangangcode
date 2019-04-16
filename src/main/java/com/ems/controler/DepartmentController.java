package com.ems.controler;

import com.ems.pojo.Department;
import com.ems.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/showAll")
    public ModelAndView showAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Department> pageInfo = departmentService.findAll(pageNum, pageSize);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("view/departmentlist");
        return modelAndView;
    }

    @RequestMapping("/addDepartment")
    public String addDepartment(Department department) {
        departmentService.addDepartment(department);
        return "redirect:/department/showAll";
    }

    @RequestMapping("/findOne")
    public String findOne(Department department, ModelMap modelMap) {
        Department one = departmentService.findOne(department.getId());
        modelMap.addAttribute("department", one);
        return "view/updateDept";
    }

    @RequestMapping("/modify")
    public String modify(Department department, ModelMap modelMap) {
         departmentService.update(department);
        return "redirect:/department/showAll";
    }
}
