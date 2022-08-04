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
 * Servlet implementation class UpdateStoryBoardServlet
 */
@WebServlet("/storyupdate")
public class UpdateStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			response.sendRedirect("storylist");
			return;
		}
		StoryBoardVo result = new StoryboardService().readStoryBoard(bNo);
		System.out.println(result);
		request.setAttribute("bvo", result);
		request.getRequestDispatcher("WEB-INF/view/board/storyboardUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
