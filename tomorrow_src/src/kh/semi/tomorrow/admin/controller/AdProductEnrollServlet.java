package kh.semi.tomorrow.admin.controller;


import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.semi.tomorrow.admin.model.service.AdminService;
import kh.semi.tomorrow.product.model.vo.ProductDetailVo;
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class AdProductEnrollServlet
 */
@WebServlet("/adProductEnroll.do")
public class AdProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdProductEnrollServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AdProductEnrollServlet - doPost\n");
	
		String fileSavePath = "upload/images/product/detail"; // "upload"파일			
		String uploadPath = getServletContext().getRealPath(fileSavePath);
		String rootPath = getServletContext().getRealPath("/");
		
		System.out.println("fileSavePath:\t" + fileSavePath);
		System.out.println("uploadPath:\t" + uploadPath);
		System.out.println("rootPath:\t" + rootPath + "\n\n");

		// 업로드 할 폴더 존재여부확인 - 없다면 생성
		File path = new File(uploadPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		
		int maxFileSize = 10 * 1024 * 1024; // 10MG
		// TODO 파일 사이즈 ?? 
		int imgSize = 124;	
		
		// 파일 업로드 완료
		MultipartRequest multi = new MultipartRequest(request, uploadPath, 
				maxFileSize, 
				"UTF-8",
				new DefaultFileRenamePolicy()); 

		String cateId_param = multi.getParameter("category");
		String pPrice_param = multi.getParameter("prod_price");
		String optNo_param = multi.getParameter("opt_no");
		String optPrice_param = multi.getParameter("opt_price");				
		
		int cateId = 0; int pPrice = 0; int optNo = 0; int optPrice = 0;		
		try {
			cateId = Integer.parseInt(cateId_param);
			pPrice = Integer.parseInt(pPrice_param);
			optNo = Integer.parseInt(optNo_param);
			optPrice = Integer.parseInt(optPrice_param);
		} catch (Exception e) {
			System.out.println("*** 정수로 변환 도중 오류 발생 ***");
			request.setAttribute("msg", "정수로 변환 중 오류가 발생했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
		
		String pFilePathParam = multi.getParameter("pFilePath");
				
		String pBrand = multi.getParameter("prod_brand");
		String pName = multi.getParameter("prod_name");
		String optVal = multi.getParameter("opt_val");
		String pContent = multi.getParameter("pContent");   
		String orgFileName = multi.getOriginalFileName("upload");  // 전송되기 전 client에서 파일이름		
		String type = multi.getContentType("upload"); // 전송된 파일의 타입 (.png. jpg)
		String upload = multi.getFilesystemName("upload");  // 서버에 저장된 파일이름
		System.out.println("upload: " + upload);

		if (upload == null && orgFileName != null && cateId == 0) {	// orgFileName은 있는데 + 파일업로드가 없는 경우
			// 파일 저장 실패
			System.out.println("upload가 null");
			response.sendRedirect("adProductEnroll");
			return;
		}
		String pImgPath = "";		
		if(upload != null && cateId != 0) { // 글쓰기 + 파일이 있는 경우
			
			File file= new File(fileSavePath);
			if (!file.exists()) {
				file.mkdirs();
			}	
			pImgPath = fileSavePath + "/" + upload;
		} else if(upload != null && pFilePathParam != null && cateId > 0) { // 기존파일 있음 + 새파일 있는 경우
			// 기존 파일 삭제
			File file = new File(rootPath + pFilePathParam);
			if(file.exists()) { // 파일 존재여부 확인 
				file.delete();
				System.out.println("파일 삭제");
			} // 파일이 없다면 아무 행동하지 않고 db 저장하러 감
			
			// 새파일을 db에 저장
			pImgPath = fileSavePath + "/" + upload;
		} else if(upload == null && pFilePathParam != null && cateId > 0) { // 기존파일 있음 + 새파일없음
			// 기존파일 유지 - db에 기존파일로 저장 
			pImgPath = pFilePathParam;
		}		
		String productImgName = pImgPath;
		
		System.out.println("pImgPath: " + pImgPath);
		
		System.out.println("ProductVo클래스로 들어갈 데이터값");
		System.out.println("==============================================");
		System.out.println("pContent:\t " + pContent);
		System.out.println("cateId:\t " + cateId);
		System.out.println("pBrand:\t " + pBrand);
		System.out.println("pName:\t " + pName);
		System.out.println("pPrice:\t " + pPrice);
		System.out.println("optNo:\t " + optNo);
		System.out.println("optVal:\t " + optVal);
		System.out.println("optPrice:\t " + optPrice);
		System.out.println("productImgName:\t" + productImgName);
		System.out.println("imgSize:\t" + imgSize);
		System.out.println("==============================================\n");

		
		
		// product 객체에 값 저장
		ProductVo product = new ProductVo();
		product.setpBrand(pBrand);
		product.setpName(pName);	
		product.setpContent(pContent); // 상품 정보용
		product.setCateId(cateId);
		product.setpPrice(pPrice);
		
		// 대표 이미지용 
		product.setProductImgName(productImgName);
		product.setProductImgSize(imgSize);
		// product_detail 객체에 값 저장
		ProductDetailVo detail = new ProductDetailVo();
		detail.setOptNo(optNo);
		detail.setOptVal(optVal);
		detail.setOptPrice(optPrice);
		
		int result = 0; 
		result = new AdminService().insertProduct(product, detail);
		System.out.println("AdProductEnrollServlet - reuslt:\t" + result + "\n");

		if(result == 0) { 
			request.setAttribute("msg", "상품 등록에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		} else { 
			request.setAttribute("msg", "상품 등록에 성공하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
		
	}	
}
