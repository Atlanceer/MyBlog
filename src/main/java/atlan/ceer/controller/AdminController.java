package atlan.ceer.controller;

import atlan.ceer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/adminUser")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(String username, String password, HttpServletResponse response, HttpServletRequest request){
        log.info(username+"============"+password);
        if (userService.login(username,password)) {
            //获取session
            HttpSession httpSession = request.getSession();
            //设置session
            httpSession.setAttribute("username",username);

            //添加cookie，设置中文转码
            Cookie nameCookie = null;
            try {
                nameCookie = new Cookie("username", URLEncoder.encode(username,"UTF-8"));
                //设置过期时间（秒为单位）一天：60*60*24
                nameCookie.setMaxAge(60*60*24);
                response.addCookie(nameCookie);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return true;
        }else {
            return false;
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public boolean logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for(Cookie cookie: cookies){
                    if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")){
                        //清楚cookie，使过期
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            //清除session
            request.getSession().invalidate();
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
