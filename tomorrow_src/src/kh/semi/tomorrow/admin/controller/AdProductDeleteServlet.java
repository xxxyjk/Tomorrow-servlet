package kh.semi.tomorrow.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.admin.model.service.AdminService;

/**
 * Servlet implementation class AdProductDeleteServlet
 */
@WebServlet("/adProductDel")
public class AdProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdProductDeleteServlet - doPost\n");
		int result = 0;				
		String[] numbers = request.getParameterValues("selectPno");
		if(numbers == null) {
			System.out.println("상품번호가 선택되지 않았습니다. 다시 입력해주세요");
			request.setAttribute("msg", "상품번호가 선택되지 않았습니다. 다시 입력해주세요");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
		
		int[] pNos = new int[numbers.length];
		
		for(int i=0; i<numbers.length; i++) {
			System.out.println("선택한 글번호: " + numbers[i]);
		}
		
		for(int i=0; i<numbers.length; i++) {
			pNos[i] = Integer.parseInt(numbers[i]);
			System.out.println("선택한 pNos[i]: " + pNos[i]);
		}
		result = new AdminService().deleteProduct(pNos);		
		
		if(result < 1) {
			System.out.println("상품을 삭제하는데 실패했습니다.");
			request.setAttribute("msg", "상품을 삭제하는데 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		} else {
			System.out.println("관리자가 상품을 삭제했습니다.");
			request.setAttribute("msg", "관리자가 상품을 삭제했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
	}

}
