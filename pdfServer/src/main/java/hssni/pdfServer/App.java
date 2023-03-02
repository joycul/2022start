package hssni.pdfServer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {

	public static void main(String[] args) {
		final Server server = new Server(Integer.parseInt(args[0]));
		
		TrayIconHandler.registerTrayIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("trayIcon.png")), "PDF Server", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		TrayIconHandler.addItem("Exit", new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					server.stop(); 
					System.exit(100);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		if (args.length == 0) {
			System.out.println(">>> Jar 파일을 실행할때 파라미터로 포트 번호를 입력해야합니다.");
			System.out.println(">>> 예시) javaw -jar XXX.jar 8090");

		} else {
			try {
				ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
				context.setContextPath("/");

				server.setHandler(context);
				context.addServlet(new ServletHolder(new PdfDownloadServlet()), "/download");
				context.addServlet(new ServletHolder(new PdfCreateServlet()), "/create");
				context.addServlet(new ServletHolder(new PdfTestServlet()), "/test");

				System.out.println(">>> JETTY 서버 실행중... 종료하려면 Ctrl + C 를 입력하세요.");
				server.start();
				server.join();
			} catch (NumberFormatException e) {
				System.out.println(">>> 포트번호가 잘못 지정 되었습니다.");
				System.exit(100);

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(100);
			}
		}

	}
}
