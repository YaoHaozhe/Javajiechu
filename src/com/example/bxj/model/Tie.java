package com.example.bxj.model;

import java.util.Date;

public class Tie {
	private int tid;
	private int ttype;
	private Date ttime;
	private String tcontent;
	private String tpic;
	private String tname;
	private Customer customer;
    
	public Tie(int tid, int ttype, Date ttime, String tcontent, String tpic,String tname,
			Customer customer) {
		super();
		this.tid = tid;
		this.ttype = ttype;
		this.ttime = ttime;
		this.tcontent = tcontent;
		this.tpic = tpic;
		this.tname = tname;
		this.customer = customer;
	}

	public Tie(String tpic, Customer customer) {
		super();
		this.tpic = tpic;
		this.customer = customer;
	}

	public Tie() {
		super();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTtype() {
		return ttype;
	}

	public void setTtype(int ttype) {
		this.ttype = ttype;
	}

	public Date getTtime() {
		return ttime;
	}

	public void setTtime(Date ttime) {
		this.ttime = ttime;
	}

	public String getTcontent() {
		return tcontent;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	public String getTpic() {
		return tpic;
	}

	public void setTpic(String tpic) {
		this.tpic = tpic;
	}
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
