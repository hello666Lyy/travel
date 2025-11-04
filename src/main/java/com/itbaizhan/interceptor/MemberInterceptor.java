package com.itbaizhan.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object member = request.getSession().getAttribute("member");
        if(member==null) {
            response.sendRedirect(request.getContextPath()+"/frontdesk/login");
            return false;
        }

        return true;
    }
}
