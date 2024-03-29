package atlan.ceer.mapper;

import atlan.ceer.pojo.TypeToBlog;
import atlan.ceer.pojo.TypeToBlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeToBlogMapper {
    long countByExample(TypeToBlogExample example);

    int deleteByExample(TypeToBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeToBlog record);

    int insertSelective(TypeToBlog record);

    List<TypeToBlog> selectByExample(TypeToBlogExample example);

    TypeToBlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeToBlog record, @Param("example") TypeToBlogExample example);

    int updateByExample(@Param("record") TypeToBlog record, @Param("example") TypeToBlogExample example);

    int updateByPrimaryKeySelective(TypeToBlog record);

    int updateByPrimaryKey(TypeToBlog record);
}