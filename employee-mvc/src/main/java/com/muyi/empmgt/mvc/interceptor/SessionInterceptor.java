package com.muyi.empmgt.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        String requestURI = request.getRequestURI();
        logger.info("from: "+ requestURI);
        //ignore for css and js files
        if (requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.endsWith(".ico")) {
            return true;
        }
        //check if user is logged in
        Object loggedInUser = request.getSession().getAttribute("loggedInUser");
        if(loggedInUser!=null){
            if(requestURI.equals("/")) {
                response.sendRedirect("/dashboard");
                return false;
            }
            return true;
        }

        //allow for / and /login, any other should be sent to login page /
        if(requestURI.equals("/") || requestURI.equals("/login")) {
            return true;
        }
        response.sendRedirect("/");
        return false;
//        // if displaying the home page, make sure the user is reloaded.
//        if (request.getRequestURI().endsWith("login.html")) {
//            session.removeAttribute("isUserLoggedIn");
//        }
//
//        User userSession = (User) request.getSession().getAttribute("user");
//        if (userSession == null && !request.getRequestURI().endsWith("login")) {
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
//        ////////////////////
//        User userSession = (User) request.getSession().getAttribute("user");
//        if (userSession == null) {
//            response.sendRedirect("/login");
//            return false;
//        }
    }
}
