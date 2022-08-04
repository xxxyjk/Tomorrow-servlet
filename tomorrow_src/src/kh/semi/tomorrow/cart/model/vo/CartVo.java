package kh.semi.tomorrow.cart.model.vo;

public class CartVo {
	private int cNo;
	private String mId;
	private String pSeq;
	private int pNo;
	private int cCnt;
	private String cNy;
	
	private String pName;
	private String pBrand;
	private int optNo;
	private String optVal;
	private String optPrice;
	
	private String pContent;
	private int pPrice;
	private String productImgName;
	
	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public CartVo(int cNo, String mId, String pSeq, int pNo, int cCnt, String cNy, String pName, String pBrand, int optNo,
			String optVal, String optPrice, String pContent, int pPrice, String productImgName) {
		this.cNo = cNo;
		this.mId = mId;
		this.pSeq = pSeq;
		this.pNo = pNo;
		this.cCnt = cCnt;
		this.cNy = cNy;
		this.pName = pName;
		this.pBrand = pBrand;
		this.optNo = optNo;
		this.optVal = optVal;
		this.optPrice = optPrice;
		this.pContent = pContent;
		this.pPrice = pPrice;
		this.productImgName = productImgName;
	}

	@Override
	public String toString() {
		return "CartVo [cNo=" + cNo + ", mId=" + mId + ", pSeq=" + pSeq + ", pNo=" + pNo + ", cCnt=" + cCnt + ", cNy="
				+ cNy + ", pName=" + pName + ", pBrand=" + pBrand + ", optNo=" + optNo + ", optVal=" + optVal
				+ ", optPrice=" + optPrice + ", pContent=" + pContent + ", pPrice=" + pPrice + ", productImgName="
				+ productImgName + "]";
	}

	public CartVo() {}



	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
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

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getcCnt() {
		return cCnt;
	}

	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}

	public String getcNy() {
		return cNy;
	}

	public void setcNy(String cNy) {
		this.cNy = cNy;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpBrand() {
		return pBrand;
	}

	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}

	public int getOptNo() {
		return optNo;
	}

	public void setOptNo(int optNo) {
		this.optNo = optNo;
	}

	public String getOptVal() {
		return optVal;
	}

	public void setOptVal(String optVal) {
		this.optVal = optVal;
	}

	public String getOptPrice() {
		return optPrice;
	}

	public void setOptPrice(String optPrice) {
		this.optPrice = optPrice;
	}

	public String getproductImgName() {
		return productImgName;
	}
	
	public void setproductImgName(String productImgName) {
		this.productImgName = productImgName;
	}
}
