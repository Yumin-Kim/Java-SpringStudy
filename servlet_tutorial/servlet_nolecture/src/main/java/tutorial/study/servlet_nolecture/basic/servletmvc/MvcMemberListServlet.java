package tutorial.study.servlet_nolecture.basic.servletmvc;

import tutorial.study.servlet_nolecture.basic.domain.Member;
import tutorial.study.servlet_nolecture.basic.domain.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "mvcMemberListServlet",
        urlPatterns = "/servlet-mvc/members/list"
)
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> all = memberRepository.findAll();
        request.setAttribute("members", all);

        String viewPath = "/WEB-INF/views/members.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);

    }
}
