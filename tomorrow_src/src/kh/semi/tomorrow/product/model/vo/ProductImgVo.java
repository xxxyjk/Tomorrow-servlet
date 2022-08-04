package kh.semi.tomorrow.product.model.vo;

public class ProductImgVo {
	private int productImgNo;
	private String productImgName;
	private int productImgSize;
	private int pNo;
	
	@Override
	public String toString() {
		return "ProductImgVo [productImgNo=" + productImgNo + ", productImgName=" + productImgName + ", productImgSize="
				+ productImgSize + ", pNo=" + pNo + "]";
	}
	public int getProductImgNo() {
		return productImgNo;
	}
	public void setProductImgNo(int productImgNo) {
		this.productImgNo = productImgNo;
	}
	public String getProductImgName() {
		return productImgName;
	}
	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}
	public int getProductImgSize() {
		return productImgSize;
	}
	public void setProductImgSize(int productImgSize) {
		this.productImgSize = productImgSize;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	
	
}
