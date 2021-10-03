package com.exceptiondemo.demo.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController {


    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request , HttpServletResponse response){
        log.info("error-page 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request , HttpServletResponse response){
        log.info("error-page 500");
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String ,Object>> getErrorPageAPIData(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        log.info("API ErrorPage 500");
        final HashMap<String, Object> result = new HashMap<>();
        final Exception requestExceptionInfo = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        final Integer requestErrorStatusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        result.put("status", requestErrorStatusCode);
        result.put("message", requestExceptionInfo);

        return new ResponseEntity<>(result, HttpStatus.valueOf(requestErrorStatusCode));
    }

}
