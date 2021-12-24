package com.tentact.shopping.dao;

import com.alibaba.fastjson.JSON;
import com.tentact.shopping.entity.Province;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProvinceDao extends BaseDAO{

    //查询全部
    public List<Province> selectAll(){
        String sql = "select * from province";
        return super.selectAll(sql, new Object[]{});
    }


    public static void main(String[] args) {
        ProvinceDao provinceDao = new ProvinceDao();
        List<Province> provinces = provinceDao.selectAll();
        System.out.println(provinces);
        /*Province province = new Province();
        province.province("广西").provinceId("13");
        String s = JSON.toJSONString(province);
        System.out.println();
        System.out.println(s);*/
        System.out.println(JSON.toJSONString(provinces));
    }

    @Override
    public Province rowMapper(ResultSet rs) {
        Province province = new Province();
        try {
            int sid = Integer.parseInt(rs.getString(1));
            String provinceID = rs.getString(2);
            String provinceName = rs.getString(3);
            province.setProvince(provinceName);
            province.setProvinceId(provinceID);
            province.setSid(sid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return province;
    }
}
