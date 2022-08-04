package kh.semi.tomorrow.admin.model.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import kh.semi.tomorrow.admin.model.vo.MemberOrderListVo;
import kh.semi.tomorrow.common.JdbcTemp;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.order.model.vo.OrderVo;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

public class AdminDao {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 1. 상품 관리
	public ArrayList<ProductVo> ctgryProduct(Connection conn, String ctgry) {
		ArrayList<ProductVo> productList = null;
		String sql = "select C.CATEGORY_ID, C.CATEGORY_NAME, P.P_NO,"
				+ "	P.P_BRAND, P.P_NAME, P.P_CONTENT, P.P_PRICE"
				+ "	from PRODUCT P JOIN PRODUCT_CATEGORY C"
				+ "	ON P.CATEGORY_ID = C.CATEGORY_ID"
				+ "	WHERE C.CATEGORY_NAME = ? "
				+ " order by p_no desc "; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ctgry);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductVo>();
			while(rs.next()) {
				ProductVo product = new ProductVo();
				product.setCateId(rs.getInt("CATEGORY_ID"));
				product.setCateName(rs.getString("CATEGORY_NAME"));
				product.setpNo(rs.getInt("P_NO"));
				product.setpBrand(rs.getString("P_BRAND"));
				product.setpName(rs.getString("P_NAME"));
				product.setpContent(rs.getString("P_CONTENT"));
				product.setpPrice(rs.getInt("P_PRICE"));
				
				productList.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		} 
		
		if(productList == null) {
			System.out.println("AdminDao-ctgryProduct()의 상품 목록 조회에 실패하였습니다.\n[productlist]\n" + productList + "\n");
		} else {
			System.out.println("AdminDao-ctgryProduct()에 의해 상품 목록이 조회되었습니다.\n[productlist]\n" + productList + "\n");		
		}
		
		return productList;
	}
	
	public ArrayList<ProductVo> ctgryProduct(Connection conn, String ctgry, int startNum, int endNum) {
		ArrayList<ProductVo> productList = null;
		String sql = "select * "
				+ "    from( select rownum r, t1.* "
				+ "        from (select C.CATEGORY_ID, C.CATEGORY_NAME, P.P_NO,"
				+ "					P.P_BRAND, P.P_NAME, p_content, P.P_PRICE"
				+ "					from PRODUCT P JOIN PRODUCT_CATEGORY C"
				+ "					ON P.CATEGORY_ID = C.CATEGORY_ID"
				+ "					WHERE C.CATEGORY_NAME = ? "
				+ "					order by p_no desc			"
				+ "        ) t1 "
				+ "    )"
				+ "    where r between ? and ?";
		String sql_img = "select * from product_img where p_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ctgry);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductVo>();
			while(rs.next()) {
				ProductVo product = new ProductVo();
				product.setCateId(rs.getInt("CATEGORY_ID"));
				product.setCateName(rs.getString("CATEGORY_NAME"));
				product.setpNo(rs.getInt("P_NO"));
				product.setpBrand(rs.getString("P_BRAND"));
				product.setpName(rs.getString("P_NAME"));
//				product.setProductImgName(rs.getString("product_Img_Name"));
				product.setpPrice(rs.getInt("P_PRICE"));
				
				productList.add(product);
			}
			System.out.println("\n[Product_Img]\n");
			for(ProductVo pvo : productList) {
				JdbcTemp.close(rs);
				JdbcTemp.close(pstmt);
				System.out.println("제품dao pvo :"+ pvo);
				pstmt = conn.prepareStatement(sql_img);
				pstmt.setInt(1, pvo.getpNo());			
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pvo.setProductImgName(rs.getString("product_Img_Name"));
					//pivo.setProductImgSize(rs.getInt("product_Img_Size"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		} 
		
		if(productList == null) {
			System.out.println("AdminDao-ctgryProduct()의 상품 목록 조회에 실패하였습니다.\n[productlist]\t" + productList + "\n");
		} else {
			System.out.println("AdminDao-ctgryProduct()에 의해 상품 목록이 조회되었습니다.\n[productlist]\t" + productList + "\n");		
		}
		return productList;
	}
	
	public ArrayList<ProductVo> seachAllProduct(Connection conn, int startNum, int endNum) {
		ArrayList<ProductVo> productList = null;
		String sql = "select * "
				+ "    from( select rownum r, t1.* "
				+ "        from (select i.PRODUCT_IMG_NAME, C.CATEGORY_ID, C.CATEGORY_NAME, P.P_NO,"
				+ "   		P.P_BRAND, P.P_NAME, P.P_CONTENT, P.P_PRICE"
				+ "		    from PRODUCT P JOIN PRODUCT_CATEGORY C ON P.CATEGORY_ID = C.CATEGORY_ID"
				+ "    		join product_img i on i.p_no = p.p_no"
				+ "    		order by p_no desc "
				+ "        ) t1 "
				+ "    )"
				+ "    where r between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			
			productList = new ArrayList<ProductVo>();				
			while(rs.next()) {
				ProductVo product = new ProductVo();
				
				product.setProductImgName(rs.getString("PRODUCT_IMG_NAME"));
				product.setCateId(rs.getInt("CATEGORY_ID"));
				product.setCateName(rs.getString("CATEGORY_NAME"));
				product.setpNo(rs.getInt("P_NO"));
				product.setpBrand(rs.getString("P_BRAND"));
				product.setpName(rs.getString("P_NAME"));
				product.setpContent(rs.getString("P_CONTENT"));
				product.setpPrice(rs.getInt("P_PRICE"));
				
				productList.add(product);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		
		if(productList == null) {
			System.out.println("AdminDao-seachAllProduct()의 상품 목록 조회에 실패하였습니다.\n[productlist]\t" + productList + "\n");
		} else {
			System.out.println("AdminDao-seachAllProduct()에 의해 상품 목록이 조회되었습니다.\n[productlist]\t" + productList + "\n");		
		}
		
		return productList;
	}
	
	public ArrayList<ProductVo> seachAllProduct(Connection conn, int startNum, int endNum, int pageCateId) {
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
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				pstmt.setInt(1, pageCateId);
			} else {
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
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
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		
		if(volist == null) {
			System.out.println("AdminDao-seachAllProduct()의 상품 목록 조회에 실패하였습니다.\n[productlist]\t" + volist + "\n");
		} else {
			System.out.println("AdminDao-seachAllProduct()에 의해 상품 목록이 조회되었습니다.\n[productlist]\t" + volist + "\n");		
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
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}

		return result;
	}
	
	public int countAllProduct(Connection conn) {
		int result= 0;
		String sql = "select count(*)"
				+ "                from PRODUCT P JOIN PRODUCT_CATEGORY C"
				+ "				ON P.CATEGORY_ID = C.CATEGORY_ID";
		
		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("AdminDao-countAllProduct()에 의한 수행이 실패했습니다.\nresult:\t" + result + "\n");
		} else {
			System.out.println("AdminDao-countAllProduct()에 의해 1행이 수행되었습니다.\nresult:\t" + result + "\n");
		}
		return result;
	}
	
	// 상품 등록	
	// 상품 등록시 필요한 상품번호 구하기
	public int insertProduct(Connection conn, ProductVo product, int pNo) {
		int result = 0;
		String sql = "insert into product(P_NO, P_BRAND, P_NAME, P_CONTENT, P_PRICE, CATEGORY_ID) "
				+ " values(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, product.getpBrand());
			pstmt.setString(3, product.getpName());
			pstmt.setString(4, product.getpContent());
			pstmt.setInt(5, product.getpPrice());
			pstmt.setInt(6, product.getCateId());
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("AdminDao-insertProduct()에 의한 상품 등록에 실패하였습니다.\n");
		} else {
			System.out.println("AdminDao-insertProduct()에 의해 상품 등록에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;
	}	 
	
	public int insertProductDetail(Connection conn, ProductDetailVo detail, int pNo) {
		int result = 0;
		String sql = "insert into product_detail(P_SEQ, P_NO, OPT_NO, OPT_VAL, OPT_PRICE) "
				+ "values (SEQUENCE_PROD_DETAIL_P_SEQ.nextval, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, pNo);
			pstmt.setInt(2, detail.getOptNo());
			pstmt.setString(3, detail.getOptVal());
			pstmt.setInt(4, detail.getOptPrice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("AdminDao-insertProductDetail()에 의한 상품 옵션 등록에 실패하였습니다.\n[결과]\t"+ result + "\n");
		} else {
			System.out.println("AdminDao-insertProductDetail()에 의해 상품 옵션 등록에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;	
	}
	
	public int insertProductImg(Connection conn, ProductVo vo, int pNo) {
		int result = 0;
		String sql = "insert into product_img(PRODUCT_IMG_NO, P_NO, PRODUCT_IMG_NAME, PRODUCT_IMG_SIZE)"
				+ " values(SEQUENCE_PRODUCT_IMG_NO.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pNo);
			pstmt.setString(2, vo.getProductImgName());
			pstmt.setInt(3, vo.getProductImgSize());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		if(result == 0) {
			System.out.println("AdminDao-insertProductContent()에 의한 상품 이미지 등록에 실패하였습니다.\n[결과]\t" + result + "\n");
		} else {
			System.out.println("AdminDao-insertProductContent()에 의한 상품 이미지 등록에 성공했습니다.\n[결과]\t" + result + "\n");
		}
		return result;
	}
	
	public int getProductPNo(Connection conn) {
		int result = 0;
		String sql = "select SEQUENCE_PRODUCT_P_NO.nextval from dual";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		System.out.println("getProductPNo:\t "+ result + "\n");
		return result;
	}
	
	// 상품 및 상품 상세 조회
	public ProductVo searchProduct(Connection conn, int pNo, int pSeq) {
		ProductVo product = null;
		ProductDetailVo detail = null;
		String sql1 = "select p_no, p_brand, p_name, p_content, p_price, category_id"
					+ " from product where p_no = ?" ;
		String sql2 = "select d.p_no, d.p_seq, d.opt_no, op.opt_name, d.opt_val, d.opt_price"
				+ " from product_detail d join option_parent op on d.OPT_NO= op.OPT_NO "
				+ " where p_no= ? and p_seq = ?";		
		String sql3 = "select product_img_name, product_img_size from product_img where p_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = new ProductVo();
				
				product.setpNo(rs.getInt("p_no"));
				product.setpBrand(rs.getString("p_brand"));
				product.setpName(rs.getString("p_name"));
				product.setpContent(rs.getString("p_content"));
				product.setpPrice(rs.getInt("p_price"));
				product.setCateId(rs.getInt("category_id"));
				JdbcTemp.close(rs);
				JdbcTemp.close(pstmt);
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, pNo);
				pstmt.setInt(2, pSeq);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					detail = new ProductDetailVo();				
					
					detail.setpNo(rs.getInt("p_no"));
					detail.setpSeq(rs.getInt("p_seq"));					
					detail.setOptNo(rs.getInt("opt_no"));
					detail.setOptName(rs.getString("opt_name"));
					detail.setOptVal(rs.getString("opt_val"));
					detail.setOptPrice(rs.getInt("opt_price"));
					
					product.setPdt(detail);
				}

				JdbcTemp.close(rs);
				JdbcTemp.close(pstmt);
				
				pstmt = conn.prepareStatement(sql3);		
				pstmt.setInt(1, pNo);
				rs = pstmt.executeQuery();
							
				if(rs.next()) {
					product.setProductImgName(rs.getString("product_img_name"));
					product.setProductImgSize(rs.getInt("product_img_size"));	
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		if(product == null) {
			System.out.println("AdminDao-searchProduct()에 의해 상품 조회가 실패했습니다.\nproduct:\t" + product + "\n");
		} else {
			System.out.println("AdaminDao-searchProduct()에 의해 상품을 조회합니다.\nproduct:\n" + product + "\n");
		}
		return product;
	}		
	
	// 상품 수정/삭제
	
	
	
	public int updateProduct(Connection conn, ProductVo product, int pNo) {
		int result= 0;
		String sql = "update product set CATEGORY_ID =?, P_BRAND=?, P_NAME=?, P_PRICE=? where p_no= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product.getCateId());
			pstmt.setString(2, product.getpBrand());
			pstmt.setString(3, product.getpName());
			pstmt.setInt(4, product.getpPrice());
			pstmt.setInt(5, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == 0) {
			System.out.println("AdminDao-updateProduct()에 의한 상품 업데이트에 실패하였습니다.\n[결과]\t"+ result + "\n");
		} else {
			System.out.println("AdminDao-updateProduct()에 의해 상품 업데이트에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;	
	}
	
	public int updateProductDetail(Connection conn, ProductDetailVo detail, int pNo, int pSeq) {
		int result= 0;
		String sql = "update product_detail set opt_no=?, opt_val=?, opt_price=? where p_no=? and p_seq=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, detail.getOptNo());
			pstmt.setString(2, detail.getOptVal());
			pstmt.setInt(3, detail.getOptPrice());
			pstmt.setInt(4, pNo);
			pstmt.setInt(5, pSeq);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("AdminDao-updateProductDetail()에 의한 상품 상세옵션의 업데이트에 실패하였습니다.\n[결과]\t"+ result + "\n");
		} else {
			System.out.println("AdminDao-updateProductDetail()에 의해 상품 상세옵션의 업데이트에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;	
	}
	
	public int updateProductImage(Connection conn, ProductVo vo, int pNo) {
		int result = 0;
		String sql = "update product_img set product_img_name = ? where p_no = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getProductImgName());
			pstmt.setInt(2, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == 0) {
			System.out.println("AdminDao-updateProductImage()에 의한 상품 대표 이미지 변경에 실패하였습니다.\n[결과]\t"+ result + "\n");
		} else {
			System.out.println("AdminDao-updateProductImage()에 의해 상품 대표 이미지 변경에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;
	}
	
	public int updateProductContent(Connection conn, ProductVo vo) {
		int result = 0;
		String sql="update product set p_content=? where p_no = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, vo.getpContent());
			pstmt.setInt(2, vo.getpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		if(result == 0) {
			System.out.println("AdminDao-updateProductContent()에 의한 상품 정보 이미지 수정에 실패하였습니다.\n[결과]\t"+ result + "\n");
		} else {
			System.out.println("AdminDao-updateProductContent()에 의한 상품 정보 이미지 수정에 성공했습니다.\n[결과]\t"+ result + "\n");
		}
		
		return result;		
	}
	
	public int deleteProduct(Connection conn, int pNo) {
		int result = 0;
		String sql = "delete from product where p_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == 0) {
			System.out.println("AdminDao-deleteProduct()에 의한 상품 삭제에 실패하였습니다.\n");
		} else {
			System.out.println("AdminDao-deleteProduct()에 의해 상품 삭제에 성공했습니다.\n[결과]\t" + result + "\n");		
		}
		return result;
	}
	
	// 2. 주문 내역 조회	
	public ArrayList<MemberOrderListVo> selectOrderList(Connection conn) {
		ArrayList<MemberOrderListVo> orderlist = null;		
		String sql = "select i.product_img_name, p.p_brand, p.p_name, o.o_no, od.O_detail_cnt"
				+ "    	o.o_date, o.O_TOTAL_PRICE, o.O_NAME"
				+ "     	from orders o join order_detail od on o.O_NO= od.O_NO"
				+ "         join product p on p.p_no = od.P_NO"
				+ "         join product_img i on i.p_no = p.p_no"
				+ "         order by o.o_no desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			orderlist = new ArrayList<MemberOrderListVo>();
			
			while(rs.next()) {
				MemberOrderListVo vo = new MemberOrderListVo();
				
				vo.setProductImgName(rs.getString("product_img_name"));
				vo.setpBrand(rs.getString("p_brand"));
				vo.setpName(rs.getString("p_name"));
				vo.setoNo(rs.getInt("o_no"));
				vo.setoDcnt(rs.getInt("o_detail_cnt"));
				vo.setoDate(rs.getTimestamp("o_date"));
				vo.setoTotalPrice(rs.getInt("o_total_price"));
				vo.setoName(rs.getString("o_name"));
				
				orderlist.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(orderlist == null) {
			System.out.println("AdminDao-selectOrderList()의 회원 목록 조회에 실패하였습니다.\n");
		} else {
			System.out.println("AdaminDao-selectOrderList()에 의해 목록이 조회되었습니다.\n[orderlist]\n" + orderlist + "\n");		
		}		
		return orderlist;
	}
	
	public ArrayList<MemberOrderListVo> selectOrderList(Connection conn, int startNum, int endNum) {
		ArrayList<MemberOrderListVo> orderlist = null;
		String sql = "select * "
				+ "    from( select rownum r, t1.* "
				+ "        from (select i.product_img_name, p.p_brand, p.p_name, o.o_no, od.O_detail_cnt,"
				+ "                o.o_date, o.O_TOTAL_PRICE, o.O_NAME "
				+ "                from orders o join order_detail od on o.O_NO= od.O_NO"
				+ "                join product p on p.p_no = od.P_NO"
				+ "                join product_img i on i.p_no = p.p_no"
				+ "                order by o.o_no desc"
				+ "        ) t1 "
				+ "    )"
				+ "    where r between ? and ?";	
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			orderlist = new ArrayList<MemberOrderListVo>();
			
			while(rs.next()) {
				MemberOrderListVo vo = new MemberOrderListVo();
				
				vo.setProductImgName(rs.getString("product_img_name"));
				vo.setpBrand(rs.getString("p_brand"));
				vo.setpName(rs.getString("p_name"));
				vo.setoNo(rs.getInt("o_no"));
				vo.setoDcnt(rs.getInt("o_detail_cnt"));
				vo.setoDate(rs.getTimestamp("o_date"));
				vo.setoTotalPrice(rs.getInt("o_total_price"));
				vo.setoName(rs.getString("o_name"));
				
				orderlist.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(orderlist == null) {
			System.out.println("AdminDao-selectOrderList()의 회원 목록 조회에 실패하였습니다.\n");
		} else {
			System.out.println("AdaminDao-selectOrderList()에 의해 목록이 조회되었습니다.\n[orderlist]\n" + orderlist + "\n");		
		}		
		return orderlist;
	}
	
	public int countOrderList(Connection conn) {
		int result = 0;		
		String sql = "select count(*) from orders o join order_detail od using(o_no) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(result == 0) {
			System.out.println("AdminDao-countOrderList()에 의한 수행이 실패하였습니다.");
		} else {
			System.out.println("AdminDao-countOrderList()에 의해 1행이 수행되었습니다.\n");
		}
		return result;
	}
	
	// 3. 회원 관리
	public ArrayList<MemberVo> selectAllMember(Connection conn) {
		ArrayList<MemberVo> memberlist = null;
		String sql = "select M_ID, M_NAME, M_NICKNAME, "
				+ " M_BIRTH, M_PHONE, M_DATE, M_NY from member "
				+ " where m_name != '관리자' ";		

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberlist = new ArrayList<MemberVo>();
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				
				vo.setmId(rs.getString("M_ID"));
				vo.setmName(rs.getString("M_NAME"));
				vo.setmNickname(rs.getString("M_NICKNAME"));
				vo.setmBrith(rs.getString("M_BIRTH"));
				vo.setmPhone(rs.getString("M_PHONE"));
				vo.setmDate(rs.getTimestamp("M_DATE"));
				vo.setmNy(rs.getString("M_NY"));
				
				memberlist.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		if(memberlist == null) {
			System.out.println("AdminDao-selectAllMember()의 회원 목록 조회에 실패하였습니다.\n");
		} else {
			System.out.println("AdaminDao-selectAllMember()에 의해 목록이 조회되었습니다.\n[boardlist]\t" + memberlist + "\n");		
		}
		return memberlist;
	}
	
	public ArrayList<MemberVo> selectAllMember(Connection conn, int startNum, int endNum) {
		ArrayList<MemberVo> memberlist = null;				
		String sql = "select *"
				+ "    from (select rownum r, t1.*"
				+ "            from (select s1.m_id, s1.m_name, s1.m_nickname, s1.m_birth, s1.m_phone, s1.m_date, s1.m_ny"
				+ "                    from member s1 where s1.m_name != '관리자' ) t1"
				+ "    		)"
				+ "	where r between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);			
			rs = pstmt.executeQuery();			
			
			memberlist = new ArrayList<MemberVo>();
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				
				vo.setmId(rs.getString("M_ID"));
				vo.setmName(rs.getString("M_NAME"));
				vo.setmNickname(rs.getString("M_NICKNAME"));
				vo.setmBrith(rs.getString("M_BIRTH"));
				vo.setmPhone(rs.getString("M_PHONE"));
				vo.setmDate(rs.getTimestamp("M_DATE"));
				vo.setmNy(rs.getString("M_NY"));
				
				memberlist.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		if(memberlist == null) {
			System.out.println("AdminDao-selectAllMember()의 회원 목록 조회에 실패하였습니다.\n");
		} else {
			System.out.println("AdaminDao-boardList()에 의해 목록이 조회되었습니다.\n[memberlist]\t" + memberlist + "\n");		
		}
		return memberlist;
	}	
	
	public ArrayList<MemberVo> leaveMember(Connection conn) {
		ArrayList<MemberVo> memberlist = null;
		String sql = "select M_ID, M_NAME, M_NICKNAME, "
				+ " M_BIRTH, M_PHONE, M_DATE from member "
				+ " where M_NY = 'Y';";		

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberlist = new ArrayList<MemberVo>();
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				
				vo.setmId(rs.getString("M_ID"));
				vo.setmName(rs.getString("M_NAME"));
				vo.setmNickname(rs.getString("M_NICKNAME"));
				vo.setmBrith(rs.getString("M_BIRTH"));
				vo.setmPhone(rs.getString("M_PHONE"));
				vo.setmDate(rs.getTimestamp("M_DATE"));
				vo.setmNy(rs.getString("M_NY"));
				
				memberlist.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		System.out.println("AdminDao-leaveMember()에 의해 목록이 조회되었습니다.\n[leaveMember]\t"+ memberlist + "\n");
		return memberlist;
	}
	
	public int updateWithDraw(Connection conn, String mId) {
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
		if(result < 1) {
			System.out.println("AdaminDao-updateWithDraw()의 실행에 실패했습니다.\n");
		} else {
			System.out.println("AdaminDao-updateWithDraw()에 의해 1행이 업데이트 되었습니다.\n");			
		}
		System.out.println("aaa");
		return result;
	}
	
	public int deleteMember(Connection conn, String mId) {
		int result = 0;
		String sql = "delete from member where m_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		if(result < 1) {
			System.out.println("AdaminDao-deleteMember()의 실행이 실패했습니다.\n");
		} else {
			System.out.println("AdminDao-deleteMember()에 의해 1행이 삭제되었습니다.\n");			
		} 
		return result;
	}
	
	public int countMember(Connection conn) {
		int result = 0;
		String sql = "select count(*)-1 from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			} 
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(result < 1) {
			System.out.println("AdaminDao-countMember()의 실행이 실패했습니다.\n");
		} else {
			System.out.println("AdaminDao-countMember()로 인해 1행이 수행되었습니다.\n");			
		} 
		return result;
	}
	
	// 4. 게시물 관리
	public ArrayList<StoryBoardVo> boardList(Connection conn) {
		ArrayList<StoryBoardVo> boardlist = null;
		String sql = "select b_no, b_title, b_writer, b_date, b_cnt from story";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boardlist = new ArrayList<StoryBoardVo>();
			
			while(rs.next()) {
				StoryBoardVo vo = new StoryBoardVo();
				vo.setbNo(rs.getInt("b_no"));
				vo.setbTitle(rs.getString("b_title"));
				vo.setbWriter(rs.getString("b_writer"));
				vo.setbDate(rs.getTimestamp("b_date"));
				vo.setbCnt(rs.getInt("b_cnt"));
				
				boardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(boardlist == null) {
			System.out.println("AdaminDao-boardList()의 게시글 목록 조회에 실패하였습니다.\n[boardlist]\n" + boardlist + "\n");
		} else {
			System.out.println("AdaminDao-boardList()로 인해 목록이 조회되었습니다.\n[boardlist]\t" + boardlist + "\n");		
		}		
		return boardlist;
	}
	
	public ArrayList<StoryBoardVo> boardList(Connection conn, int startNum, int endNum) {
		ArrayList<StoryBoardVo> boardlist = null;
		String sql = "select *"
				+ "    from (select rownum r, t1.*"
				+ "            from (select s1.b_no, s1.b_title, s1.b_writer, s1.b_date, s1.b_cnt"
				+ "                    from story s1 order by b_date desc, b_no desc) t1"
				+ "    		)"
				+ "	where r between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<StoryBoardVo>();
			
			while(rs.next()) {
				StoryBoardVo vo = new StoryBoardVo();
				vo.setbNo(rs.getInt("b_no"));
				vo.setbTitle(rs.getString("b_title"));
				vo.setbWriter(rs.getString("b_writer"));
				vo.setbDate(rs.getTimestamp("b_date"));
				vo.setbCnt(rs.getInt("b_cnt"));
				
				boardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(rs);
			JdbcTemp.close(pstmt);
		}
		
		if(boardlist == null) {
			System.out.println("AdaminDao-boardList()의 게시글 목록 조회에 실패하였습니다.\n[boardlist]\n" + boardlist + "\n");
		} else {
			System.out.println("AdaminDao-boardList()로 인해 목록이 조회되었습니다.\n[boardlist]\t" + boardlist + "\n");		
		}		
		return boardlist;
	}
	
	public int countBoard(Connection conn) {
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
			System.out.println("AdminDao-countBoard()의 게시글 개수 조회에 실패하였습니다.\n");
		} else {
			System.out.println("AdminDao-countBoard()의 게시글 개수 조회를 수행합니다. \n[bresult]\t" + result + "\n");
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, int bNo) {
		int result= 0;
		String sql = "delete from story where b_no= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemp.close(pstmt);
		}
		
		if(result < 1) {
			System.out.println("AdaminDao-deleteBoard()의 실행에 실패했습니다.\n");
		} else {
			System.out.println("AdaminDao-deleteBoard()에 의해 1행이 삭제되었습니다.\n");			
		} 
		return result;
	}
}
