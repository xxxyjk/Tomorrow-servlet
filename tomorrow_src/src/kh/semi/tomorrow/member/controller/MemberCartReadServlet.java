package kh.semi.tomorrow.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.member.model.service.MemberService;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class MemberCartReadServlet
 */
@WebServlet("/myProduct")
public class MemberCartReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MemberCartReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("/memberCart doGet");
			
			// 로그인 상태 확인
			MemberVo ssvo = (MemberVo)request.getSession().getAttribute("ssMV");
			String mId = "";
			if(ssvo == null) {	// 로그아웃 상태라면 login page 진입
				response.sendRedirect("login");
				return;	// DB저장 안하고 밖으로 빠지게
			} else {	// 로그인한 상태라면 write page 진입
				mId = ssvo.getmId();
			}
			System.out.println("로그인한 mId:"+ mId);
			
			
			ArrayList<ProductVo> ProductVo = new MemberService().myProduct(mId);
			System.out.println(ProductVo+"productVo");
			request.setAttribute("ProductVo", ProductVo);
			request.getRequestDispatcher("WEB-INF/view/memberinfo/mypage.jsp").forward(request, response);
			
	}


}
