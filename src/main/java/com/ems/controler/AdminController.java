package com.ems.controler;

import com.ems.pojo.Admin;
import com.ems.service.AdminService;
import com.ems.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session) {
        Admin admin1 = adminService.queryOneAdmin(admin);
        if (admin1 != null) {
            session.setAttribute("admin", admin1);
            return "redirect:/department/showAll";
        }
        return "redirect:/view/login.jsp";
    }

    @RequestMapping("/register")
    public String register(Admin admin, String number, HttpSession session, Model model) {
        String validationCode = (String) session.getAttribute("validationCode");
        if (validationCode.equalsIgnoreCase(number)) {
            adminService.register(admin);
            return "redirect:/view/login.jsp";
        } else {
            model.addAttribute("result", new Result(false, "验证码错误"));
            return "redirect:/view/regist.jsp";
        }
    }
}
