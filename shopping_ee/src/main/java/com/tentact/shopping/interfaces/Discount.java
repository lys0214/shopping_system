package com.tentact.shopping.interfaces;

import com.tentact.shopping.entity.Level;

public interface Discount {
	// 接口中定义两个折扣方法
	public abstract double vipDiscount(Level level,double money);
	
	double offDiscount(Level level,double money);
}
