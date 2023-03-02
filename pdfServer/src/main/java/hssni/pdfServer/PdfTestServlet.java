package hssni.pdfServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ironsoftware.ironpdf.License;
import com.ironsoftware.ironpdf.PdfDocument;

public class PdfTestServlet extends HttpServlet {

	public PdfTestServlet() {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//License.setLicenseKey("IRONPDF.JAVA.2199-1B73E6A98D-BXF6Z23B2PKYQFR-PNPANR47TTUD-J5QDQ3X36H3C-YLNYV4EDTCLJ-PRE25RKUUWOC-CLMFXY-TEBSEI7ZA7CIUA-DEPLOYMENT.TRIAL-J7I4AV.TRIAL.EXPIRES.24.FEB.2023");
		License.setLicenseKey("IRONPDF.JAVA.2199-8C9F50A3EA-HWPJDZGTUGWYT-H7MXYIPYSVZ7-FYYBZRKGGUBO-PRKHU7Y4DQHB-2SRSMLN4JV2M-3MHZEL-TG2S3YSMPQCJEA-DEPLOYMENT.TRIAL-2QTMLK.TRIAL.EXPIRES.30.MAR.2023");


		PdfDocument myPdf = PdfDocument.renderUrlAsPdf("http://localhost:2245/servlets3/wietmsd?target=main&action=book_win&id=1675413384676&collection=KF21_TESTIETM&book=pmc_kfxtest_test2_tst00_00_ko-kr&lang=english");
		// Save the PdfDocument to a file
		myPdf.saveAs(Paths.get("C:\\test\\serverCreatePdf.pdf"));
		
		//PdfDocument pdfDocument = PdfDocument.renderUrlAsPdf("https://ironpdf.com/java");
		String downloadTitle = "testOn.pdf";
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
