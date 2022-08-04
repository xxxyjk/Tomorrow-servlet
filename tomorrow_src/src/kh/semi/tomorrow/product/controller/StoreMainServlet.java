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
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/store")
public class StoreMainServlet extends HttpServlet {
	private ProductService service = new ProductService();

	public StoreMainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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



		ArrayList<ProductVo> result =  service.selectAllProduct(pNo);
		System.out.println("결과"+result+" pNo"+pNo);
		
		request.setAttribute("pageCateId", pageCateId);
		request.setAttribute("selectAllProduct", result);
		
		request.getRequestDispatcher("WEB-INF/view/product/store.jsp").forward(request, response);
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//	
//	private int countBoard() {
//		int result = service.countBoard();
//		return result;
//	}

}
