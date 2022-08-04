package kh.semi.tomorrow.storyboard.model.vo;

import java.sql.Timestamp;

public class StoryRecommentVo {
	private int rNo;
	private int bNo;
	private String rWriter;
	private String rContent;
	private Timestamp rDate;
	private String mId;
	
	public StoryRecommentVo() {
		
	}

	public StoryRecommentVo(int rNo, int bNo, String rWriter, String rContent, Timestamp rDate, String mId) {
		super();
		this.rNo = rNo;
		this.bNo = bNo;
		this.rWriter = rWriter;
		this.rContent = rContent;
		this.rDate = rDate;
		this.mId = mId;
	}

	@Override
	public String toString() {
		return "StoryRecommentVo [rNo=" + rNo + ", bNo=" + bNo + ", rWriter=" + rWriter + ", rContent=" + rContent
				+ ", rDate=" + rDate + ", mId=" + mId + "]";
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getrWriter() {
		return rWriter;
	}

	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}
	
}
