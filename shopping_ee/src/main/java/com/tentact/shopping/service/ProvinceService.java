package com.tentact.shopping.service;

import com.tentact.shopping.dao.ProvinceDao;
import com.tentact.shopping.entity.Province;

import java.util.List;

public class ProvinceService {
    private ProvinceDao provinceDao = new ProvinceDao();

    public List<Province> selectAll() {
        return provinceDao.selectAll();
    }

}
