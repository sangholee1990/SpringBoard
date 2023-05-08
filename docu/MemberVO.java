package com.kosmo.test;

import java.util.ArrayList;

//VO : Value Object
public class MemberVO {
				//property
	private int seq;
	private String id;
	private String pw;
	private String name;
	private String roll;
	private String regdate;
	
	private ArrayList<PayVO> payVOList;
	
	public ArrayList<PayVO> getPayVOList() {
		return payVOList;
	}
	public void setPayVOList(ArrayList<PayVO> payVOList) {
		this.payVOList = payVOList;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
