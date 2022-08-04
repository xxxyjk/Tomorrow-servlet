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
import kh.semi.tomorrow.member.model.service.MemberService;
import kh.semi.tomorrow.member.model.vo.MemberVo;

/**
 * Servlet implementation class OrderViewServlet
 */
@WebServlet("/orderpage")
public class OrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrderViewServlet() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 상태 확인
				MemberVo ssvo = (MemberVo)request.getSession().getAttribute("ssMV");
				String mId = "";
				if(ssvo == null) {	// 로그아웃 상태라면 login page 진입
					response.sendRedirect("login");
					return;	// DB저장 안하고 밖으로 빠지게
				} else {	// 로그인한 상태라면 write page 진입
					mId = ssvo.getmId();
				}
		
		//회원정보
		MemberVo memberVo = new MemberService().myName(mId);
		System.out.println("memberVo:"+ memberVo);
		request.setAttribute("memberVo", memberVo);
		
				
		//장바구니 목록
		ArrayList<CartVo> cartVoList = new CartService().myCart(mId);
		System.out.println("cartVoList:"+ cartVoList);
		request.setAttribute("cartVoList", cartVoList);
		request.getRequestDispatcher("WEB-INF/view/orderpage/orderpage.jsp").forward(request, response);

	}
}
