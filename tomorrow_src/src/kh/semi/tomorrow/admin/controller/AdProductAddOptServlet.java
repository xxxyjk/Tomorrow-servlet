package kh.semi.tomorrow.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.tomorrow.admin.model.service.AdminService;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;

/**
 * Servlet implementation class AdProductAddOptServlet
 */
@WebServlet("/adOptionAddtion.aj")
public class AdProductAddOptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdProductAddOptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdProductAddOptServlet - doPost");
		PrintWriter out = response.getWriter();
		int result = -1;

		String pNo_param = request.getParameter("pNo");
		String optNo_param = request.getParameter("optNo");
		String optVal = request.getParameter("optVal");
		String optPrice_param = request.getParameter("optPrice");
		
		int pNo=0; int optPrice=0; int optNo=0;
		try {
			pNo = Integer.parseInt(pNo_param);
			optNo = Integer.parseInt(optNo_param);
			optPrice = Integer.parseInt(optPrice_param);
		} catch(Exception e) {
			System.out.println("*** 정수로 변환 도중 오류 발생 ***\n");
			request.setAttribute("msg", "정수로 변환 중 오류가 발생했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
			return ;
		}
		System.out.println("페이지로부터 전달받은 데이터값");
		System.out.println("==============================================");
		System.out.println("상품번호:\t\t" + pNo);
		System.out.println("옵션번호(가구/패브릭/조명):\t" + optNo);
		System.out.println("옵션값:\t\t" + optVal);
		System.out.println("옵션가격:\t\t" + optPrice);
		System.out.println("==============================================\n");
		
		ProductDetailVo detail = new ProductDetailVo();
		detail.setOptNo(optNo);
		detail.setOptVal(optVal);
		detail.setOptPrice(optPrice);
		
		result = new AdminService().insertProductDetail(detail, pNo);
		System.out.println("AdProductAddOptServlet - result:\t" + result + "\n");
		
		out.print(result);
		out.flush();
		out.close();
	}

}
