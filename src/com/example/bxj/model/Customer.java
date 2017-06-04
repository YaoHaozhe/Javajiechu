package com.example.bxj.model;

import java.util.Date;

public class Customer {
	private int cid;//id
	private String cphoto;//头像
	private String cname;//昵称
	private Date cbirth;//生日
	private String cadd;//地址
	private String cphone;//登陆手机号
	private String cpass;//密码
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getCbirth() {
		return cbirth;
	}
	public void setCbirth(Date cbirth) {
		this.cbirth = cbirth;
	}
	public String getCadd() {
		return cadd;
	}
	public void setCadd(String cadd) {
		this.cadd = cadd;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCpass() {
		return cpass;
	}
	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	public Customer(String cphone, String cpass) {
		super();
		this.cphone = cphone;
		this.cpass = cpass;
	}
	public Customer(int cid, String cphoto, String cname, Date cbirth,
			String cadd, String cphone, String cpass) {
		super();
		this.cid = cid;
		this.cphoto = cphoto;
		this.cname = cname;
		this.cbirth = cbirth;
		this.cadd = cadd;
		this.cphone = cphone;
		this.cpass = cpass;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cphoto=" + cphoto + ", cname="
				+ cname +", cbirth=" + cbirth + ", cadd=" + cadd +
				", cphone=" + cphone +", cpass=" + cpass +"]";
	}
	
}
