package com.ems.controler;

import com.ems.pojo.Department;
import com.ems.pojo.Employee;
import com.ems.service.DepartmentService;
import com.ems.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;
    @RequestMapping("/showAll")
    public ModelAndView showAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, String depId){
        PageInfo<Employee> all = employeeService.findAll(pageNum, pageSize, depId);
        ModelAndView modelAndView=new ModelAndView();
        Department department = departmentService.findOne(depId);
        modelAndView.addObject("pageInfo",all);
        modelAndView.addObject("department",department);
        modelAndView.setViewName("view/emplist");
        return modelAndView;
    }
    @RequestMapping("/remove")
    public String remove(String id,String depId){
        employeeService.delete(id);
        return "redirect:/employee/showAll?depId="+depId;
    }
    @RequestMapping("/modify")
    public String modify(Employee employee){
        employeeService.update(employee);
        return "redirect:/employee/showAll?depId="+employee.getDepartmentId();
    }
    @RequestMapping("/addEmp")
    public String addEmp(Employee employee){
        employeeService.save(employee);
        return "redirect:/employee/showAll?depId="+employee.getDepartmentId();
    }
    @RequestMapping("/findOne")
    public String findOne(Employee employee, Model model){
        Employee employee1 = employeeService.findOne(employee);
        model.addAttribute("employee",employee1);
        return "view/updateEmp";
    }
}
