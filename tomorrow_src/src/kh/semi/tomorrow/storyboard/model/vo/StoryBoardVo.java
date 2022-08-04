package kh.semi.tomorrow.storyboard.model.vo;

import java.sql.Timestamp;
import java.util.ArrayList;


public class StoryBoardVo {
	private int bNo;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String mId;
	private int bCnt;
	private Timestamp bDate;
	private int pNo;
	private String bNy;
	private String bImgPath;
	
	private int rCnt;
	private String mIntro;
	
	private ArrayList<StoryRecommentVo> bRecommentList;
	
	public StoryBoardVo() {
		
	}

	public StoryBoardVo(int bNo, String bTitle, String bContent, String bWriter, int bCnt, Timestamp bDate, String bNy,
			String mId, int pNo) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bCnt = bCnt;
		this.bDate = bDate;
		this.bNy = bNy;
		this.mId = mId;
		this.pNo = pNo;
	}

	public StoryBoardVo(int bNo, String bTitle, String bContent, String bWriter, int bCnt, Timestamp bDate, String bNy,
			String mId, int pNo, int rCnt) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bCnt = bCnt;
		this.bDate = bDate;
		this.bNy = bNy;
		this.mId = mId;
		this.pNo = pNo;
		this.rCnt = rCnt;
	}

	public StoryBoardVo(int bNo, String bTitle, String bContent, String bWriter, int bCnt, Timestamp bDate, String bNy,
			String mId, int pNo, String bImgPath, int rCnt) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bCnt = bCnt;
		this.bDate = bDate;
		this.bNy = bNy;
		this.mId = mId;
		this.pNo = pNo;
		this.bImgPath = bImgPath;
		this.rCnt = rCnt;
	}

	
	public StoryBoardVo(int bNo, String bTitle, String bContent, String bWriter, String mId, int bCnt, Timestamp bDate,
			int pNo, String bNy, String bImgPath, int rCnt, ArrayList<StoryRecommentVo> bRecommentList) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.mId = mId;
		this.bCnt = bCnt;
		this.bDate = bDate;
		this.pNo = pNo;
		this.bNy = bNy;
		this.bImgPath = bImgPath;
		this.rCnt = rCnt;
		this.bRecommentList = bRecommentList;
	}
	

	public StoryBoardVo(int bNo, String bTitle, String bContent, String bWriter, String mId, int bCnt, Timestamp bDate,
			int pNo, String bNy, String bImgPath, int rCnt, String mIntro, ArrayList<StoryRecommentVo> bRecommentList) {
		super();
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.mId = mId;
		this.bCnt = bCnt;
		this.bDate = bDate;
		this.pNo = pNo;
		this.bNy = bNy;
		this.bImgPath = bImgPath;
		this.rCnt = rCnt;
		this.mIntro = mIntro;
		this.bRecommentList = bRecommentList;
	}

	@Override
	public String toString() {
		return "StoryBoardVo [bNo=" + bNo + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bWriter=" + bWriter
				+ ", mId=" + mId + ", bCnt=" + bCnt + ", bDate=" + bDate + ", pNo=" + pNo + ", bNy=" + bNy
				+ ", bImgPath=" + bImgPath + ", rCnt=" + rCnt + ", mIntro=" + mIntro + ", bRecommentList="
				+ bRecommentList + "]";
	}
	

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public int getbCnt() {
		return bCnt;
	}

	public void setbCnt(int bCnt) {
		this.bCnt = bCnt;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public String getbNy() {
		return bNy;
	}

	public void setbNy(String bNy) {
		this.bNy = bNy;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getbImgPath() {
		return bImgPath;
	}
	
	public void setbImgPath(String bImgPath) {
		this.bImgPath = bImgPath;
	}
	
	public int getrCnt() {
		return rCnt;
	}

	public void setrCnt(int rCnt) {
		this.rCnt = rCnt;
	}

	public String getmIntro() {
		return mIntro;
	}

	public void setmIntro(String mIntro) {
		this.mIntro = mIntro;
	}

	public ArrayList<StoryRecommentVo> getbRecommentList() {
		return bRecommentList;
	}

	public void setbRecommentList(ArrayList<StoryRecommentVo> bRecommentList) {
		this.bRecommentList = bRecommentList;
	}

}