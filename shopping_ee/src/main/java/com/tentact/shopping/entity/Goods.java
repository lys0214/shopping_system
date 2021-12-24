package com.tentact.shopping.entity;

public class Goods {
	private String goodsId;
	private String goodsName;
	private double goodsPrice;
	private double goodsWeight;
	private String createDate;
	private Kind kind;
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public double getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Kind getKind() {
		return kind;
	}
	public void setKind(Kind kind) {
		this.kind = kind;
	}
	public Goods(String goodsId, String goodsName, double goodsPrice, double goodsWeight, String createDate,
			Kind kind) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsWeight = goodsWeight;
		this.createDate = createDate;
		this.kind = kind;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsWeight=" + goodsWeight + ", createDate=" + createDate + ", kind=" + kind + "]";
	}
	
}
