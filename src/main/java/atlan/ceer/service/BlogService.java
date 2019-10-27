package atlan.ceer.service;

import atlan.ceer.model.QueryPage;
import atlan.ceer.pojo.Blog;
import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TypeBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //根据查询的类型 获取查询列表
    QueryPage getList(Map<String, Object> map);
    //获取分类和标签列表，用于填充于博客添加页
    List getListForBlog(Map<String, Object> map);


    //获取分类列表 map存查询的条件 如 当前页码或者搜索信息
    //QueryPage<TypeBlog> getTypeList(Map map);
    //添加分类
    boolean addType(String typeName, int userId);
    //修改分类
    boolean changeType(Integer typeId, String typeName);
    //删除分类
    boolean deleteType(Integer typeId);

    //获取标签列表 map存查询的条件 如 当前页码或者搜索信息
    //QueryPage<TagBlog> getTagList(Map map);
    //添加标签
    boolean addTag(String tagName, int userId);
    //修改标签
    boolean changeTag(Integer tagId, String tagName);
    //删除标签
    boolean deleteTag(Integer tagId);

    //查询博客列表
    QueryPage getBlogList(Map map);
    //添加博客
    boolean addBlog(Blog blog, int type, int[] tag);
    //修改博客
    boolean changeBlog(Map map);
    //删除博客
    boolean deleteBlog(Integer blogId);




}
