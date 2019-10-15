package atlan.ceer.service;

import atlan.ceer.model.QueryPage;
import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TypeBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //根据查询的类型 获取查询列表
    QueryPage getList(Map<String, Object> map);


    //获取分类列表 map存查询的条件 如 当前页码或者搜索信息
    //QueryPage<TypeBlog> getTypeList(Map map);
    //添加分类
    boolean addType(String typeName);
    //修改分类
    boolean changeType(Integer typeId, String typeName);
    //删除分类
    boolean deleteType(Integer typeId);

    //获取标签列表 map存查询的条件 如 当前页码或者搜索信息
    //QueryPage<TagBlog> getTagList(Map map);
    //添加标签
    boolean addTag(String tagName);
    //修改标签
    boolean changeTag(Integer tagId, String tagName);
    //删除标签
    boolean deleteTag(Integer tagId);

}
