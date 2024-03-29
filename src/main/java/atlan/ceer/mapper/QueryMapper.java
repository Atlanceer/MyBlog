package atlan.ceer.mapper;

import atlan.ceer.model.BlogInfSimple;
import atlan.ceer.model.UserInfSimple;
import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TypeBlog;

import java.util.List;
import java.util.Map;

/**
 * 用来查询复杂的请求
 */
public interface QueryMapper {
    //查询分类列表
    List<TypeBlog> getTypeList(Map map);

    //查询标签列表
    List<TagBlog> getTagList(Map map);

    //查询博客列表
    List<BlogInfSimple> getBlogList(Map map);

    //获取用户的简易信息
    UserInfSimple getUserInfSimple(Map<String, Object> map);

    //添加博客
    //void addBlog(Map map);
    List<Map> getBlogTag(Map<String, Integer> map);

    Map getBlogType(Map<String, Integer> map);
}
