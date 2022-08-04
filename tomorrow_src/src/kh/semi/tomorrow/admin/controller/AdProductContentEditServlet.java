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
import kh.semi.tomorrow.product.model.vo.ProductVo;

/**
 * Servlet implementation class AdProductContentEditServlet
 */
@WebServlet("/adContentImageEdit.do")
public class AdProductContentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdProductContentEditServlet() {
        super();
    }
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdProductContentEditServlet - doPost");
		
		String fileSavePath = "upload/images/product/content";
		String uploadPath = getServletContext().getRealPath(fileSavePath);
		String rootPath = getServletContext().getRealPath("/");
		System.out.println("uploadPath: " + uploadPath);
		System.out.println("rootPath: " + rootPath + "\n");
		
		// 업로드 할 폴더 존재여부확인 - 없다면 생성
		File path = new File(uploadPath);
		if (!path.exists()) {
			path.mkdirs();
		}		
		int maxFileSize = 10 * 1024 * 1024; // 10MG
		
		// 파일 업로드 완료
		MultipartRequest multi = new MultipartRequest(request, uploadPath, 
				maxFileSize, 
				"UTF-8",
				new DefaultFileRenamePolicy()); 
		
		String pNo_param = multi.getParameter("pnum");
		int pNo = 0;
		try {
			pNo = Integer.parseInt(pNo_param);
		} catch(Exception e) {
			System.out.println("*** 정수로 변환 도중 오류 발생 ***");
			request.setAttribute("msg", "오류가 발생했습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
		String pFilePathParam = multi.getParameter("pContentPath");
		
		String pContent = multi.getParameter("pContent");
		String orgFileName = multi.getOriginalFileName("pContent");  // 전송되기 전 client에서 파일이름		
		String type = multi.getContentType("pContent"); // 전송된 파일의 타입 (.png. jpg)
		String upload = multi.getFilesystemName("pContent");  // 서버에 저장된 파일이름
		System.out.println("upload:\t" + upload);
		
		if (upload == null && orgFileName != null && pNo == 0) {	// orgFileName은 있는데 + 파일업로드가 없는 경우
			// 파일 저장 실패
			System.out.println("upload가 null");
			response.sendRedirect("adProductEnroll");
			return;
		}
		String pImgPath = "";		
		if(upload != null && pNo == 0) { // 글쓰기 + 파일이 있는 경우
			
			File file= new File(fileSavePath);
			if (!file.exists()) {
				file.mkdirs();
			}	
			pImgPath = fileSavePath + "/" + upload;
		} else if(upload != null && pFilePathParam != null && pNo > 0) { // 기존파일 있음 + 새파일 있는 경우
			// 기존 파일 삭제
			File file = new File(rootPath + pFilePathParam);
			if(file.exists()) { // 파일 존재여부 확인 
				file.delete();
				System.out.println("파일 삭제");
			} // 파일이 없다면 아무 행동하지 않고 db 저장하러 감
			
			// 새파일을 db에 저장
			pImgPath = fileSavePath + "/" + upload;
		} else if(upload == null && pFilePathParam != null && pNo > 0) { // 기존파일 있음 + 새파일없음
			// 기존파일 유지 - db에 기존파일로 저장 
			pImgPath = pFilePathParam;
		}
		
		System.out.println("ProductVo클래스로 들어갈 데이터값");
		System.out.println("==============================================");
		System.out.println("pNo:\t\t" + pNo);
		System.out.println("pContent:\t " + pContent);		
		System.out.println("==============================================\n");
		
		ProductVo product = new ProductVo();
		product.setpNo(pNo);
		product.setpContent(pContent);
		
		int result = 0; 
		result = new AdminService().updateProductContent(product);
		if(result == 0) { 
			request.setAttribute("msg", "상품 등록에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		} else { 
			request.setAttribute("msg", "상품 등록에 성공하였습니다.");
			request.getRequestDispatcher("WEB-INF/view/admin/confirm/msg.jsp").forward(request, response);
		}
	}

}
