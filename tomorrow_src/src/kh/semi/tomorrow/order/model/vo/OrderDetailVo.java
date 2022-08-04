package kh.semi.tomorrow.order.model.vo;

import kh.semi.tomorrow.product.model.vo.ProductVo;

public class OrderDetailVo {
	private int odSeq;
	private int oNo;
	private int pNo;
	private String pSeq;
	private int oDcnt;
	private ProductVo productVo;
	private String productImgName;
	
	public ProductVo getProductVo() {
		return productVo;
	}
	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}


	public OrderDetailVo() {}
	
	@Override
	public String toString() {
		return "OrderDetailVo [odSeq=" + odSeq + ", oNo=" + oNo + ", pNo=" + pNo + ", pSeq=" + pSeq + ", oDcnt=" + oDcnt
				+ ", productImgName=" + productImgName + "]";
	}
	
	public String getProductImgName() {
		return productImgName;
	}
	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}
	public int getOdSeq() {
		return odSeq;
	}

	public void setOdSeq(int odSeq) {
		this.odSeq = odSeq;
	}

	public int getoNo() {
		return oNo;
	}

	public void setoNo(int oNo) {
		this.oNo = oNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpSeq() {
		return pSeq;
	}

	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}

	public int getoDcnt() {
		return oDcnt;
	}

	public void setoDcnt(int oDcnt) {
		this.oDcnt = oDcnt;
	}
	
	
	
}
