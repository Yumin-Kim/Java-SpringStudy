package ch00;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class result
 */
@WebServlet("/ch00/result")
public class result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");	
		response.setCharacterEncoding("utf-8"); 
		
		HttpSession session = request.getSession(false);
		String id = (String)session.getAttribute("jsp");
		String URL = request.getRequestURI();
		int serverPort = request.getServerPort();
		PrintWriter out = response.getWriter(); 
		String name = request.getParameter("name");
		String port = request.getParameter("port");
		
		String aa = name.equals(URL)? "정답!!!" : URL+"답은 이거예요!! 서블릿으로 구현해서 .jsp가 없어요!!"; 
		String portVaild = Integer.parseInt(port) == serverPort ? "정답!!" : "본인의 포트는 "+serverPort+"이거랍니다" ;
		out.println("<html><head></head><body>"); 
		out.println("<h2>Session을 삭제 했다면 "+id+" 나올 겁니다!!</h2>\r\n");
		out.println("<h4>입력하신 파일 uri는 "+aa+"</h4>\r\n");
		out.println("<h4>입력하신 Port의 결과는 "+portVaild+"</h4>\r\n");
		out.println("<html><head></head><body>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
