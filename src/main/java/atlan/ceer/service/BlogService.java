package atlan.ceer.service;

import atlan.ceer.model.QueryPage;
import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TypeBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //获取分类列表 map存查询的条件 如 当前页码或者搜索信息
    QueryPage<TypeBlog> getTypeList(Map map);
    //添加分类
    boolean addType(String typeName);
    //删除分类
    boolean changeType(Integer typeId);

    //获取标签列表 map存查询的条件 如 当前页码或者搜索信息
    QueryPage<TagBlog> getTagList(Map map);
    //添加标签
    boolean addTag(String tagName);
    //删除标签
    boolean changeTag(Integer tagId);
}
