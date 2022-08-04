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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/view/memberinfo/update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet - doPost");
		String mId = request.getParameter("mId");
		String mNickname = request.getParameter("mNickname");
		String mBrith = request.getParameter("mBrith");
		String phone = request.getParameter("phone");
		String intro = request.getParameter("intro");
		
		
		System.out.println("=========================[회원정보]=======================");
		System.out.println("mId:\t\t" + mId);
		System.out.println("mNickname:\t" + mNickname);
		System.out.println("mBirth:\t\t" + mBrith);
		System.out.println("phone:\t\t" + phone);
		System.out.println("intro:\t\t" + intro);
		System.out.println("========================================================\n");
		
		MemberVo member = new MemberVo();
		member.setmId(mId);
		member.setmNickname(mNickname);
		member.setmBrith(mBrith);
		member.setmPhone(phone);
		member.setmIntro(intro);
		
		int result = 0;
		result = new MemberService().updateMember(member);
		System.out.println("MemberUpdateServlet result:\t\t" + result + "\n");
		if(result == 0) {
			System.out.println("회원 정보 수정에 실패하였습니다.");
			request.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/member/confirm/msg.jsp").forward(request, response);
		} else {
			System.out.println("회원 정보 수정을 성공하였습니다.");
			request.setAttribute("msg", "회원 정보 수정에 성공하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/member/confirm/msg.jsp").forward(request, response);
		}
		
	}

}
