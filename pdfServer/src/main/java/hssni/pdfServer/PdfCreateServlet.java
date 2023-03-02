package hssni.pdfServer;

import com.ironsoftware.ironpdf.*;  
import com.ironsoftware.ironpdf.render.*;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 

public class PdfCreateServlet extends HttpServlet {

	
	
	public PdfCreateServlet() {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String url = "";
//		if(request.getParameter("ietmUrl") == null){
//			url = "http://192.168.1.36:8090/IETM/resources/pdf/KFXIETM.html";
//			System.out.println("kfUrl :"+url);
//		}else{
//			url = URLDecoder.decode(request.getParameter("port"),"UTF-8") + URLDecoder.decode(request.getParameter("ietmUrl"),"UTF-8");
//			System.out.println("lahUrl :"+url);
//		}
		
		String  url = URLDecoder.decode(request.getParameter("port"),"UTF-8") + URLDecoder.decode(request.getParameter("ietmUrl"),"UTF-8");
		System.out.println("lahUrl :"+url);
		
		//System.out.println(request.getServerPort());
		//System.out.println("url :"+url);
//		String url = URLDecoder.decode(request.getParameter("ietmUrl"),"UTF-8");
		//String path = request.getScheme() + "://" + request.getServerName() + ":" + 2245 + url;
		//System.out.println("path : "+path);
		
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String title = URLDecoder.decode(request.getParameter("title"), "UTF-8");
		String code = request.getParameter("code");
		String pubDate = request.getParameter("pubDate");
		String pubNum = request.getParameter("pubNum");
		String paperOri = request.getParameter("paperOri");
	
		
		PdfCreate ietmPdf = new PdfCreate(url, date, time, title, code, pubDate, pubNum, paperOri);
		
        RequestDispatcher requestDispatehcer = request.getRequestDispatcher("/download");
        requestDispatehcer.forward(request, response);
		//response.sendRedirect("/download?title=" + URLEncoder.encode(title, "UTF-8"));
        
	}
	
	

}
