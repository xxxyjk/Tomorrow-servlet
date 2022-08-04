package kh.semi.tomorrow.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.member.model.service.MemberService;
import kh.semi.tomorrow.member.model.vo.MemberVo;

/**
 * Servlet implementation class MemberDeleteDoServlet
 */
@WebServlet("/DeleteDo")
public class MemberDeleteDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberDeleteDoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 상태 확인
		MemberVo ssvo = (MemberVo) request.getSession().getAttribute("ssMV");
		String mId = "";
		if (ssvo == null) { // 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
			return; // DB저장 안하고 밖으로 빠지게
		} else { // 로그인한 상태라면 write page 진입
			mId = ssvo.getmId();
		}

		int result = new MemberService().deleteAccount(mId);
		if (result > 0) {
			System.out.println("탈퇴되었습니다");
			response.sendRedirect("main");	
		}

	}
}
