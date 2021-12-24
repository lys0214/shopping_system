package com.tentact.shopping.entity;

public class Kind {
	private int kindId;
	private String kindName;
	private String kindMemo;
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getKindMemo() {
		return kindMemo;
	}
	public void setKindMemo(String kindMemo) {
		this.kindMemo = kindMemo;
	}
	@Override
	public String toString() {
		return "Kind [kindId=" + kindId + ", kindName=" + kindName + ", kindMemo=" + kindMemo + "]";
	}
	public Kind(int kindId, String kindName, String kindMemo) {
		super();
		this.kindId = kindId;
		this.kindName = kindName;
		this.kindMemo = kindMemo;
	}
	public Kind() {
		super();
	}
	
}
