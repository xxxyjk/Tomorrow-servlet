package kh.semi.tomorrow.order.model.vo;

import java.sql.Timestamp;
import java.util.ArrayList;



public class OrderVo {
	private int oNo;
	private String mId;
	private String pSeq;
	private int pCnt;   // 1번구매시 총상품개수
	private int pPrice;
	private Timestamp oDate;
	private int oTotalPrice;
	private String oName;
	private String eMail;
	private String oPhone;
	private String reName;
	private String reAddress;
	private String rePhone;
	private String optVal;
	private String productImgName;
	
	private String pName;
	private String pBrand;
	

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

	private ArrayList<OrderDetailVo> odVolist ;
	
	public ArrayList<OrderDetailVo> getOdVolist() {
		return odVolist;
	}
	public void setOdVolist(ArrayList<OrderDetailVo> odVolist) {
		this.odVolist = odVolist;
	}
	
	
	public OrderVo() {}
	public OrderVo(int oNo, String mId, String pSeq, int pCnt, int pPrice, Timestamp oDate, int oTotalPrice, String oName,
			String eMail, String oPhone, String reName, String reAddress, String rePhone, String optVal) {
		this.oNo = oNo;
		this.mId = mId;
		this.pSeq = pSeq;
		this.pCnt = pCnt;
		this.pPrice = pPrice;
		this.oDate = oDate;
		this.oTotalPrice = oTotalPrice;
		this.oName = oName;
		this.eMail = eMail;
		this.oPhone = oPhone;
		this.reName = reName;
		this.reAddress = reAddress;
		this.rePhone = rePhone;
		this.optVal = optVal;
	}
	

	
	@Override
	public String toString() {
		return "OrderVo [oNo=" + oNo + ", mId=" + mId + ", pSeq=" + pSeq + ", pCnt=" + pCnt + ", pPrice=" + pPrice
				+ ", oDate=" + oDate + ", oTotalPrice=" + oTotalPrice + ", oName=" + oName + ", eMail=" + eMail
				+ ", oPhone=" + oPhone + ", reName=" + reName + ", reAddress=" + reAddress + ", rePhone=" + rePhone
				+ ", optVal=" + optVal + ", productImgName=" + productImgName + ", pName=" + pName + ", pBrand="
				+ pBrand + ", odVolist=" + odVolist + "]";
	}
	public String getProductImgName() {
		return productImgName;
	}
	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}
	public int getoNo() {
		return oNo;
	}

	public void setoNo(int oNo) {
		this.oNo = oNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getpSeq() {
		return pSeq;
	}

	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}

	public int getpCnt() {
		return pCnt;
	}

	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	public String getReAddress() {
		return reAddress;
	}

	public void setReAddress(String reAddress) {
		this.reAddress = reAddress;
	}

	public String getRePhone() {
		return rePhone;
	}

	public void setRePhone(String rePhone) {
		this.rePhone = rePhone;
	}

	public String getOptVal() {
		return optVal;
	}

	public void setOptVal(String optVal) {
		this.optVal = optVal;
	}
	
	
	
}
