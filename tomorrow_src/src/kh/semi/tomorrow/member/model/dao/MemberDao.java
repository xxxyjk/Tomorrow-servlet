package kh.semi.tomorrow.member.model.dao;

import static kh.semi.tomorrow.common.JdbcTemp.close;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import kh.semi.tomorrow.common.JdbcTemp;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;



public class MemberDao {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection getConnection() throws Exception{
		Context cts = new InitialContext();
		Context env = (Context)cts.lookup("java:comp/env"); 
		DataSource ds = (DataSource)env.lookup("jdbc/orcl"); 
		return ds.getConnection();
	}
	



	
	
	//로그인
	public MemberVo login(Connection conn, String mId, String mPw) { 
		MemberVo vo= null;
		String sql = "select * from member where m_id= ? and m_pw=?";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo = new MemberVo(
					rs.getString("M_ID"),
					rs.getString("M_PW"),
					rs.getString("M_NAME"),
					rs.getString("M_NICKNAME"),
					rs.getString("M_PHONE"),
					rs.getInt("M_GRADE"),
					rs.getString("M_NY"),
					rs.getTimestamp("M_DATE"),
					rs.getString("M_BIRTH"),
					rs.getString("M_INTRO")
				);	
			}							
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}			
		return vo;
	}
	


	
	// 회원가입 
	public int insertMember(Connection conn, MemberVo member) {
//		M_ID       NOT NULL VARCHAR2(20) 
//		M_PW       NOT NULL VARCHAR2(20) 
//		M_NAME     NOT NULL VARCHAR2(20) 
//		M_NICKNAME NOT NULL VARCHAR2(20) 
//		M_BIRTH    NOT NULL CHAR(6)      
//		M_INTRO             VARCHAR2(30) 
//		M_DATE     NOT NULL TIMESTAMP(6) 
//		M_PHONE    NOT NULL VARCHAR2(20) 
//		M_GRADE    NOT NULL NUMBER       
//		M_NY       NOT NULL VARCHAR2(1)
		int result=0;
		String sql = "INSERT INTO MEMBER(M_ID, M_PW, M_NAME, M_NICKNAME, "
				+ "	M_BIRTH, M_INTRO, M_DATE, M_PHONE, M_GRADE, M_NY) "
				+ "	VALUES(?, ?, ?, ?, ?, ?, default, ?, default, default)";
		
		try {
			pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getmNickname());
			pstmt.setString(5, member.getmBrith());
			pstmt.setString(6, member.getmIntro());			
			pstmt.setString(7, member.getmPhone());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		if(result == 0) {
			System.out.println("MemberDao - insertMember()에 의해 수행이 실패되었습니다.\nresult:\t" + result + "\n" );
		} else {
			System.out.println("MemberDao - insertMember()에 의해 수행이 성공되었습니다.\nresult:\t" + result + "\n" );
		}
		
		return result;
	}
	
	


	//아이디 중복 여부 확인 메서드 
		public boolean confirmId(String mId) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			boolean result = false;
			try{
				conn = getConnection();
				String sql = "select id from users where id = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(rs != null) try {rs.close();}catch(Exception e) {e.printStackTrace();}
				if(pstmt != null) try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
				if(conn != null) try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
			return result;
		}
	

	public int updateMember(Connection conn, MemberVo member) {
		int result = 0;
		String sql = "update member set m_nickname = ?, m_birth= ?, m_phone= ?, m_intro=? where m_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getmNickname());
			pstmt.setString(2, member.getmBrith());
			pstmt.setString(3, member.getmPhone());
			pstmt.setString(4, member.getmIntro());
			pstmt.setString(5, member.getmId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == 0) {
			System.out.println("MemberDao - updateMember()에 의한 수행이 실패했습니다.\nresult:\t " + result + "\n");
		} else {
			System.out.println("MemberDao - updateMember()에 의한 수행을 성공했습니다.\nresult:\t " + result + "\n");
		}
		return result;
	}
	
	//내가쓴 게시글 조회
	public ArrayList<StoryBoardVo> myBoardList(Connection conn, int startNum, int endNum, String mId) {
		ArrayList<StoryBoardVo> boardlist = null;
		
		String sql = "select *"
				+ "    from (select rownum r, b1.*"
				+ "            from (select st.b_no, st.b_title, st.b_date, st.b_cnt, st.b_img_path"
				+ "                    from story st where m_Id=?  order by b_date desc, b_no desc) b1"
				+ "    		)"
				+ "	where r between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<StoryBoardVo>();
			
			while(rs.next()) {
				StoryBoardVo vo = new StoryBoardVo();
				vo.setbNo(rs.getInt("b_no"));
				vo.setbTitle(rs.getString("b_title"));
				vo.setbDate(rs.getTimestamp("b_date"));
				vo.setbCnt(rs.getInt("b_cnt"));
				vo.setbImgPath(rs.getString("b_img_path"));
				
				boardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(boardlist == null) {
			System.out.println(boardlist);
		} else {
			System.out.println(boardlist);		
		}		
		return boardlist;
	}
	
	
	
	public int countMyBoard(Connection conn, String mId) {
		int result = 0;
		String sql = "select count(*) from story";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {					
				result= rs.getInt(1);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println(result);
		}
		return result;
	}
	
	//구매 목록보기
	public ArrayList<ProductVo> myProduct(Connection conn, String mId) {
		ArrayList<ProductVo> productVo = null;
		
		String sql = "select p_no,p_name,p_brand,p_price,cate_id,"
				+ "cate_name,opt_no,opt_val,opt_price,p_seq "
				+ "from (select * from orders where m_id=?) t1 "
				+ "join order_detail t2 using(o_no) "
				+ "join product t3 using (p_no);";
	

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);			
			rs = pstmt.executeQuery();
			
			productVo = new ArrayList<ProductVo>();
		
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setpNo(rs.getInt("p_no"));
				vo.setpName(rs.getString("p_name"));
				vo.setpBrand(rs.getString("p_brand"));
				vo.setpPrice(rs.getInt("p_price"));
				vo.setCateId(rs.getInt("cate_id"));
				vo.setCateName(rs.getString("cate_name"));
				vo.setOptNo(rs.getInt("opt_no"));
				vo.setOptVal(rs.getString("opt_val"));
				vo.setOptPrice(rs.getInt("opt_price"));
				vo.setpSeq(rs.getInt("p_seq"));
				vo.setProductImgName(rs.getString("product_img_name"));
		
				productVo.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
				
		return productVo;
	}

	

	//회원정보
	public MemberVo myName(Connection conn, String mId) {
		MemberVo vo= null;
		String sql = "select * from member where m_Id = ?";
		
			try {
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					vo = new MemberVo(
						rs.getString("M_ID"),
						rs.getString("M_PW"),
						rs.getString("M_NAME"),
						rs.getString("M_NICKNAME"),
						rs.getString("M_PHONE"),
						rs.getInt("M_GRADE"),
						rs.getString("M_NY"),
						rs.getTimestamp("M_DATE"),
						rs.getString("M_BIRTH"),
						rs.getString("M_INTRO")
					);	
				}					
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return vo;
	}
	
	
	
	//회원탈퇴
	public int deleteAccount(Connection conn, String mId) {
		int result = 0;
		String sql = "update member set m_ny= 'Y' where m_id =? "; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		
		System.out.println("탈퇴 성공");
		return result;
	}
	
	
	
	
}
