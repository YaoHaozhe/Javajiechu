package com.example.bxj.model;

public class Shoucang {
	private Customer customer;
	private Tie tie;
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
	public Shoucang(Customer customer, Tie tie) {
		super();
		this.customer = customer;
		this.tie = tie;
	}
	public Shoucang() {
		super();
	}
	
}
