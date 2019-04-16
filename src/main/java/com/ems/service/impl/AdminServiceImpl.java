package com.ems.service.impl;

import com.ems.mapper.AdminMapper;
import com.ems.pojo.Admin;
import com.ems.pojo.AdminExample;
import com.ems.service.AdminService;
import com.ems.util.Result;
import com.ems.util.Sha256Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin queryOneAdmin(Admin admin) {
        AdminExample adminExample = new AdminExample();
        String password = admin.getPassword();
        if (admin != null) {
            adminExample.createCriteria().andUsernameEqualTo(admin.getUsername());
            List<Admin> admins = adminMapper.selectByExample(adminExample);
            if (admins.size() == 1) {
                for (Admin admin1 : admins) {
                    String newPass = password + admin1.getSalt();
                    String sha256 = Sha256Util.getSHA256(newPass).substring(0, 32);
                    if (password.equals(sha256)) {
                        admin = admin1;
                    }
                }
            }
        }else {
            admin=null;
        }
        return admin;
    }

    @Override
    public Result register(Admin admin) {
        try {
            String password = admin.getPassword();
            String salt = Sha256Util.getUUID().substring(0, 4);
            admin.setSalt(salt);
            String newPass=Sha256Util.getSHA256(password+salt).substring(0,32);
            admin.setPassword(newPass);
            adminMapper.insert(admin);
            return new Result(true,"注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"注册失败");
        }
    }
}
