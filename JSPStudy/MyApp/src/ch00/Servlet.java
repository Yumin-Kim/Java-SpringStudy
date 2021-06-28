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
 * Servlet implementation class Servlet
 */
@WebServlet("/ch00/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
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
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");	
		response.setCharacterEncoding("utf-8"); 
		
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(100);
		if(session != null) session.setAttribute("jsp", "JSP");
		int intervalTime = session.getMaxInactiveInterval();
		String name = (String) session.getAttribute("jsp");
		
		
		PrintWriter out = response.getWriter(); 
		out.println("<html><head></head><body>"); 
		out.println("      <h2>현재 페이지에서 Session 보여주시고 \r\n다음 페이지에서는 세션을 삭제해주세요!!</h2>\r\n" + 
				"      <h2>다음에 결과 값이 출력되는 파일명을 적어주세요 >>결과 출력 /MyApp/ch00/result.jsp 이와 같은 값</h2>\r\n" + 
				"      <h2>본인 포트 번호 맞추기 포트 번호란 localhost:XXXX 이와 같이 XXXX에 적힌 숫자를 맞추시면 되요!!</h2>\r\n" + 
				" 		<p> 현재 가지고 있는 session 값입니다!! <b>"+intervalTime+"</b>값을 추가 했습니다</p>"+
				" 		<p> 현재 가지고 있는 session value 값입니다!! <b>"+name+"</b>값을 추가 했습니다</p>"+
				" 		<p> session은 키 와 value 이루어져 있습니다!!</p>"+
				"      <form  action=\"result.jsp\" method=\"POST\" >\r\n" +
				"        <p> FilePath : \r\n" + 
				"          <input style=\"width: 300px;\" name= \"name\" type=\"text\" placeholder=\"/MyApp/ch00/result.jsp 이와 같은 값을 입력!!!!\" />\r\n" + 
				"        </p>\r\n" + 
				"        <p> Port : \r\n" + 
				"          <input style=\"width: 300px;\" type=\"number\" name= \"port\" placeholder=\"본인의 PORT번호를 입력하세요!!\" />\r\n" + 
				"        </p>\r\n" + 
				"        <input type=\"submit\" value=\"확인하러 가기!!\" />\r\n" + 
				"      </form>\r\n" + 
				""); 
		out.println("</body></html>");
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
