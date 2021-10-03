package com.exceptiondemo.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final String requestURI = httpServletRequest.getRequestURI();
        final String uuid = UUID.randomUUID().toString();
        try{
            log.info("REQUEST [{}][{}][{}]",uuid,httpServletRequest.getDispatcherType(),requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (Exception e){
            log.info("Excetion {}" , e.getMessage());
            throw e;
        }
        finally {
            log.info("REQUEST [{}][{}][{}]",uuid,httpServletRequest.getDispatcherType(),requestURI);
        }
    }
}
