package atlan.ceer.mapper;

import atlan.ceer.pojo.TypeBlog;

import java.util.List;
import java.util.Map;

/**
 * 用来查询复杂的请求
 */
public interface QueryMapper {
    //查询分类列表
    List<TypeBlog> getTypeList(Map map);

}
