package kh.semi.tomorrow.storyboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.product.model.service.ProductService;
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class WriteFileStoryBoardServlet
 */
@WebServlet("/storyenroll")
public class WriteFileStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteFileStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet - storyenroll");
		
		ArrayList<ProductVo> volist = new ProductService().selectAllProduct();
		System.out.println(volist);
		request.setAttribute("listProduct", volist);
		MemberVo ssvo = (MemberVo)request.getSession().getAttribute("ssMV");
		if(ssvo == null) {	// 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
		} else {	// 로그인한 상태라면 write page 진입
			request.getRequestDispatcher("WEB-INF/view/board/storyboardWriteFile.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
