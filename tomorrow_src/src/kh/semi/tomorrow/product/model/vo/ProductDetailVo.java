package kh.semi.tomorrow.product.model.vo;

public class ProductDetailVo {
	private int optNo;
	private String optName;
	private String optVal;
	private int optPrice;
	private int pSeq;
	
	
	
//	
	private int pNo;
	
	
	public ProductDetailVo() {
		super();
	}


	public ProductDetailVo(int optNo, String optName, String optVal, int optPrice) {
		super();
		this.optNo = optNo;
		this.optName = optName;
		this.optVal = optVal;
		this.optPrice = optPrice;
	}


	@Override
	public String toString() {
		return "ProductDetailVo [optNo=" + optNo + ", optName=" + optName + ", optVal=" + optVal + ", optPrice="
				+ optPrice + ", pSeq=" + pSeq + "]";
	}


	public int getOptNo() {
		return optNo;
	}


	public void setOptNo(int optNo) {
		this.optNo = optNo;
	}


	public String getOptName() {
		return optName;
	}


	public void setOptName(String optName) {
		this.optName = optName;
	}


	public String getOptVal() {
		return optVal;
	}


	public void setOptVal(String optVal) {
		this.optVal = optVal;
	}


	public int getOptPrice() {
		return optPrice;
	}


	public void setOptPrice(int optPrice) {
		this.optPrice = optPrice;
	}


	public int getpSeq() {
		return pSeq;
	}


	public void setpSeq(int pSeq) {
		this.pSeq = pSeq;
	}
	
	public int getpNo() {
		return pNo;
	}
	
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	

	

}
