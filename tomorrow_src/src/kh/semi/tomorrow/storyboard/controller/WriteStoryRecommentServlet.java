package kh.semi.tomorrow.storyboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.storyboard.model.service.StoryboardService;
import kh.semi.tomorrow.storyboard.model.vo.StoryRecommentVo;

/**
 * Servlet implementation class WriteStoryRecommentServlet
 */
@WebServlet("/storyrenroll")
public class WriteStoryRecommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteStoryRecommentServlet() {
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
		System.out.println("doPost - storyrenroll");
		
		String bNoStr = request.getParameter("bNo");
		int bNo = 0;
		try {
			bNo = Integer.parseInt(bNoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bNo <= 0) {
			response.sendRedirect("storylist");
			return;
		}
		String mId = request.getParameter("mId");
		String bRecomment = request.getParameter("bRecomment");
		StoryRecommentVo vo = new StoryRecommentVo();
		vo.setbNo(bNo);
		vo.setmId(mId);
		vo.setrContent(bRecomment);
		int result = new StoryboardService().writeStoryReComment(vo);
		if(result < 1) {
			response.sendRedirect("storylist");
		} else {
			response.sendRedirect("storyread?bno=" + bNo);
		}
	}
}
