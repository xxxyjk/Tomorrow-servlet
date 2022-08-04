package kh.semi.tomorrow.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.admin.model.service.AdminService;

/**
 * Servlet implementation class AdMemberWithDrawServlet
 */
@WebServlet("/adMemberWithDraw")
public class AdMemberWithDrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMemberWithDrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdMemberWithDrawServlet - doPost");
		int result = 0;
		String[] mIds = request.getParameterValues("chk_box");
		if(mIds == null) {
			System.out.println("회원을 선택하지 않았습니다.");
			request.setAttribute("msg", "회원을 선택하지 않았습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);			
		}
		
		for(int i=0; i< mIds.length; i++) {
			System.out.println("선택 회원ID: " + mIds[i] + "\n");
		}
		result=new AdminService().updateWithDraw(mIds);
		
		if(result < 1) {
			System.out.println("회원 탈퇴 여부 변경에 실패하였습니다.");			
			request.setAttribute("msg", "회원 탈퇴 여부 변경에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		} else {
			System.out.println("회원 탈퇴 여부를 변경했습니다.");			
			request.setAttribute("msg", "회원 탈퇴 여부를 변경했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
	}

}
