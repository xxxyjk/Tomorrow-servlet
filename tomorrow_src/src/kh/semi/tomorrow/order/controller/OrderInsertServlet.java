package kh.semi.tomorrow.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.cart.model.service.CartService;
import kh.semi.tomorrow.cart.model.vo.CartVo;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.product.model.service.ProductService;
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class OrderInsertServlet
 */
@WebServlet("/orderEnroll")
public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/orderEnroll post 진입");

		// 로그인 상태 확인
		MemberVo ssvo = (MemberVo) request.getSession().getAttribute("ssMV");
		String mId = "";
		if (ssvo == null) { // 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
			return;
		} else { // 로그인한 상태라면 write page 진입
			mId = ssvo.getmId();
		}

		String option1 = request.getParameter("option_1");
		String option2 = request.getParameter("option_2");
		String option3 = request.getParameter("option_3");
		String option4 = request.getParameter("option_4");
		System.out.println("option1 :"+option1);
		System.out.println("option2 :"+option2);
		System.out.println("option3 :"+option3);
		System.out.println("option4 :"+option4);
		
		if(option2 != null && option2.equals("")) {
			option1 += ", "+option2;
		}
		if(option3 != null && option3.equals("")) {
			option1 += ", "+option3;
		}
		if(option4 != null && option4.equals("")) {
			option1 += ", "+option4;
		}
		System.out.println(option1);

		String pNoStr = request.getParameter("pNo");
		System.out.println(pNoStr);
		int pNo = 0;
		try {
			pNo = Integer.parseInt(pNoStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (pNo < 1) {
			return;
		} else {

//			ProductVo result = new ProductService().selectProduct(pNo);
//			System.out.println(result);
//			request.setAttribute("selectProduct", result);

			int result = new CartService().insertmyCart(mId, pNo, option1);

			ArrayList<CartVo> cartVoList = new CartService().myCart(mId);
			request.setAttribute("cartVoList", cartVoList);
			request.getRequestDispatcher("WEB-INF/view/orderpage/orderpage.jsp").forward(request, response);
		}
	}
}
