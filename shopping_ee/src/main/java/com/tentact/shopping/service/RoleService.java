package com.tentact.shopping.service;

import com.tentact.shopping.dao.RoleDao;
import com.tentact.shopping.entity.Account;
import com.tentact.shopping.entity.Role;

import java.util.List;

public class RoleService {
    private RoleDao roleDao = new RoleDao();

    public List<Role> selectAll(){
        return roleDao.selectAll();
    }


}
