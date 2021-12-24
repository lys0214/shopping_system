package com.tentact.shopping.dao;

import com.tentact.shopping.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDao extends BaseDAO{
    //查
    public List<Role> selectAll(){
        String sql = "select * from role";
        return super.selectAll(sql,new Object[]{});
    }


    public static void main(String[] args) {
        RoleDao roleDao = new RoleDao();
        List<Role> roles = roleDao.selectAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Override
    public Role rowMapper(ResultSet rs) {
        Role role = new Role();
        try {
            role.setRoleId(rs.getInt("roleid"));
            role.setRoleName(rs.getString("rolename"));
            role.setRoleMemo(rs.getString("rolememo"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }
}
