package atlan.ceer.service.impl;

import atlan.ceer.mapper.*;
import atlan.ceer.model.QueryPage;
import atlan.ceer.pojo.*;
import atlan.ceer.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagToBlogMapper tagToBlogMapper;
    @Autowired
    private TypeToBlogMapper typeToBlogMapper;

    @Override
    public QueryPage getList(Map<String, Object> map) {
        int currentPage = Integer.valueOf((String)map.get("currentPage"));
        if (currentPage==1){
            map.put("beginNum",0);
        }else {
            map.put("beginNum",(currentPage-1)*10);
        }
        int totalCount = 0;

        List queryList = null;
        //判断查询的类型
        if (((String)map.get("queryType")).equals("type")){
            TypeBlogExample typeBlogExample = new TypeBlogExample();
            totalCount = (int) typeBlogMapper.countByExample(typeBlogExample);
            //查询列表
            queryList = queryMapper.getTypeList(map);
        }else if (((String)map.get("queryType")).equals("tag")){
            TagBlogExample tagBlogExample = new TagBlogExample();
            totalCount = (int) tagBlogMapper.countByExample(tagBlogExample);
            //查询列表
            queryList = queryMapper.getTagList(map);
        }else {
            BlogExample blogExample = new BlogExample();
            totalCount = (int) blogMapper.countByExample(blogExample);
        }

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

        //List<TypeBlog> typeBlogs = queryMapper.getTypeList(map);
        queryPage.setPageList(queryList);
        return queryPage;
    }

    @Override
    public List getListForBlog(Map<String, Object> map) {
        //判断查询的类型
        if (((String)map.get("queryType")).equals("type")){
            TypeBlogExample typeBlogExample = new TypeBlogExample();
            //查询列表
            return queryMapper.getTypeList(map);
        }else/* if (((String)map.get("queryType")).equals("tag"))*/{
            TagBlogExample tagBlogExample = new TagBlogExample();
            //查询列表
            return queryMapper.getTagList(map);
        }
    }

    @Override
    public boolean addType(String typeName, int userId) {
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
    public boolean changeType(Integer typeId, String typeName) {
        //更新的字段
        TypeBlog typeBlog = new TypeBlog();
        typeBlog.setNameType(typeName);
        //时间字段


        TypeBlogExample typeBlogExample = new TypeBlogExample();
        TypeBlogExample.Criteria criteria = typeBlogExample.createCriteria();
        criteria.andIdEqualTo(typeId);
        typeBlogMapper.updateByExampleSelective(typeBlog, typeBlogExample);
        return true;
    }

    @Override
    public boolean deleteType(Integer typeId) {
        try {
            typeBlogMapper.deleteByPrimaryKey(typeId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean addTag(String tagName, int userId) {
        try {
            TagBlog tagBlog = new TagBlog();
            tagBlog.setNameTag(tagName);
            tagBlogMapper.insert(tagBlog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeTag(Integer tagId, String tagName) {
        //更新的字段
        TagBlog tagBlog = new TagBlog();
        tagBlog.setNameTag(tagName);
        //时间字段


        TagBlogExample tagBlogExample = new TagBlogExample();
        TagBlogExample.Criteria criteria = tagBlogExample.createCriteria();
        criteria.andIdEqualTo(tagId);
        tagBlogMapper.updateByExampleSelective(tagBlog, tagBlogExample);
        return true;
    }

    @Override
    public boolean deleteTag(Integer tagId) {
        try {
            tagBlogMapper.deleteByPrimaryKey(tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public QueryPage getBlogList(Map map) {
        return null;
    }

    //添加博客
    @Transactional
    @Override
    public boolean addBlog(Blog blog, int type, int[] tag) {
        try {
            //添加进博客表
            blogMapper.insertSelective(blog);
            int blogId = blog.getId();

            //添加进博客—分类表
            TypeToBlog typeToBlog = new TypeToBlog();
            typeToBlog.setIdBlog(blogId);
            typeToBlog.setIdType(type);
            typeToBlog.setGmtCreate(blog.getGmtCreate());
            typeToBlog.setGmtModified(blog.getGmtModified());
            typeToBlogMapper.insertSelective(typeToBlog);

            //int temp = 1/0;
            //添加进博客—标签表
            for(int i=0;i<tag.length;i++){
                TagToBlog tagToBlog = new TagToBlog();
                tagToBlog.setIdBlog(blogId);
                tagToBlog.setIdTag(tag[i]);
                tagToBlog.setGmtCreate(blog.getGmtCreate());
                tagToBlog.setGmtModified(blog.getGmtModified());

                tagToBlogMapper.insertSelective(tagToBlog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加博客失败:用户id："+blog.getIdUser());
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeBlog(Map map) {
        return false;
    }

    @Override
    public boolean deleteBlog(Integer blogId) {
        return false;
    }
}
