package hssni.pdfServer;

import com.ironsoftware.ironpdf.*;  
import com.ironsoftware.ironpdf.render.*;

import com.ironsoftware.ironpdf.headerfooter.TextHeaderFooter;
import com.ironsoftware.ironpdf.headerfooter.HeaderFooterOptions;
import com.ironsoftware.ironpdf.headerfooter.HtmlHeaderFooter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PdfCreate {
	
	
	public PdfCreate(String url, String date, String time, String title, String code, String pubDate, String pubNum, String paperOri) throws IOException {
		create(url, date, time, title, code, pubDate, pubNum, paperOri);
	}
	
	public void create(String url, String date, String time, String title, String code, String pubDate, String pubNum, String paperOri) throws IOException {
	
		
		//License.setLicenseKey("IRONPDF.JAVA.2199-1B73E6A98D-BXF6Z23B2PKYQFR-PNPANR47TTUD-J5QDQ3X36H3C-YLNYV4EDTCLJ-PRE25RKUUWOC-CLMFXY-TEBSEI7ZA7CIUA-DEPLOYMENT.TRIAL-J7I4AV.TRIAL.EXPIRES.24.FEB.2023");
		License.setLicenseKey("IRONPDF.JAVA.2199-8C9F50A3EA-HWPJDZGTUGWYT-H7MXYIPYSVZ7-FYYBZRKGGUBO-PRKHU7Y4DQHB-2SRSMLN4JV2M-3MHZEL-TG2S3YSMPQCJEA-DEPLOYMENT.TRIAL-2QTMLK.TRIAL.EXPIRES.30.MAR.2023");
		 
		// Set a log path
		//Settings.setLogPath(Paths.get("C:/tmp/IronPdfEngine.log"));
		
		// Create a new ChromePdfRenderOptions object
		ChromePdfRenderOptions renderOptions = new ChromePdfRenderOptions();
		 
		
		// Set custom options for the generated PDF document (paper size, title, etc.)
		renderOptions.setCustomPaperSizeInCentimeters(21, 29.7);
		//renderOptions.setPaperSize(PaperSize.A4);
		renderOptions.setPrintHtmlBackgrounds(true);
		
		if(paperOri.equals("PORTRAIT")) {
			renderOptions.setPaperOrientation(PaperOrientation.PORTRAIT);
			System.out.println("세로");
			renderOptions.setTitle(title);
			renderOptions.setEnableJavaScript(true);
			renderOptions.setRenderDelay(7000); // time in milliseconds
			
//			/* test */
//			renderOptions.isEnableJavaScript();
			
			renderOptions.setCssMediaType(CssMediaType.PRINT);
			renderOptions.setFitToPaperMode(FitToPaperModes.None);
			renderOptions.setZoom(100); // percentage
			renderOptions.setCreatePdfFormsFromHtml(true);
			 
			// Customize PDF Margins by specifying sizes in millimeters
			renderOptions.setMarginTop(20);
			renderOptions.setMarginBottom(20);
			renderOptions.setMarginLeft(10);
			renderOptions.setMarginRight(10);		
			
			
			// Render the HTML as a PDF. Stored in myPdf as type PdfDocument;
			PdfDocument myPdf = PdfDocument.renderUrlAsPdf(url, renderOptions);

			
			HtmlHeaderFooter footer = new HtmlHeaderFooter();
			footer.setMaxHeight(20); // millimeters
			//footer.setLoadStylesAndCSSFromMainHtmlDocument(true);
			footer.setDrawDividerLine(false);
			footer.setHtmlFragment("<hr><div style='width: 100%; display: flex; flex-direction: row;'><div style='font-size: 2vw; width: 40%;'><div id='date'>" + date + "</div><div id='time'>" + time + "</div><div>본 문서는 사용 후 파기하여 주시기 바랍니다.</div></div><div style='width: 45%; display: flex; flex-direction: column; align-items: flex-end;'><div id='idNum'style='font-size: 4vw; font-weight: bold; float: right;'>" + code + "</div><div id='num' style='font-size: 2vw; float: right;'>" + pubNum +  "</div></div><div style='font-size: 2vw; width: 15%; display: flex; flex-direction: column; align-items: flex-end;'><div id='page' style='font-size: 2vw; float: right;'><br>페이지 {page}</div><div id='pDate' style='font-size: 2vw; float: right;'>" + pubDate + "</div></div></div><br>");
			myPdf.addHtmlFooter(footer);
			List<PdfDocument> pdfs = new ArrayList<>();
			
			HtmlHeaderFooter header = new HtmlHeaderFooter();
			header.setMaxHeight(10); // millimeters
			//header.setHtmlFragment("<img src=\"logo.png\" />");
			//header.setBaseUrl("./assets/");
			header.setHtmlFragment("<br><div align='right' style='font-size: 2vw;'>{pdf-title}</div>");
			myPdf.addHtmlHeader(header);
			
			
			try {
				myPdf.saveAs(Paths.get("C:\\test\\serverCreatePdf.pdf"));
			} catch (IOException e) {
			    throw new RuntimeException(e);
			}
			
		} else if(paperOri.equals("LANDSCAPE")) {
			renderOptions.setPaperOrientation(PaperOrientation.LANDSCAPE);
			System.out.println("가로");
			
			
			renderOptions.setTitle(title);
			renderOptions.setEnableJavaScript(true);
			renderOptions.setRenderDelay(5000); // time in milliseconds
			renderOptions.setCssMediaType(CssMediaType.PRINT);
			renderOptions.setFitToPaperMode(FitToPaperModes.None);
			renderOptions.setZoom(100); // percentage
			renderOptions.setCreatePdfFormsFromHtml(true);
			 
			// Customize PDF Margins by specifying sizes in millimeters
			renderOptions.setMarginTop(10);
			renderOptions.setMarginBottom(15);
			renderOptions.setMarginLeft(10);
			renderOptions.setMarginRight(10);		
			
			
			
			// Render the HTML as a PDF. Stored in myPdf as type PdfDocument;
			PdfDocument myPdf = PdfDocument.renderUrlAsPdf(url, renderOptions);
			
			
			HtmlHeaderFooter footer = new HtmlHeaderFooter();
			footer.setMaxHeight(20); // millimeters
			//footer.setLoadStylesAndCSSFromMainHtmlDocument(true);
			footer.setDrawDividerLine(false);
			footer.setHtmlFragment("<hr><div style='width: 100%; display: flex; flex-direction: row;'><div style='font-size: 1vw; width: 40%;'><div id='date'>" + date + "</div><div id='time'>" + time + "</div><div>본 문서는 사용 후 파기하여 주시기 바랍니다.</div></div><div style='width: 45%; display: flex; flex-direction: column; align-items: flex-end;'><div id='idNum'style='font-size: 2vw; font-weight: bold; float: right;'>" + code + "</div><div id='num' style='font-size: 1vw; float: right;'>" + pubNum +  "</div></div><div style='font-size: 1vw; width: 15%; display: flex; flex-direction: column; align-items: flex-end;'><div id='page' style='font-size: 1vw; float: right;'><br>페이지 {page}</div><div id='pDate' style='font-size: 1vw; float: right;'>" + pubDate + "</div></div></div><br>");
			myPdf.addHtmlFooter(footer);
			List<PdfDocument> pdfs = new ArrayList<>();
			
			HtmlHeaderFooter header = new HtmlHeaderFooter();
			header.setMaxHeight(10); // millimeters
			//header.setHtmlFragment("<img src=\"logo.png\" />");
			//header.setBaseUrl("./assets/");
			header.setHtmlFragment("<br><div align='right' style='font-size: 1vw;'>{pdf-title}</div>");
			myPdf.addHtmlHeader(header);
			
			try {
				myPdf.saveAs(Paths.get("C:\\test\\serverCreatePdf.pdf"));
			} catch (IOException e) {
			    throw new RuntimeException(e);
			}
			
		} else {
			System.out.println(paperOri);
			System.out.println("출력 용지 방향 오류");
		}

		 
		

	}
}
