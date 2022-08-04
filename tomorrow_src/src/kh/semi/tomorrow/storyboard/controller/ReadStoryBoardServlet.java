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
 * Servlet implementation class ReadStoryBoardServlet
 */
@WebServlet("/storyread")
public class ReadStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bNoStr = request.getParameter("bno");
		System.out.println("request.getParameter(\"bno\") : " + request.getParameter("bno"));
		int bNo = 0;
		try {
			bNo = Integer.parseInt(bNoStr); 
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if (bNo < 1) {
			// 오류 페이지로 이동, 또는 boardlist로 이동
			return;
		}
		StoryBoardVo result = new StoryboardService().readStoryBoard(bNo);
		request.setAttribute("bvo", result);
		
		int hitresult = new StoryboardService().hitStoryBoard(bNo);
		if (hitresult > 0) {
			request.getRequestDispatcher("WEB-INF/view/board/storyboardRead.jsp").forward(request, response);
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
