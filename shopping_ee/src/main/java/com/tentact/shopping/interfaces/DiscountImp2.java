package com.tentact.shopping.interfaces;

import com.tentact.shopping.entity.Level;

public class DiscountImp2 implements Discount {

	@Override
	public double vipDiscount(Level level, double money) {
		// TODO Auto-generated method stub
		double mon = money;
		// 固定字符串内容调用equals  不会出现空指针
		if(level!=null&&"白银".equals(level.getLevelName())) {
			mon = money*0.9;
		}else if(level!=null&&"黄金".equals(level.getLevelName())) {
			mon = money*0.8;
		}else if(level!=null&&"钻石".equals(level.getLevelName())) {
			mon = money*0.7;
		}
		return mon;
	}

	@Override
	public double offDiscount(Level level, double money) {
		// TODO Auto-generated method stub
		double mon = money;
		if(level!=null&&level.getLevelName().equals("白银")) {
			mon = money*0.9;
		}else if(level!=null&&level.getLevelName().equals("黄金")) {
			mon = money*0.8;
		}else if(level!=null&&level.getLevelName().equals("钻石")) {
			mon = money*0.7;
		}
		return mon-(int)(mon/1000)*100;
	}

}
