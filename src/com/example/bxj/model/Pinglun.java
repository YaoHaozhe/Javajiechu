package com.example.bxj.model;

public class Pinglun {
	private int pid;
	private String content;
	private Customer customer;
	private Tie tie;
	public Pinglun() {
		super();
	}
	public Pinglun(int pid, String content, Customer customer, Tie tie) {
		super();
		this.pid = pid;
		this.content = content;
		this.customer = customer;
		this.tie = tie;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Tie getTie() {
		return tie;
	}
	public void setTie(Tie tie) {
		this.tie = tie;
	}
}
