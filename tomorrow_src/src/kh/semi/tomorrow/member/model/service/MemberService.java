package kh.semi.tomorrow.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.semi.tomorrow.common.JdbcTemp;
import kh.semi.tomorrow.member.model.dao.MemberDao;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;


public class MemberService {
	private MemberDao dao= new MemberDao();
	
	
	//로그인
	public MemberVo login(String mId, String pw) {
		Connection conn = null;
		conn = JdbcTemp.getConnection();
		MemberVo vo = dao.login(conn, mId, pw);
		JdbcTemp.close(conn);	
		return vo;
	}
	
	// 회원가입
	public int insertMember(MemberVo member) {
		Connection conn = JdbcTemp.getConnection();
		int result = dao.insertMember(conn, member);
		JdbcTemp.close(conn);	
		return result;
		
	}
	
	// 내 정보 수정
	public int updateMember(MemberVo member) {
		Connection conn = JdbcTemp.getConnection();
		int result = dao.updateMember(conn, member);
		JdbcTemp.close(conn);	
		return result;
	}
	
	//회원이름
	public MemberVo myName(String mId) {
		Connection conn = null;
		conn = JdbcTemp.getConnection();
		MemberVo vo = dao.myName(conn, mId);
		JdbcTemp.close(conn);	
		return vo;
	}
	
	

	//보드리스트
	public ArrayList<StoryBoardVo> myBoardList(int startNum, int endNum, String mId) {
		Connection conn = JdbcTemp.getConnection();
		ArrayList<StoryBoardVo> boardlist = dao.myBoardList(conn, startNum, endNum, mId);
		JdbcTemp.close(conn);
		return boardlist;
	}
	
	public int countMyBoard(String mId) {
		int result = 0;
		Connection conn= JdbcTemp.getConnection();
		result=dao.countMyBoard(conn, mId);
		JdbcTemp.close(conn);
		return result;
	}	

	
	//구매 목록
	public ArrayList<ProductVo> myProduct(String mId) {
		Connection conn = JdbcTemp.getConnection();
		ArrayList<ProductVo> productVo = dao.myProduct(conn, mId);
		JdbcTemp.close(conn);
		return productVo;
	}
	
	//회원탈퇴
		public int deleteAccount(String mId) {
			int result = 0;
			Connection conn= JdbcTemp.getConnection();
			result=dao.deleteAccount(conn, mId);
			JdbcTemp.close(conn);
			return result;
		}
	
	
}
