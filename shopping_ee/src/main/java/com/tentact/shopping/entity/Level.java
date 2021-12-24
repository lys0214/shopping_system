package com.tentact.shopping.entity;

public class Level {
	private int levelId;
	private String levelName;
	private double levelDiscount;
	private String levelMemo;
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public double getLevelDiscount() {
		return levelDiscount;
	}
	public void setLevelDiscount(double levelDiscount) {
		this.levelDiscount = levelDiscount;
	}
	public String getLevelMemo() {
		return levelMemo;
	}
	public void setLevelMemo(String levelMemo) {
		this.levelMemo = levelMemo;
	}
	@Override
	public String toString() {
		return "Level [levelId=" + levelId + ", levelName=" + levelName + ", levelDiscount=" + levelDiscount
				+ ", levelMemo=" + levelMemo + "]";
	}
	public Level(int levelId, String levelName) {
		super();
		this.levelId = levelId;
		this.levelName = levelName;
	}
	public Level() {
		super();
	}
	
}
