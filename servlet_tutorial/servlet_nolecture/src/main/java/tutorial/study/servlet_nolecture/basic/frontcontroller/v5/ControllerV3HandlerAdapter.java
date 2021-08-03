package tutorial.study.servlet_nolecture.basic.frontcontroller.v5;

import tutorial.study.servlet_nolecture.basic.frontcontroller.ModelView;
import tutorial.study.servlet_nolecture.basic.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ControllerV3HandlerAdapter implements MyHandleAdapter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controllerV3 = (ControllerV3) handler;
        HashMap<String, String> paramMap =  createParamMap(request);
        return controllerV3.process(paramMap);
    }

    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
