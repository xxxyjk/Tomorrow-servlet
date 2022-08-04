package kh.semi.tomorrow.storyboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import static kh.semi.tomorrow.common.JdbcTemp.close;

import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryRecommentVo;

public class StoryboardDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

//		B_NO      	NOT NULL NUMBER         
//		B_TITLE   	NOT NULL VARCHAR2(50)   
//		B_CONTENT 	NOT NULL VARCHAR2(3000) 
//		B_WRITER  	NOT NULL VARCHAR2(20)   
//		M_ID      	NOT NULL VARCHAR2(20)   
//		B_CNT     	NOT NULL NUMBER         
//		B_DATE    	NOT NULL TIMESTAMP(6)   
//		P_NO      	NOT NULL NUMBER         
//		B_NY      	NOT NULL VARCHAR2(1)
//		B_IMG_PATH	NOT NULL VARCHAR2(300)
	
	public int writeStoryBoard(Connection conn, StoryBoardVo vo) {
		System.out.println("StoryboardDao vo : " + vo);
		int result = 0;
		
		String sql = "INSERT INTO story VALUES (SEQUENCE_STORY_B_NO.nextval, ?, ?, (SELECT m_nickname FROM member WHERE m_id = ?), ?, default, default, ?, default, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getbTitle());
			pstmt.setString(2, vo.getbContent());
			pstmt.setString(3, vo.getmId());
			pstmt.setString(4, vo.getmId());
			pstmt.setInt(5, vo.getpNo());
			pstmt.setString(6, vo.getbImgPath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + result);
		
		return result;
	}
	
	public int updateStoryBoard(Connection conn, StoryBoardVo vo) {
		System.out.println("StoryboardDao vo : " + vo);
		int result = 0;
		
		String sql = "UPDATE story SET b_title = ?, b_content = ?, b_date = sysdate, b_img_path = ? WHERE b_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getbTitle());
			pstmt.setString(2, vo.getbContent());
			pstmt.setString(3, vo.getbImgPath());
			pstmt.setInt(4, vo.getbNo());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + result);
		
		return result;
	}
	
	public int deleteStoryBoard(Connection conn, int bNo) {
		System.out.println("StoryboardDao bNo : " + bNo);
		int result = 0;
		
		String sql = "DELETE FROM story WHERE b_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("StoryboardDao result : " + result);
		
		return result;
	}
	
	public int writeStoryReComment(Connection conn, StoryRecommentVo vo) {
		System.out.println("StoryboardDao vo : " + vo);
		int result = 0;
		
//		R_NO      NOT NULL NUMBER        
//		B_NO      NOT NULL NUMBER        
//		R_WRITER  NOT NULL VARCHAR2(20)  
//		M_ID      NOT NULL VARCHAR2(20)  
//		R_CONTENT NOT NULL VARCHAR2(100) 
//		R_DATE    NOT NULL TIMESTAMP(6)  
		
		String sql = "INSERT INTO story_recomment VALUES ((SELECT nvl(max(r_no), 0) + 1 FROM story_recomment WHERE b_no = ?), ?, (SELECT m_nickname FROM member WHERE m_id = ?), ?, ?, default)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getbNo());
			pstmt.setInt(2, vo.getbNo());
			pstmt.setString(3, vo.getmId());
			pstmt.setString(4, vo.getmId());
			pstmt.setString(5, vo.getrContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + result);
		
		return result;
	}
	
	

	public ArrayList<StoryBoardVo> listStoryBoard(Connection conn) {
		ArrayList<StoryBoardVo> volist = null;
		String sql = "SELECT * FROM story ORDER BY b_no DESC, b_date DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				volist = new ArrayList<StoryBoardVo>();
				do {
					StoryBoardVo vo = new StoryBoardVo();

					vo.setbNo(rs.getInt("B_NO"));
					vo.setbTitle(rs.getString("B_TITLE"));
					vo.setbContent(rs.getString("B_CONTENT"));
					vo.setbWriter(rs.getString("B_WRITER"));
					vo.setmId(rs.getString("M_ID"));
					vo.setbCnt(rs.getInt("B_CNT"));
					vo.setbDate(rs.getTimestamp("B_DATE"));
					vo.setpNo(rs.getInt("P_NO"));
					vo.setbNy(rs.getString("B_NY"));
					vo.setbImgPath(rs.getString("B_IMG_PATH"));

					volist.add(vo);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + volist);

		return volist;
	}
	
	public ArrayList<StoryBoardVo> listStoryBoard(Connection conn, int startRnum, int endRnum){
		ArrayList<StoryBoardVo> volist = null;
		
		String sql ="SELECT * FROM (SELECT rownum AS r, m_intro, t1.* FROM (SELECT s1.*, (SELECT count(*) FROM story_recomment r1 WHERE r1.b_no = s1.b_no) r_cnt "
				+ "FROM story s1 ORDER BY s1.b_no DESC, b_date DESC) t1 JOIN member m1 ON m1.m_id = t1.m_id) WHERE r BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				volist=new ArrayList<StoryBoardVo>();
				while(rs.next()) {
					StoryBoardVo vo = new StoryBoardVo();
					
					vo.setbNo(rs.getInt("B_NO"));
					vo.setbTitle(rs.getString("B_TITLE"));
					vo.setbContent(rs.getString("B_CONTENT"));
					vo.setbWriter(rs.getString("B_WRITER"));
					vo.setmId(rs.getString("M_ID"));
					vo.setbCnt(rs.getInt("B_CNT"));
					vo.setbDate(rs.getTimestamp("B_DATE"));
					vo.setpNo(rs.getInt("P_NO"));
					vo.setbNy(rs.getString("B_NY"));
					vo.setbImgPath(rs.getString("B_IMG_PATH"));
					vo.setrCnt(rs.getInt("R_CNT"));
					vo.setmIntro(rs.getString("M_INTRO"));
					
					volist.add(vo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + volist);
		
		return volist;
	}
	
	public int countStoryBoard(Connection conn) {
		int result = 0;
		String sql = "SELECT count(*) cnt FROM story";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public StoryBoardVo readStoryBoard(Connection conn, int bNo) {
		StoryBoardVo vo = null;
		
		String sql1 = "SELECT m1.m_intro, s1.* FROM story s1 JOIN member m1 ON s1.m_id = m1.m_id WHERE b_no = ?";
		String sql2 = "SELECT * FROM story_recomment WHERE b_no = ? ORDER BY r_date DESC, r_no DESC";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bNo);

			rs = pstmt.executeQuery();
			vo = new StoryBoardVo();

//			B_NO      	NOT NULL NUMBER         
//			B_TITLE   	NOT NULL VARCHAR2(50)   
//			B_CONTENT 	NOT NULL VARCHAR2(3000) 
//			B_WRITER  	NOT NULL VARCHAR2(20)   
//			M_ID      	NOT NULL VARCHAR2(20)   
//			B_CNT     	NOT NULL NUMBER         
//			B_DATE    	NOT NULL TIMESTAMP(6)   
//			P_NO      	NOT NULL NUMBER         
//			B_NY      	NOT NULL VARCHAR2(1)
//			B_IMG_PATH	NOT NULL VARCHAR2(300)
			
			if (rs.next()) {
				vo.setbNo(rs.getInt("B_NO"));
				vo.setbTitle(rs.getString("B_TITLE"));
				vo.setbContent(rs.getString("B_CONTENT"));
				vo.setbWriter(rs.getString("B_WRITER"));
				vo.setmId(rs.getString("M_ID"));
				vo.setbCnt(rs.getInt("B_CNT"));
				vo.setbDate(rs.getTimestamp("B_DATE"));
				vo.setpNo(rs.getInt("P_NO"));
				vo.setbNy(rs.getString("B_NY"));
				vo.setbImgPath(rs.getString("B_IMG_PATH"));
				vo.setmIntro(rs.getString("M_INTRO"));
				
				close(rs);
				close(pstmt);
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, bNo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					ArrayList<StoryRecommentVo> brvoList = new ArrayList<StoryRecommentVo>();
					do {
						StoryRecommentVo rvo = new StoryRecommentVo();
						
//						R_NO      NOT NULL NUMBER        
//						B_NO      NOT NULL NUMBER        
//						R_WRITER  NOT NULL VARCHAR2(20)  
//						M_ID      NOT NULL VARCHAR2(20)  
//						R_CONTENT NOT NULL VARCHAR2(100) 
//						R_DATE    NOT NULL TIMESTAMP(6)
						rvo.setrNo(rs.getInt("R_NO"));
						rvo.setbNo(rs.getInt("B_NO"));
						rvo.setrWriter(rs.getString("R_WRITER"));
						rvo.setmId(rs.getString("M_ID"));
						rvo.setrContent(rs.getString("R_CONTENT"));
						rvo.setrDate(rs.getTimestamp("R_DATE"));
						brvoList.add(rvo);
					} while(rs.next());
				vo.setbRecommentList(brvoList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + vo);
		
		return vo;
	}
	
	public int hitStoryBoard(Connection conn, int bNo) {
		System.out.println("StoryboardDao bNo : " + bNo);
		int result = 0;
		
		String sql = "UPDATE story SET b_cnt = b_cnt + 1 WHERE b_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);	
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("StoryboardDao vo : " + result);
		
		return result;
	}
}
