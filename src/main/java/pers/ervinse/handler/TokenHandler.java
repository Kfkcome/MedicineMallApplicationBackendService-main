package pers.ervinse.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.ervinse.domain.User;
import pers.ervinse.exception.SystemException;
import pers.ervinse.utils.JwtUtil;
import pers.ervinse.utils.UserContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static pers.ervinse.enums.ResponseCode.FORBIDDEN;

@Component
public class TokenHandler implements HandlerInterceptor {

    @Autowired
    @Lazy
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取token
        String token = request.getHeader("token");
        if (token != null) {
            try {
                // 解析token
                User user = jwtUtil.parseTokenToEmployee(token);
                if (user != null) {
                    // 存放用户
                    UserContextUtil.set(user);
                    return true;
                }
            } catch (Exception e) {
                throw new SystemException(FORBIDDEN);
            }

        }
        throw new SystemException(FORBIDDEN);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
