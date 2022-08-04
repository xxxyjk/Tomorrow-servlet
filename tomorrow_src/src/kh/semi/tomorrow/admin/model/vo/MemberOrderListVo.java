package kh.semi.tomorrow.admin.model.vo;

import java.sql.Timestamp;

public class MemberOrderListVo {
	private String productImgName;
	private String pBrand;
	private String pName;
	private int oNo;
	private int oDcnt;
	private Timestamp oDate;
	private int oTotalPrice;
	private String oName;
	
	public MemberOrderListVo () {}
	
	@Override
	public String toString() {
		return "MemberOrderListVo [productImgName=" + productImgName + ", pBrand=" + pBrand + ", pName=" + pName
				+ ", oNo=" + oNo + ", oDcnt=" + oDcnt + ", oDate=" + oDate + ", oTotalPrice=" + oTotalPrice + ", oName="
				+ oName + "]";
	}
	
	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public String getpBrand() {
		return pBrand;
	}

	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getoNo() {
		return oNo;
	}

	public void setoNo(int oNo) {
		this.oNo = oNo;
	}

	public int getoDcnt() {
		return oDcnt;
	}

	public void setoDcnt(int oDcnt) {
		this.oDcnt = oDcnt;
	}

	public Timestamp getoDate() {
		return oDate;
	}

	public void setoDate(Timestamp oDate) {
		this.oDate = oDate;
	}

	public int getoTotalPrice() {
		return oTotalPrice;
	}

	public void setoTotalPrice(int oTotalPrice) {
		this.oTotalPrice = oTotalPrice;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}		

		
	
	
}
