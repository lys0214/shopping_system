package com.tentact.shopping.interfaces;

import com.tentact.shopping.entity.Level;

public class DiscountImp1 implements Discount {

	@Override
	public double vipDiscount(Level level, double money) {
		double mon = money;
		if(level!=null&&"白银".equals(level.getLevelName())) {
			mon = money*0.95;
		}else if(level!=null&&"黄金".equals(level.getLevelName())) {
			mon = money*0.9;
		}else if(level!=null&&"钻石".equals(level.getLevelName())) {
			mon = money*0.85;
		}
		return mon;
	}

	@Override
	public double offDiscount(Level level, double money) {	
		return money-(int)(money/1000)*100;
	}

}
