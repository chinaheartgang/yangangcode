package com.ems.service;

import com.ems.pojo.Admin;
import com.ems.util.Result;

public interface AdminService {
    Admin queryOneAdmin(Admin admin);
    Result register(Admin admin);
}
