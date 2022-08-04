package kh.semi.tomorrow.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.member.model.service.MemberService;
import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.order.model.service.OrderService;
import kh.semi.tomorrow.order.model.vo.OrderVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;

/**
 * Servlet implementation class MemberInfoReadServlet
 */
@WebServlet("/memberinfo")
public class MemberInfoReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberinfo doGet");
		
		// 로그인 상태 확인
		MemberVo ssvo = (MemberVo)request.getSession().getAttribute("ssMV");
		String mId = "";
		if(ssvo == null) {	// 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
			return;	// DB저장 안하고 밖으로 빠지게
		} else {	// 로그인한 상태라면 write page 진입
			mId = ssvo.getmId();
		}

		// 구매 목록 
		ArrayList<OrderVo> orderVoList = new OrderService().myOrder2(mId);
		System.out.println("orderVoList:"+orderVoList);
		request.setAttribute("orderVoList", orderVoList);
		
		//회원이름
		MemberVo memberVo = new MemberService().myName(mId);
		request.setAttribute("memberVo", memberVo);
		
		// 내 스토리 목록
		int currentPage = 1;		
		String currentPageStr = request.getParameter("page");
		
		try {
			if(currentPageStr !=null && !currentPageStr.equals(""))
				currentPage = Integer.parseInt(currentPageStr);
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		final int pageSize = 8;  // 한페이지에 보여줄 행
		final int pageBlock = 3;  // 페이징에 나타날 페이지수
		int startPage=0;
		int endPage=0;
		int startNum=0;
		int endNum=0;
		
		int totalCnt = 0; // 총 글 수
		totalCnt = new MemberService().countMyBoard(mId);
		System.out.println("게시글 총 개수 "+totalCnt);
		
		/* Paging 처리 */
		int totalPageCnt = (totalCnt/pageSize) + (totalCnt%pageSize==0 ? 0 : 1);
		if(currentPage%pageBlock == 0) {
			startPage = ((currentPage/pageBlock)-1)*pageBlock + 1;
		} else {
			startPage = (currentPage/pageBlock)*pageBlock + 1;
		}
		endPage = startPage + pageBlock - 1;
		if(endPage>totalPageCnt) {
			endPage = totalPageCnt;
		}
		System.out.println("page:"+ startPage +"~"+endPage);
		
		/* rownum 처리 */
		startNum = (currentPage-1)*pageSize + 1;
		endNum = startNum + pageSize -1;
		if(endNum>totalCnt) {
			endNum = totalCnt;
		}
		System.out.println("rnum:"+ startNum +"~"+endNum);	
		
		ArrayList<StoryBoardVo> boardlist = new MemberService().myBoardList(startNum, endNum, mId );
		System.out.println(boardlist);
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPageCnt", totalPageCnt);
		
		
		request.getRequestDispatcher("WEB-INF/view/memberinfo/mypage.jsp").forward(request, response);
		
	}
	

}