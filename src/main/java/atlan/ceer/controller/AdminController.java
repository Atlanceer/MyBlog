package atlan.ceer.controller;


import atlan.ceer.model.*;
import atlan.ceer.pojo.Blog;
import atlan.ceer.service.BlogService;
import atlan.ceer.service.UserService;
import atlan.ceer.utils.AESUtil;
import atlan.ceer.utils.TimeUtil;
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
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/adminUser")
public class AdminController {
    @Autowired
    private AESUtil aesUtil;
    @Autowired
    private TimeUtil timeUtil;
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
            //查询用户简易信息
            Map<String, Object> map = new HashMap<>();
            map.put("username",username);
            UserInfSimple userInfSimple = userService.getUserInfSimple(map);
            log.info(userInfSimple.toString());

            HttpSession httpSession = request.getSession();
            //设置session
            httpSession.setAttribute("blog_session", aesUtil.AESEncode(userInfSimple.getId().toString()));

            //添加cookie记录用户信息，设置中文转码
            Cookie blogCookie = null;
            try {
                //blogCookie = new Cookie("blog_cookie", URLEncoder.encode(username,"UTF-8"));
                blogCookie = new Cookie("blog_cookie", aesUtil.AESEncode(userInfSimple.getId().toString()));
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
    public MyResult addType(String typeName, HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = getUserId(httpSession);
        boolean judge = blogService.addType(typeName, userId);
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
    public MyResult getTypeList(String currentPage, HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = getUserId(httpSession);
        Map<String, Object> map = new HashMap<>();
        map.put("queryType","type");
        map.put("userId",userId);
        if (currentPage!=null){
            map.put("currentPage",currentPage);
            QueryPage queryPage = blogService.getList(map);
            return new MyResult(queryPage,true,"查询成功",200);
        }else {
            List queryList = blogService.getListForBlog(map);
            return new MyResult(queryList,true,"查询成功",200);
        }
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
    public MyResult addTag(String tagName, HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = getUserId(httpSession);
        boolean judge = blogService.addTag(tagName, userId);
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
    public MyResult getTagList(String currentPage, HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = getUserId(httpSession);
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("queryType","tag");
        if (currentPage!=null){
            map.put("currentPage",currentPage);
            QueryPage queryPage = blogService.getList(map);
            return new MyResult(queryPage,true,"查询成功",200);
        }else {
            //List queryList = blogService.getListForBlog(map);
            return new MyResult(blogService.getListForBlog(map),true,"查询成功",200);
        }
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
    public MyResult addBlog(String title, String content, int type, int[] tag, String indexPicture, String publish, String comment, String modify, int blogId, HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = getUserId(httpSession);
        //log.info(title+"==="+content+"==="+type+"==="+ Arrays.toString(tag) +"==="+indexPicture+"==="+publish+"==="+comment+"==="+userId);

        Blog blog = new Blog();
        blog.setIdUser(userId);
        blog.setTitle(title);
        blog.setContent(content);

        /*Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        map.put("tag",tag);*/
        if (indexPicture!=null&&!indexPicture.equals("")){
            blog.setFirstPicture(indexPicture);
        }
        //是否公开
        log.info("publish=========="+publish);
        if (publish!=null&&!publish.equals("")){
            if (publish.equals("on")){
                blog.setIsPublish(true);
            }else {
                blog.setIsPublish(false);
            }
        }else {
            blog.setIsPublish(false);
        }
        //是否开启评论
        if (comment!=null&&!comment.equals("")){
            if (comment.equals("on")){
                blog.setIsComment(true);
            }else {
                blog.setIsComment(false);
            }
        }else {
            blog.setIsComment(false);
        }
        Date nowTime = timeUtil.getTime();
        blog.setGmtCreate(nowTime);
        blog.setGmtModified(nowTime);
        boolean judge=true;
        if (modify!=null&&modify.equals("yes")){
            blog.setId(blogId);
            judge = blogService.modifyBlog(blog, type, tag);
        }else {
            judge = blogService.addBlog(blog, type, tag);
        }
        if (judge) {
            return new MyResult(true,"添加成功",200);
        }else {
            return new MyResult(false,"添加失败",205);
        }
    }

    /**
     * 获取博客列表
     * @param
     * @return
     */
    @RequestMapping(value = "/blog/list", method = RequestMethod.GET)
    public MyResult getBlogList(String title, String type ,String isPublish,String currentPage, HttpSession session){
        int userId = getUserId(session);
        Map<String, Object> map = new HashMap<>();
        map.put("idUser",userId);
        if (!(title==null||title.equals(""))){
            map.put("title",title);
        }
        if (!(type==null||type.equals(""))){
            map.put("type",Integer.valueOf(type));
        }
        if (!(isPublish==null||isPublish.equals(""))){
            if (isPublish.equals("on")){
                map.put("isPublish",1);
            }
        }else {
            map.put("isPublish",0);
        }
        if (currentPage!=null){
            map.put("currentPage",currentPage);
        }
        map.put("queryType","blog");
        QueryPage<BlogInfSimple> queryPage = blogService.getList(map);
        return new MyResult(queryPage,true,"查询成功",200);
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

    @RequestMapping(value = "/blog/inf", method = RequestMethod.GET)
    public MyResult getBlogInfForModify(int id, HttpSession httpSession){
        int idUser = getUserId(httpSession);
        BlogInfForModify blogInfForModify = blogService.getBlogInfForModify(idUser, id);
        if (blogInfForModify==null){
            return new MyResult(false,"查询失败",205);
        }else {
            return new MyResult(blogInfForModify,true,"查询成功",200);
        }
    }

    /**
     * 博客删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/blog/delete", method = RequestMethod.POST)
    public MyResult deleteBlog(String id, HttpSession httpSession){
        int idUser = getUserId(httpSession);

        return null;
    }

    /**
     * 获取用户简易信息
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/userInf", method = RequestMethod.GET)
    public MyResult getUserInf(HttpSession httpSession){
        UserInfSimple userInfSimple = getUserInfSimple(getUserId(httpSession));
        return new MyResult(userInfSimple,true,"查询成功",200);
    }

    //通过session获取用户id
    public int getUserId(HttpSession httpSession){
        //获取用户id，存在session中的
        int userId = Integer.valueOf(aesUtil.AESDecode((String)httpSession.getAttribute("blog_session")));
        return userId;
    }

    //通过用户id获取简易信息
    public UserInfSimple getUserInfSimple(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        return userService.getUserInfSimple(map);
    }

}
