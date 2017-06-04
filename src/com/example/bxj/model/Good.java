package com.example.bxj.model;

public class Good {
	private int gid;// 商品id
	private String gname;// 商品名称
	private float gtuan;// 团购价
	private float gmen;// 门店价
	private int gsold;// 已售
	private float gscore;// 商品评分
	private String gcombo;// 套餐具体
	private String gnotice;// 注意事项
	private Seller seller;// 店家
	private String gphoto;// 商品图片

	public Good() {
		super();
		
	}

	public Good(int gid, String gname, float gtuan, float gmen, int gsold,
			float gscore, String gcombo, String gnotice, Seller seller,
			String gphoto) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gtuan = gtuan;
		this.gmen = gmen;
		this.gsold = gsold;
		this.gscore = gscore;
		this.gcombo = gcombo;
		this.gnotice = gnotice;
		this.seller = seller;
		this.gphoto = gphoto;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGphoto() {
		return gphoto;
	}

	public void setGphoto(String gphoto) {
		this.gphoto = gphoto;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public float getGtuan() {
		return gtuan;
	}

	public void setGtuan(float gtuan) {
		this.gtuan = gtuan;
	}

	public float getGmen() {
		return gmen;
	}

	public void setGmen(float gmen) {
		this.gmen = gmen;
	}

	public int getGsold() {
		return gsold;
	}

	public void setGsold(int gsold) {
		this.gsold = gsold;
	}

	public float getGscore() {
		return gscore;
	}

	public void setGscore(float gscore) {
		this.gscore = gscore;
	}

	public String getGcombo() {
		return gcombo;
	}

	public void setGcombo(String gcombo) {
		this.gcombo = gcombo;
	}

	public String getGnotice() {
		return gnotice;
	}

	public void setGnotice(String gnotice) {
		this.gnotice = gnotice;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}
