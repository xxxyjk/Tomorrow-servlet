package kh.semi.tomorrow.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.admin.model.service.AdminService;

/**
 * Servlet implementation class AdStoryBoardDeleteServlet
 */
@WebServlet("/adAritcleDelete")
public class AdStoryBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdStoryBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdStoryBoardDeleteServlet - doGet");
		int result = 0;				
		String[] numbers = request.getParameterValues("checkBoard");
		if(numbers == null) {
			System.out.println("게시물을 선택하지 않았습니다.");
			request.setAttribute("msg", "게시물을 선택하지 않았습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);			
		}
		int[] bNos = new int[numbers.length];
		
		for(int i=0; i<numbers.length; i++) {
			System.out.println("선택한 글번호: " + numbers[i]);
		}
		
		for(int i=0; i<numbers.length; i++) {
			bNos[i] = Integer.parseInt(numbers[i]);
			System.out.println("bNos[i]: " + bNos[i]);
		}
		result = new AdminService().deleteBoard(bNos);		
		if(result < 1) {
			System.out.println("게시글을 삭제하는데 실패했습니다.");
			request.setAttribute("msg", "게시글을 삭제하는데 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		} else {
			System.out.println("관리자가 게시글을 삭제했습니다.");
			request.setAttribute("msg", "관리자가 게시글을 삭제했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
	
	}

}
