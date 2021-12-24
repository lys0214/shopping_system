package com.tentact.shopping.dao;

import java.util.List;

import com.tentact.shopping.entity.Goods;

public interface GoodsDAO {
	// 查询所有
	public abstract List<Goods> selectAll();
	// 通过商品id查询
	Goods select(String goodsId);
	// 修改
	boolean update(Goods goods);
	// 删除
	boolean delete(String goodsId);
	// 新建
	boolean insert(Goods goods);

}
