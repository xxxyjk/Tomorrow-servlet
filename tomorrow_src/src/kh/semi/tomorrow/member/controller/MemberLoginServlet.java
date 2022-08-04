package kh.semi.tomorrow.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.semi.tomorrow.member.model.service.MemberService;
import kh.semi.tomorrow.member.model.vo.MemberVo;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MemberLoginServlet - doGet");
		System.out.println("로그인 페이지로 이동합니다.\n");
		request.getRequestDispatcher("WEB-INF/view/member/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mId = request.getParameter("id");
		String pw = request.getParameter("pwd");

		System.out.println("MemberLoginServlet-> login");
		System.out.println("id: " + mId);
		System.out.println("pw: " + pw + "\n");

		MemberVo vo = new MemberService().login(mId, pw);
		if (vo == null) {
			System.out.println("로그인에 실패했습니다.\n");
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.getRequestDispatcher("WEB-INF/view/member/confirm/msg.jsp").forward(request, response);
		} else if (vo != null && vo.getmId().equals("admin001") && vo.getmPw().equals("admin001")) {
			System.out.println("로그인에 성공하였습니다.");
			System.out.println("*** 관리자님이 로그인하였습니다. ***\n");
			request.getSession().setAttribute("ssMV", vo);
			response.sendRedirect(request.getContextPath() + "/storylist");
		} else {
			System.out.println("로그인에 성공하였습니다.\n");
			request.getSession().setAttribute("ssMV", vo);
			System.out.println(vo.getmId());
			response.sendRedirect(request.getContextPath() + "/storylist");
		}
	}

}
