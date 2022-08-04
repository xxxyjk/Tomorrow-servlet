package kh.semi.tomorrow.member.model.vo;

import java.sql.Timestamp;

public class MemberVo {
	private String mId;
	private String mPw;
	private String mName;
	private String mNickname;
	private String mPhone;
	private int mGrade;
	private String mNy;
	private Timestamp mDate;
	private String mBrith;
	private String mIntro;

	private String productImgName;

	public MemberVo() {
	}

	public MemberVo(String mId, String mPw, String mName, String mNickname, String mPhone, int mGrade, String mNy,
			Timestamp mDate, String mBrith, String mIntro) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mNickname = mNickname;
		this.mPhone = mPhone;
		this.mGrade = mGrade;
		this.mNy = mNy;
		this.mDate = mDate;
		this.mBrith = mBrith;
		this.mIntro = mIntro;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNickname() {
		return mNickname;
	}

	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public int getmGrade() {
		return mGrade;
	}

	public void setmGrade(int mGrade) {
		this.mGrade = mGrade;
	}

	public String getmNy() {
		return mNy;
	}

	public void setmNy(String mNy) {
		this.mNy = mNy;
	}

	public Timestamp getmDate() {
		return mDate;
	}

	public void setmDate(Timestamp mDate) {
		this.mDate = mDate;
	}

	public String getmBrith() {
		return mBrith;
	}

	public void setmBrith(String mBrith) {
		this.mBrith = mBrith;
	}

	public String getmIntro() {
		return mIntro;
	}

	public void setmIntro(String mIntro) {
		this.mIntro = mIntro;
	}

	@Override
	public String toString() {
		return "MemberVo [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mNickname=" + mNickname + ", mPhone="
				+ mPhone + ", mGrade=" + mGrade + ", mNy=" + mNy + ", mDate=" + mDate + ", mBrith=" + mBrith
				+ ", mIntro=" + mIntro + ", productImgName=" + productImgName + "]";
	}

}
