package com.exceptiondemo.demo.resolver;

import com.exceptiondemo.demo.exception.MemberException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class UserExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("UserExceptionResolver");
        if (ex instanceof MemberException) {
            log.info("UserExceptionResolver find MemberException");
            final String header = request.getHeader(HttpHeaders.ACCEPT);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (MediaType.APPLICATION_JSON_VALUE.equals(header)) {
                final HashMap<Object, Object> result = new HashMap<>();
                result.put("ex", ex.getClass());
                result.put("message", ex.getMessage());
                final String resultTojsonFormat = objectMapper.writeValueAsString(result);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(resultTojsonFormat);
                return new ModelAndView();
            }else {
                return new ModelAndView("error/500");
            }

        }
        return null;
    }
}
