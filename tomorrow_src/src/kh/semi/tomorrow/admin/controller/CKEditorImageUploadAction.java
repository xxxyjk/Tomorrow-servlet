package kh.semi.tomorrow.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ContentImageUpload.do")

public class CKEditorImageUploadAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("CKEditorImageUploadAction - service()");
		System.out.println("ContentImageUpload.do - textarea에 사진 업로드 시작합니다.\n");
		
		PrintWriter printWriter = null;
		
        try{
        	String web_path = "/upload/images/product/content/";        	
            String abs_path = request.getServletContext().getRealPath(web_path);
            int max_size = 1024*1024*10;
            
            System.out.println("web_path:\t" + web_path);
            System.out.println("abs_path:\t" + abs_path);
            
            // 폴더가 없다면 생성
            File path = new File(abs_path);
            if(!path.exists()) {
            	path.mkdirs();
            }
            
            MultipartRequest mr = new MultipartRequest(request, abs_path, 
            		max_size, 
            		"utf-8",
            		new DefaultFileRenamePolicy());
            
            File f = mr.getFile("upload");
            String fileName="";
            if(f!=null){
            	fileName = f.getName();
            }  	
            
            response.setContentType("text/html;charset=utf-8");
            printWriter = response.getWriter();
            
            String url = request.getRequestURL().toString().replaceAll("/ContentImageUpload.do", "");
            //System.out.println(url);
            
            String fileUrl = url + web_path + fileName;//url
            String sendData = String.format("{\"filename\" : \"%s\", \"uploaded\" : 1, \"url\":\"%s\"}", 
            		                                         fileName,       					fileUrl
            		);
            
            System.out.println("");
            
            printWriter.println(sendData);
            printWriter.flush();
 
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
	}
	
}
