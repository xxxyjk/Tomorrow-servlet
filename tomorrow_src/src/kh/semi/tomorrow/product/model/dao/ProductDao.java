package kh.semi.tomorrow.product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.semi.tomorrow.cart.model.vo.CartVo;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

import static kh.semi.tomorrow.common.JdbcTemp.*;

public class ProductDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ArrayList<ProductVo> selectAllProduct(Connection conn) {
		ArrayList<ProductVo> volist = null;
		String sql = "select p_no, p_content, p_name, p_brand, p_price from product order by p_no desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				volist = new ArrayList<ProductVo>();
				do {
					ProductVo vo = new ProductVo();
					vo.setpNo(rs.getInt("p_no"));
					vo.setpContent(rs.getString("p_content"));
					vo.setpName(rs.getString("p_name"));
					vo.setpBrand(rs.getString("p_brand"));
					vo.setpPrice(rs.getInt("p_price"));

					volist.add(vo);

				} while (rs.next());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return volist;
	}

	public ArrayList<ProductVo> selectAllProduct(Connection conn, int startRnum, int endRnum, int pageCateId, int pNo) {
		ArrayList<ProductVo> volist = null;

		String sql = "select p_no, category_id, p_content, p_name, p_brand, p_price, product_img_name from "
				+ " (select rownum r, t1.* from (select p1.* from product p1 ";
		if (pageCateId > 0) {
			sql += " where category_id=?";
		}
		sql += " order by p_no desc) t1) join product_img using (p_no) " + " where r between ? and ?";

		try {
			pstmt = conn.prepareStatement(sql);
			if (pageCateId > 0) {
				pstmt.setInt(2, startRnum);
				pstmt.setInt(3, endRnum);
				pstmt.setInt(1, pageCateId);
			} else {
				pstmt.setInt(1, startRnum);
				pstmt.setInt(2, endRnum);
			}

			rs = pstmt.executeQuery();

			if (rs != null) {
				volist = new ArrayList<ProductVo>();
				while (rs.next()) {
					ProductVo vo = new ProductVo();
					vo.setpContent(rs.getString("p_content"));
					vo.setpName(rs.getString("p_name"));
					vo.setpBrand(rs.getString("p_brand"));
					vo.setpPrice(rs.getInt("p_price"));
					vo.setCateId(rs.getInt("category_id"));
					vo.setpNo(rs.getInt("p_no"));
					vo.setProductImgName(rs.getString("product_img_name"));
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

		return volist;
	}
	
	public ArrayList<ProductVo> selectAllProduct(Connection conn, int pNo) {
		ArrayList<ProductVo> volist = null;

		String sql = "select p_no, p_name, p_brand,p_price, category_id, product_img_name "
				+ "from product p join product_img i using(p_no) "
				+ "where p_no in (1,4,7,2,5)";

		try {
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();

			if (rs != null) {
				volist = new ArrayList<ProductVo>();
				while (rs.next()) {
					ProductVo vo = new ProductVo();
					vo.setProductImgName(rs.getString("product_img_name"));
					vo.setpName(rs.getString("p_name"));
					vo.setpBrand(rs.getString("p_brand"));
					vo.setpPrice(rs.getInt("p_price"));
					vo.setCateId(rs.getInt("category_id"));
					vo.setpNo(rs.getInt("p_no"));
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

		return volist;
	}

	public int countProduct(Connection conn, int pageCateId) {
		int result = 0;
		String sql = "select count(*) cnt from (select p_no, category_id, p_content, p_name, p_brand, p_price, product_img_name "
				+ "from (select rownum r, t1.* from (select p1.* from product p1";
				
				if (pageCateId > 0) {
					sql += " where category_id=?";
				}
				sql += " order by p_no desc) t1) join product_img using (p_no))";

		try {
			
			pstmt = conn.prepareStatement(sql);
			if(pageCateId == 0) {
				System.out.println("전체상품");
			} else {
				pstmt.setInt(1, pageCateId);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
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

	public ProductVo selectProduct(Connection conn, int pNo) {
		ProductVo vo = null;
		ArrayList<ProductDetailVo> pdvolist = null;
		String sql1 = "select p_no, p_content, p_name, p_brand, p_price, product_img_name from product join product_img using(p_no) where p_no=?";
		String sql2 = "select p_seq, opt_no, opt_name, opt_val,opt_price from product_detail join option_parent using(opt_no) where p_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			vo = new ProductVo();
			
			if(rs.next()) {
				
				vo.setpNo(rs.getInt("p_no"));
				vo.setpContent(rs.getString("p_content"));
				vo.setpName(rs.getString("p_name"));
				vo.setpBrand(rs.getString("p_brand"));
				vo.setpPrice(rs.getInt("p_price"));
				vo.setProductImgName(rs.getString("product_img_name"));
				
				close(rs);
				close(pstmt);
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, pNo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pdvolist = new ArrayList<ProductDetailVo>();
					
					do {
						ProductDetailVo pdvo = new ProductDetailVo();
						pdvo.setpSeq(rs.getInt("p_seq"));
						pdvo.setOptNo(rs.getInt("opt_no"));
						pdvo.setOptName(rs.getString("opt_name"));
						pdvo.setOptPrice(rs.getInt("opt_price"));
						pdvo.setOptVal(rs.getString("opt_val"));
						pdvolist.add(pdvo);
					}while(rs.next());
					vo.setPdvo(pdvolist);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
		
		
	}
	
	public ArrayList<StoryBoardVo> listStoryBoard(Connection conn, int pNo) {
		ArrayList<StoryBoardVo> volist = null;
		String sql = "select * from story where p_no=? order by b_no desc, b_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
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
	

	


}
