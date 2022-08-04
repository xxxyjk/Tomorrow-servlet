package kh.semi.tomorrow.storyboard.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.semi.tomorrow.member.model.vo.MemberVo;
import kh.semi.tomorrow.storyboard.model.service.StoryboardService;
import kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo;


/**
 * Servlet implementation class WriteFileDoStoryBoardServlet
 */
@WebServlet("/storyenroll.do")
public class WriteFileDoStoryBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteFileDoStoryBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost - storyenroll.do");

		String fileSavePath = "upload/images/story/detail";
		String uploadPath = getServletContext().getRealPath(fileSavePath);
		String rootPath = getServletContext().getRealPath("/");
		
		File path = new File(uploadPath);
		if(!path.exists()) {
			path.mkdirs();
		}
		
		int maxFileSize = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, uploadPath
				, maxFileSize
				, "UTF-8"
				, new DefaultFileRenamePolicy());
		
		String bNoStr = multi.getParameter("bNo");
		int bNo = 0; // 글쓰기인 경우 bNo 를 0으로 함.
		try {
			bNo = Integer.parseInt(bNoStr);
		} catch (Exception e) {
			// 글쓰기인 경우 bNoStr이 null
		}
		
		String bFilePathParm = multi.getParameter("bFilePath");
		
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		String orgFileName = multi.getOriginalFileName("upload");	// 전송되어오기 전 client에서 파일이름
		String uploadName = multi.getFilesystemName("upload");	// 서버에 저장된 파일이름
		String mId = multi.getParameter("mId");
		int pNo = Integer.parseInt(multi.getParameter("pNo"));
		System.out.println("uploadName : " + uploadName);
		
		if (uploadName == null && orgFileName != null && bNo == 0) {	// 글쓰기 + orgFileName은 있는데 + 파일업로드가 없는 경우
			// 파일 저장 실패
			System.out.println("upload 가 null");
			response.sendRedirect("storylist");
			return;
		}
		String bImgPath = "";
		if (uploadName != null && bNo == 0) {	// 글쓰기 + 파일이 있는 경우
			bImgPath = fileSavePath + "/" + uploadName;	// 새파일을 db에 저장
		} else if (uploadName != null && bFilePathParm != null && bNo > 0) {	// 글수정/ + 기존파일 있음 + 새파일이 있는 경우
			// 기존파일 서버에서 파일 삭제
			File file = new File(rootPath + bFilePathParm);
			if (file.exists()) {	// 파일 존재여부확인 - 있다면
				file.delete();
				System.out.println("파일 삭제");
			}	// 파일 없다면.. 아무 행동하지 않고 db 저장하러 감.
			// 새파일을 db에 저장
			bImgPath = fileSavePath + "/" + uploadName;	// 새파일을 db에 저장
		} else if (uploadName == null && bFilePathParm != null && bNo > 0) {	// 글수정 + 기존파일 있음 + 새파일없음.
			// 기존파일 유지 - db에 기존파일로 저장
			bImgPath = bFilePathParm;	// 기존파일로 저장.
		}
				
		System.out.println("bImgPath : " + bImgPath);
		
		StoryBoardVo vo = new StoryBoardVo();
		vo.setbNo(bNo);
		vo.setbTitle(bTitle);
		vo.setbContent(bContent);
		vo.setmId(mId);
		vo.setpNo(pNo);
		
		vo.setbImgPath(bImgPath);
		// 로그인 상태 확인
		MemberVo ssvo = (MemberVo)request.getSession().getAttribute("ssMV");
		if(ssvo == null) {	// 로그아웃 상태라면 login page 진입
			response.sendRedirect("login");
			return;	// DB저장 안하고 밖으로 빠지게
		} else {	// 로그인한 상태라면 write page 진입
			vo.setmId(ssvo.getmId());
			vo.setbWriter(ssvo.getmNickname());
		}
		
		// DB저장
		int result = -1;
		if (bNo > 0) {
			result = new StoryboardService().updateStoryBoard(vo);
		} else {
			result = new StoryboardService().writeStoryBoard(vo);
		}
		if(result < 1) {	// 글등록 실패
			// 기존 저장된 파일이 있다면 삭제
			response.sendRedirect("storyenroll");
		} else {	// 글등록 성공
			response.sendRedirect("storylist");
		}
	}

}
