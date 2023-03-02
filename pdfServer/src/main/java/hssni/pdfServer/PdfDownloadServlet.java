package hssni.pdfServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ironsoftware.ironpdf.*;  
import com.ironsoftware.ironpdf.render.*;
import java.io.IOException;
import java.nio.file.Paths;

public class PdfDownloadServlet extends HttpServlet {

	public PdfDownloadServlet() {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setCharacterEncoding("UTF-8");
//		String title = URLDecoder.decode(request.getParameter("title"), "UTF-8");
//		String date = request.getParameter("date");
		String code = request.getParameter("code");
		//String downloadTitle = code + ".pdf";
		
		String downloadTitle = "tests.pdf";
		
		//String downloadTitle = "testee.pdf";
		System.out.println(downloadTitle);
		System.out.println("pdf다운로드 시작");
		String fileName = "serverCreatePdf.pdf";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename=\"" + downloadTitle + "\"");

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\test\\" + fileName));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		int readBytes = 0;
		while ((readBytes = bis.read()) != -1) {
			bos.write(readBytes);
		}

		bis.close();
		bos.close();

	}
}
