package kh.semi.tomorrow.order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import kh.semi.tomorrow.cart.model.vo.CartVo;
import kh.semi.tomorrow.common.JdbcTemp;
import kh.semi.tomorrow.order.model.vo.OrderDetailVo;
import kh.semi.tomorrow.order.model.vo.OrderVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;

import static kh.semi.tomorrow.common.JdbcTemp.close;

public class OrderDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//구매목록보기
	public ArrayList<OrderVo> myOrder(Connection conn, String mId) {
		ArrayList<OrderVo> orderVoList = new ArrayList<OrderVo>();
		
//			String sql = "select "
//					+ "    p_no, o_no, o_date, o_name, o_total_price, p_seq, o_detail_cnt, p_brand, p_name, PRODUCT_IMG_NAME"
//					+ "			from (select * from orders where m_id= ? ) t1 "
//					+ "					join order_detail t2 using(o_no) "
//					+ "					join product t3 using (p_no)"
//					+ "                    left outer join PRODUCT_IMG t4 using (p_no)";
		String sql_order = "select * from orders where m_id=?";
		String sql_order_detail = "select * from (select * from order_detail where o_no=?) t1 "
				+ "left outer join product t2 using (p_no)"
				+ "left outer join product_img using (p_no)";
		try {
			pstmt = conn.prepareStatement(sql_order);
			pstmt.setString(1, mId);			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVo ovo = new OrderVo();
				ovo.setoNo(rs.getInt("o_no"));
				ovo.setoDate(rs.getTimestamp("o_date"));
				ovo.setoName(rs.getString("o_name"));
				ovo.setoTotalPrice(rs.getInt("o_total_price"));
				System.out.println("구매목록dao ovo :"+ ovo);
				orderVoList.add(ovo);
			}
			System.out.println("구매목록dao ======= order detail====");
			for(OrderVo ovo : orderVoList) {
				JdbcTemp.close(rs);
				JdbcTemp.close(pstmt);
				System.out.println("구매목록dao ovo :"+ ovo);
				pstmt = conn.prepareStatement(sql_order_detail);
				pstmt.setInt(1, ovo.getoNo());					
				rs = pstmt.executeQuery();
			
				ArrayList<OrderDetailVo> odvolist = new ArrayList<OrderDetailVo>();
				int pCnt = 0; // 1번구매시 총상품개수
				while(rs.next()) {
					OrderDetailVo odvo = new OrderDetailVo();
					odvo.setOdSeq(rs.getInt("O_D_SEQ"));
					odvo.setoNo(rs.getInt("O_NO"));
					odvo.setpNo(rs.getInt("P_NO"));
					odvo.setpSeq(rs.getString("P_SEQ"));
					odvo.setoDcnt(rs.getInt("O_DETAIL_CNT"));
					
					
					pCnt += odvo.getoDcnt(); // 1번구매시 총상품개수 - 상품개수 누적
					System.out.println("구매목록dao odvo :"+ odvo);
					
					ProductVo pvo = new ProductVo();
					pvo.setpBrand(rs.getString("p_Brand"));
					pvo.setpName(rs.getString("p_Name"));
					pvo.setProductImgName(rs.getString("product_img_name"));
					
					// TODO :윤성훈 추가 컬럼들 더 채우기	
					odvo.setProductVo(pvo);
								
					
					odvolist.add(odvo);
				}
				ovo.setpCnt(pCnt); // 1번구매시 총상품개수  
				ovo.setOdVolist(odvolist);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}


		System.out.println("구매목록dao orderVoList :"+ orderVoList);
		return orderVoList;
	}


	//구매목록보기
		public ArrayList<OrderVo> myOrder2(Connection conn, String mId) {
			ArrayList<OrderVo> orderVoList = new ArrayList<OrderVo>();
			
				String sql = "select * "
						+ "from (select * from orders where m_id= ? ) t1 "
						+ "join order_detail t2 using(o_no) "
						+ "join product t3 using (p_no) "
						+ "left outer join PRODUCT_IMG t4 using (p_no)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);			
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					OrderVo vo = new OrderVo();
					vo.setoNo(rs.getInt("o_no"));
					vo.setoDate(rs.getTimestamp("o_date"));
					vo.setoName(rs.getString("o_name"));
					vo.setoTotalPrice(rs.getInt("o_total_price"));
					vo.setProductImgName(rs.getString("product_img_name"));
					vo.setpName(rs.getString("p_name"));
					vo.setpBrand(rs.getString("p_brand"));
					
					orderVoList.add(vo);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return orderVoList;
	}
}
