package atlan.ceer.mapper;

import atlan.ceer.pojo.TagToBlog;
import atlan.ceer.pojo.TagToBlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagToBlogMapper {
    long countByExample(TagToBlogExample example);

    int deleteByExample(TagToBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagToBlog record);

    int insertSelective(TagToBlog record);

    List<TagToBlog> selectByExample(TagToBlogExample example);

    TagToBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagToBlog record, @Param("example") TagToBlogExample example);

    int updateByExample(@Param("record") TagToBlog record, @Param("example") TagToBlogExample example);

    int updateByPrimaryKeySelective(TagToBlog record);

    int updateByPrimaryKey(TagToBlog record);
}