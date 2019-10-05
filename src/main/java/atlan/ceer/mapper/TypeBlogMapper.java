package atlan.ceer.mapper;

import atlan.ceer.pojo.TypeBlog;
import atlan.ceer.pojo.TypeBlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeBlogMapper {
    long countByExample(TypeBlogExample example);

    int deleteByExample(TypeBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeBlog record);

    int insertSelective(TypeBlog record);

    List<TypeBlog> selectByExample(TypeBlogExample example);

    TypeBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeBlog record, @Param("example") TypeBlogExample example);

    int updateByExample(@Param("record") TypeBlog record, @Param("example") TypeBlogExample example);

    int updateByPrimaryKeySelective(TypeBlog record);

    int updateByPrimaryKey(TypeBlog record);
}