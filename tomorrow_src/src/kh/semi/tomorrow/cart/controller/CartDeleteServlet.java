package kh.semi.tomorrow.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.admin.model.service.AdminService;
import kh.semi.tomorrow.cart.model.service.CartService;
import kh.semi.tomorrow.member.model.vo.MemberVo;


@WebServlet("/cartdel")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CartDeleteServlet() {
        super();
 
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 상태 확인
		MemberVo ssvo = (MemberVo) request.getSession().getAttribute("ssMV");
		String mId = "";
		if (ssvo == null) { // 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
			return; // DB저장 안하고 밖으로 빠지게
		} else { // 로그인한 상태라면 mId 가져오기
			mId = ssvo.getmId();
		}
		
		System.out.println("장바구니에서 상품을 삭제해보자");
		int result = 0;				
		String[] numbers = request.getParameterValues("selectPno");
		if(numbers == null) {
			request.setAttribute("msg", "삭제할 상품번호를 선택해주세요.");
			request.getRequestDispatcher("WEB-INF/view/orderpage/msg.jsp").forward(request, response);
		}
		
		int[] cNo = new int[numbers.length];
		

		for(int i=0; i<numbers.length; i++) {
			cNo[i] = Integer.parseInt(numbers[i]);
		}
		result = new CartService().cartDelete(cNo, mId);		
		
		if(result < 1) {
			System.out.println("상품을 삭제하는데 실패했습니다.");
			request.setAttribute("msg", "상품을 삭제하는데 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/view/orderpage/msg.jsp").forward(request, response);
		} else {
			System.out.println("상품을 삭제했습니다.");
			request.setAttribute("msg", "상품을 삭제했습니다.");
			request.getRequestDispatcher("WEB-INF/view/orderpage/msg.jsp").forward(request, response);
		}
	}

}