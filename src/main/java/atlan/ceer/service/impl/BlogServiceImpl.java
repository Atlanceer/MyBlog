package atlan.ceer.service.impl;

import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.mapper.TagBlogMapper;
import atlan.ceer.mapper.TypeBlogMapper;
import atlan.ceer.model.QueryPage;
import atlan.ceer.pojo.TagBlog;
import atlan.ceer.pojo.TypeBlog;
import atlan.ceer.pojo.TypeBlogExample;
import atlan.ceer.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private TypeBlogMapper typeBlogMapper;
    @Autowired
    private TagBlogMapper tagBlogMapper;
    @Autowired
    private QueryMapper queryMapper;

    @Override
    public QueryPage<TypeBlog> getTypeList(Map map) {
        int currentPage = Integer.valueOf((String)map.get("currentPage"));
        if (currentPage==1){
            map.put("beginNum",0);
        }else {
            map.put("beginNum",(currentPage-1)*10);
        }
        TypeBlogExample typeBlogExample = new TypeBlogExample();
        int totalCount = (int) typeBlogMapper.countByExample(typeBlogExample);
        int totalPage;
        if (totalCount<=10){
            totalPage = 1;
        }else{
            if (totalCount%10>0){
                totalPage = totalCount/10+1;
            }else {
                totalPage = totalCount/10;
            }
        }

        QueryPage queryPage = new QueryPage();
        queryPage.setCurrentPage(currentPage);
        queryPage.setTotalCount(totalCount);
        queryPage.setTotalPage(totalPage);

        if (currentPage==totalPage){
            queryPage.setHaveMore(false);
        }else {
            queryPage.setHaveMore(true);
        }

        List<TypeBlog> typeBlogs = queryMapper.getTypeList(map);
        queryPage.setPageList(typeBlogs);
        return queryPage;
    }

    @Override
    public boolean addType(String typeName) {
        try {
            TypeBlog typeBlog = new TypeBlog();
            typeBlog.setNameType(typeName);
            typeBlogMapper.insert(typeBlog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeType(Integer typeId) {
        return false;
    }

    @Override
    public QueryPage<TagBlog> getTagList(Map map) {
        return null;
    }

    @Override
    public boolean addTag(String tagName) {
        return false;
    }

    @Override
    public boolean changeTag(Integer tagId) {
        return false;
    }
}
