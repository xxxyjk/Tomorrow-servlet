package kh.semi.tomorrow.storyboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.storyboard.model.dao.StoryboardDao;
import kh.semi.tomorrow.storyboard.model.service.StoryboardService;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

/**
 * Servlet implementation class WriteDoStoryBoardServlet
 */
@WebServlet("/enroll.do")
public class WriteDoStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteDoStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost - enroll.do");
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String mId = request.getParameter("mId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		
		StoryBoardVo vo = new StoryBoardVo();
		vo.setbTitle(bTitle);
		vo.setbContent(bContent);
		vo.setmId(mId);
		vo.setpNo(pNo);
		
		System.out.println("WriteDoStoryBoardServlet vo : " + vo);
		
		int result = new StoryboardService().writeStoryBoard(vo);

		System.out.println("WriteDoStoryBoardServlet result : " + result);
		
		if(result == 1) {
			System.out.println("글 등록 성공");
		} else {
			System.out.println("글 등록 실패");
		}
	}
}
