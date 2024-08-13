package com.hhplus.concert.interfaces.interceptor;

import com.hhplus.concert.application.facade.QueueFacade;
import com.hhplus.concert.application.facade.TokenFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    QueueFacade queueFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("============================== START ==============================");
        logger.info(" Request URI \t:  " + request.getRequestURI());
        logger.info(" Servlet URL \t:  " + request.getRequestURL());

        return queueFacade.checkActiveToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(" Request URI \t:  " + request.getRequestURI());
        logger.info(" Servlet URL \t:  " + request.getRequestURL());
        logger.debug("============================== END ==============================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            logger.debug("============================== ERROR ==============================");
            logger.error(" Request URI \t:  " + request.getRequestURI());
            logger.error(" Servlet URL \t:  " + request.getRequestURL());
            logger.error(" Servlet URL \t:  " + ex.getClass());
            logger.debug("============================== END ==============================");
        }
    }
}
