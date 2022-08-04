package kh.semi.tomorrow.cart.model.dao;

import static kh.semi.tomorrow.common.JdbcTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.semi.tomorrow.cart.model.vo.CartVo;
import kh.semi.tomorrow.common.JdbcTemp;
import kh.semi.tomorrow.order.model.vo.OrderDetailVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

public class CartDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	// 장바구니 넣기
	public int insertmyCart(Connection conn, String mId, int pNo, String option1) {

		int result = 0;
		String sql = "insert into cart (C_NO,M_ID,P_NO,P_SEQ,C_CNT,C_NY) "
				+ "values (SEQUENCE_CART_C_NO.nextval, ?, ?, ?, 1, default)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, pNo);
			pstmt.setString(3, option1);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		System.out.println("shyoon:" + result);
		return result;
	}

	// 장바구니 목록에 들어있는 개수
	public int checkCntCart(Connection conn, String mId, int pNo) {
		int cnt = 0;

		String sql = "select c_cnt from cart where m_id=? and p_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, pNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("c_cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return cnt;
	}

	//옵션값 불러오기 
	public int checkEqualCart(Connection conn, String mId, int pNo, String option1) {
		int eql = 0;

		String sql = "select p_seq from cart where m_id=? and p_no=? and option1";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, pNo);
			pstmt.setString(3, option1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				eql = rs.getInt("p_seq");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return eql;
	}
	
	
	// 장바구니 업데이트
	public int updatemyCart(Connection conn, String mId, int pNo, int cnt, String option1) {

		int result = 0;
		String sql = "update cart set c_cnt = ? where m_id = ? and p_no = ? and p_seq = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setString(2, mId);
			pstmt.setInt(3, pNo);
			pstmt.setString(4, option1);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		System.out.println("shyoon:" + result);
		return result;
	}

	// 장바구니 목록보기
	public ArrayList<CartVo> myCart(Connection conn, String mId) {
		ArrayList<CartVo> cartVoList = null;

		String sql = "select * from cart left outer join product using(p_no) "
				+ "left outer join product_img using(p_no) where m_id = ?"
				+ "order by c_no desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();

			cartVoList = new ArrayList<CartVo>();

			while (rs.next()) {
				CartVo vo = new CartVo();

				vo.setcNo(rs.getInt("c_no"));
				vo.setmId(rs.getString("m_id"));
				vo.setpNo(rs.getInt("p_no"));
				vo.setpSeq(rs.getString("p_seq"));
				vo.setcCnt(rs.getInt("c_cnt"));
				vo.setcNy(rs.getString("c_ny"));
				vo.setpBrand(rs.getString("p_brand"));
				vo.setpName(rs.getString("p_name"));
				vo.setproductImgName(rs.getString("product_img_name"));
				vo.setpPrice(rs.getInt("p_price"));
				
				cartVoList.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return cartVoList;
	}
	
	//장바구니에서 빼기
	public int cartDelete(Connection conn, int cNo, String mId) {
		int result = 0;
		String sql = "delete from cart where c_no=? and m_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
			pstmt.setString(2, mId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == 0) {
			System.out.println("상품을 삭제하지 못했습니다.");
		} else {
			System.out.println("상품이 삭제되었습니다.");		
		}
		return result;
	}

	
	
	
	
}
