package kh.semi.tomorrow.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kh.semi.tomorrow.product.model.service.ProductService;
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet.ax")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cateIdStr = request.getParameter("pageCateId");
		int pageCateId = 0;
		
		String pNoStr = request.getParameter("pNo");
		int pNo=0;
		
		try {
			pageCateId = Integer.parseInt(cateIdStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(pageCateId + ": CategoryServlet 카테고리 아이디");

		int currentPage = 1;

		String currentPageStr = request.getParameter("page");
		try {
			if (currentPageStr != null && !currentPageStr.equals(""))
				currentPage = Integer.parseInt(currentPageStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		final int pageSize = 6; // 한페이지에 보여줄 행
		final int pageBlock = 5; // 페이징에 나타날 페이지수
		int startPage = 0;
		int endPage = 0;
		int startRnum = 0;
		int endRnum = 0;

		int totalCnt = 0; // 총 글 수
		totalCnt = countProduct(pageCateId);

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

		ArrayList<ProductVo> result =  new ProductService().selectAllProduct(startRnum, endRnum, pageCateId, pNo);
		System.out.println(result);

		PrintWriter out = response.getWriter();
		
		Gson gobj = new GsonBuilder().setPrettyPrinting().create();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectAllProduct", result);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("totalPageCnt", totalPageCnt);
		map.put("pageCateId", pageCateId);
		String resStr = gobj.toJson(map);
		
		System.out.println(resStr);
		out.println(resStr);
		out.flush();
		out.close();
	}

	private int countProduct(int pageCateId) {
		int result = new ProductService().countProduct(pageCateId);
		return result;
	}
}
