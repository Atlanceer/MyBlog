package atlan.ceer.controller;


import atlan.ceer.model.MyResult;
import atlan.ceer.model.QueryPage;
import atlan.ceer.service.BlogService;
import atlan.ceer.service.UserService;
import atlan.ceer.utils.AESUtil;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/adminUser")
public class AdminController {
    @Autowired
    private AESUtil aesUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(String username, String password, HttpServletResponse response, HttpServletRequest request){
        //log.info(username+"============"+password);
        if (userService.login(username,password)) {
            //获取session
            HttpSession httpSession = request.getSession();
            //设置session
            httpSession.setAttribute("blog_session", aesUtil.AESEncode(username));

            //添加cookie记录用户信息，设置中文转码
            Cookie blogCookie = null;
            try {
                //blogCookie = new Cookie("blog_cookie", URLEncoder.encode(username,"UTF-8"));
                blogCookie = new Cookie("blog_cookie", aesUtil.AESEncode(username));
                //log.info(blogCookie.getName()+"-->>>>>"+blogCookie.getValue());
                //设置过期时间（秒为单位）一天：60*60*24
                blogCookie.setMaxAge(60*60*24);
                //表示访问当前工程下的所有webApp都会产生cookie
                blogCookie.setPath("/");
                response.addCookie(blogCookie);
                //log.info(nameCookie.toString());
            } catch (Exception e) {
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
                    if (cookie.getName().equals("blog_cookie")){
                        //清楚cookie，使过期
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        log.info("cookie清除成功");
                    }
                }
            }
            //清除session
            request.getSession().invalidate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加博客分类
     * @param typeName
     * @return
     */
    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public MyResult addType(String typeName){
        boolean judge = blogService.addType(typeName);
        if (judge){
            return new MyResult(true,"添加成功",200);
        }else {
            return new MyResult(false,"添加失败",201);
        }
    }

    /**
     * 获取博客分类列表
     * @return
     */
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public MyResult getTypeList(String currentPage){
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage",currentPage);
        map.put("queryType","type");
        QueryPage queryPage = blogService.getList(map);
        return new MyResult(queryPage,true,"查询成功",200);
    }

    /**
     * 删除分类
     */
    @RequestMapping(value = "/type/delete", method = RequestMethod.POST)
    public MyResult deleteType(String id){
        int typeId = Integer.valueOf(id);
        if (blogService.deleteType(typeId)) {
            return new MyResult(true,"删除成功",200);
        }else {
            return new MyResult(false,"删除失败",201);
        }
    }

    /**
     * 修改分类
     */
    @RequestMapping(value = "/type/change", method = RequestMethod.POST)
    public MyResult changeType(String typeId, String typeName){
        int id = Integer.valueOf(typeId);
        if (blogService.changeType(id, typeName)) {
            return new MyResult(true,"修改成功",200);
        }else {
            return new MyResult(false,"修改失败",201);
        }
    }

    /**
     * 添加博客标签
     * @param tagName
     * @return
     */
    @RequestMapping(value = "/tag/add", method = RequestMethod.POST)
    public MyResult addTag(String tagName){
        boolean judge = blogService.addTag(tagName);
        if (judge){
            return new MyResult(true,"添加成功",200);
        }else {
            return new MyResult(false,"添加失败",201);
        }
    }

    /**
     * 获取博客标签列表
     * @return
     */
    @RequestMapping(value = "/tag/list", method = RequestMethod.GET)
    public MyResult getTagList(String currentPage){
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage",currentPage);
        map.put("queryType","tag");
        QueryPage queryPage = blogService.getList(map);
        return new MyResult(queryPage,true,"查询成功",200);
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public MyResult deleteTag(String id){
        int tagId = Integer.valueOf(id);
        if (blogService.deleteTag(tagId)) {
            return new MyResult(true,"删除成功",200);
        }else {
            return new MyResult(false,"删除失败",201);
        }
    }

    /**
     * 修改标签
     */
    @RequestMapping(value = "/tag/change", method = RequestMethod.POST)
    public MyResult changeTag(String tagId, String tagName){
        int id = Integer.valueOf(tagId);
        if (blogService.changeTag(id, tagName)) {
            return new MyResult(true,"修改成功",200);
        }else {
            return new MyResult(false,"修改失败",201);
        }
    }

    /**
     * 添加博客
     * @param
     * @return
     */
    @RequestMapping(value = "/blog/add", method = RequestMethod.POST)
    public MyResult addBlog(String title, String content, int type, int[] tag, String indexPicture){
        log.info(title+"==="+content+"==="+type+"==="+ Arrays.toString(tag) +"==="+indexPicture);
        return null;
    }

    /**
     * 获取博客列表
     * @param
     * @return
     */
    @RequestMapping(value = "/blog/list", method = RequestMethod.GET)
    public MyResult getBlogList(String currentPage, String type, String tag, int ifRecommend){
        return  null;
    }

    /**
     * 博客修改
     * @param id
     * @return
     */
    @RequestMapping(value = "/blog/modify", method = RequestMethod.POST)
    public MyResult modifyBlog(String id){
        return null;
    }

    /**
     * 博客删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/blog/delete", method = RequestMethod.POST)
    public MyResult deleteBlog(String id){
        return null;
    }

}
