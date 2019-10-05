package atlan.ceer.mapper;

import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TagBlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagBlogMapper {
    long countByExample(TagBlogExample example);

    int deleteByExample(TagBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagBlog record);

    int insertSelective(TagBlog record);

    List<TagBlog> selectByExample(TagBlogExample example);

    TagBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagBlog record, @Param("example") TagBlogExample example);

    int updateByExample(@Param("record") TagBlog record, @Param("example") TagBlogExample example);

    int updateByPrimaryKeySelective(TagBlog record);

    int updateByPrimaryKey(TagBlog record);
}