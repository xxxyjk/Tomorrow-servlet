package kh.semi.tomorrow.cart.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.semi.tomorrow.cart.model.dao.CartDao;
import kh.semi.tomorrow.cart.model.vo.CartVo;
import kh.semi.tomorrow.common.JdbcTemp;

import static kh.semi.tomorrow.common.JdbcTemp.*;

public class CartService {
	private CartDao dao = new CartDao();

	public ArrayList<CartVo> myCart(String mId) {
		Connection conn = JdbcTemp.getConnection();
		ArrayList<CartVo> cartVo = dao.myCart(conn, mId);
		JdbcTemp.close(conn);
		return cartVo;
	}

	// 상품추가
	public int insertmyCart(String mId, int pNo, String option1) {
		Connection conn = JdbcTemp.getConnection();
		
		int cnt = dao.checkCntCart(conn, mId, pNo);
		int eql = dao.checkEqualCart(conn, mId, pNo, option1);
		
		int result = 0;
		if(cnt == 0)
			result = dao.insertmyCart(conn, mId, pNo, option1);
		else{
			if(eql == 0){
				result = dao.insertmyCart(conn, mId, pNo, option1);
			}else
				result = dao.updatemyCart(conn, mId, pNo, ++cnt, option1);
		}
			
		
		JdbcTemp.close(conn);
		return result;
	}

	// 상품 삭제
	public int cartDelete(int[] cNo, String mId) {
		int result = 0;
		Connection conn = JdbcTemp.getConnection();
		JdbcTemp.setAutocommit(conn, false);
		boolean bool = true;

		for (int i = 0; i < cNo.length; i++) {
			result = dao.cartDelete(conn, cNo[i], mId);
			if (result == 0) {
				bool = false;
				break;
			}
		}
		if (bool) {
			JdbcTemp.commit(conn);
		} else {
			JdbcTemp.rollback(conn);
		}

		JdbcTemp.close(conn);
		return result;
	}

}
