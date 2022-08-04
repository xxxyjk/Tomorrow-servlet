package kh.semi.tomorrow.storyboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.service.StoryboardService;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

/**
 * Servlet implementation class ListStoryBoardServlet
 */
@WebServlet("/storylist")
public class ListStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoryboardService service = new StoryboardService();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;

		String currentPageStr = request.getParameter("page");
		try {
			if (currentPageStr != null && !currentPageStr.equals(""))
				currentPage = Integer.parseInt(currentPageStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		final int pageSize = 12; // 한페이지에 보여줄 행
		final int pageBlock = 3; // 페이징에 나타날 페이지수
		int startPage = 0;
		int endPage = 0;
		int startRnum = 0;
		int endRnum = 0;

		int totalCnt = 0; // 총 글 수
		totalCnt = countStoryBoard();

		System.out.println("총" + totalCnt);

		int totalPageCnt = (totalCnt / pageSize) + (totalCnt % pageSize == 0 ? 0 : 1);
		if (currentPage % pageBlock == 0) {
			startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
		} else {
			startPage = (currentPage / pageBlock) * pageBlock + 1;
		}
		endPage = startPage + pageBlock - 1;
		if (endPage > totalPageCnt) {
			endPage = totalPageCnt;
		}
		System.out.println("page:" + startPage + "~" + endPage);

		startRnum = (currentPage - 1) * pageSize + 1;
		endRnum = startRnum + pageSize - 1;
		if (endRnum > totalCnt) {
			endRnum = totalCnt;
		}
		System.out.println("rnum:" + startRnum + "~" + endRnum);

		ArrayList<StoryBoardVo> result = service.listStoryBoard(startRnum, endRnum);
		System.out.println(result);

		request.setAttribute("listStoryBoard", result);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPageCnt", totalPageCnt);
		
		request.getRequestDispatcher("WEB-INF/view/board/storyboardMain.jsp").forward(request, response);
	}
	
	private int countStoryBoard() {
		int result = service.countStoryBoard();
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
