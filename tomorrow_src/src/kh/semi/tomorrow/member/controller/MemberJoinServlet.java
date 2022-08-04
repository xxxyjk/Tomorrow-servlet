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
@WebServlet("/join")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberJoinServlet - doGet");		
		request.getRequestDispatcher("WEB-INF/view/member/join.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberJoinServlet - doPost");		
		
		String mId = request.getParameter("mId");
		String pwd = request.getParameter("password");
		String mName = request.getParameter("mNname");
		String mNickname = request.getParameter("mNickname");
		String mBrith = request.getParameter("mBirth");
		String phone = request.getParameter("phone");
		String intro = request.getParameter("intro");		
		
		System.out.println("** 회원가입 페이지에서 가져온 값 **");
		System.out.println("=========================[회원정보]=======================");
		System.out.println("mid:\t\t" + mId);
		System.out.println("pwd:\t\t" + pwd);
		System.out.println("mNickname:\t" + mNickname);
		System.out.println("mName:\t\t" + mName);
		System.out.println("mBirth:\t\t" + mBrith);
		System.out.println("phone:\t\t" + phone);
		System.out.println("intro:\t\t" + intro);
		System.out.println("========================================================\n");
		
		MemberVo member = new MemberVo();
		member.setmId(mId);
		member.setmPw(pwd);
		member.setmName(mName);
		member.setmNickname(mNickname);
		member.setmBrith(mBrith);
		member.setmPhone(phone);
		member.setmIntro(intro);		
		
		int result = 0;
		result = new MemberService().insertMember(member);
		System.out.println("MemberJoinServlet - result:\t" + result + "\n" );
		
		if(result == 0) {
			System.out.println("회원가입에 실패했습니다.");
			request.setAttribute("msg", "회원가입에 실패했습니다. 회원 가입 양식을 다시 확인해주세요.");
			request.getRequestDispatcher("WEB-INF/view/member/confirm/msg.jsp").forward(request, response);
		} else {
			System.out.println("회원가입에 성공했습니다.");
			request.setAttribute("msg", "회원가입에 성공했습니다.");
			request.getRequestDispatcher("WEB-INF/view/member/confirm/msg.jsp").forward(request, response);
		}		

	}

}
