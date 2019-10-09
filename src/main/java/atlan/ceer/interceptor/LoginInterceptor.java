package atlan.ceer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MyInterceptor pre");
        //String token=request.getHeader("token");

        //判断是否重新登录
        boolean ifReLogin=false;

        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("username"));
        //获取所有cookies
        /*Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // 检查是否有用户信息
                    String username=URLDecoder.decode(cookie.getValue(), "utf-8");
                    HttpSession session=request.getSession();
                    session.setAttribute("username", username);
                    log.info("用户重新登录成功"+username);
                    ifReLogin=true;
                }
            }
            //如果没有重新登录成功
            if(!ifReLogin) {
                //设置响应码
                log.info("重新登录失败");
                response.setStatus(201);
                return false;
            }
        }*/

        return true;
    }

    /*
      后处理方法，controller方法执行后，success.jsp执行前
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor post");
    }

    /*success.jsp页面执行完成之后，该方法会执行*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("MyInterceptor after");
    }
}
