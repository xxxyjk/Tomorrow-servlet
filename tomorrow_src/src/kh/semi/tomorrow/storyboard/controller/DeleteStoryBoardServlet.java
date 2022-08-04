package kh.semi.tomorrow.storyboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.storyboard.model.service.StoryboardService;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

/**
 * Servlet implementation class DeleteStoryBoardServlet
 */
@WebServlet("/storydelete")
public class DeleteStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public DeleteStoryBoardServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet - storydelete");
		String bnoStr = request.getParameter("bno");
		System.out.println("bnoStr : " + bnoStr);
		int bNo = 0;
		try {
			bNo = Integer.parseInt(bnoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bNo < 1) {
			// 오류 페이지 또는 storylist
			System.out.println("storydelete fail");
			response.sendRedirect("storylist");
			return;
		}
		int result = new StoryboardService().deleteStoryBoard(bNo);
		System.out.println(result);
		if (result > 0) {
			System.out.println("storydelete end");
			response.sendRedirect("storylist");
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
