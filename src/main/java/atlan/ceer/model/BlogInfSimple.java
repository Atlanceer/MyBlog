package atlan.ceer.model;

import atlan.ceer.utils.TimeUtil;

import java.util.Arrays;
import java.util.Date;

public class BlogInfSimple {
    //图片的服务器地址
    private String preUrl = "http://localhost:8080";
    //时间处理工具
    TimeUtil timeUtil = new TimeUtil();

    private Integer id;//博客id

    private Integer idUser;//用户id

    private String title;//标题

    private String content;//博客内容

    private String firstPicture;//首图地址

    private String nameType;//类型

    private Integer[] tags;//标签

    private Integer views;//浏览量

    private Date gmtCreate;//创建时间

    private Date gmtModified;//修改时间

    private Boolean isPublish;//是否发布

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return preUrl+firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Integer[] getTags() {
        return tags;
    }

    public void setTags(Integer[] tags) {
        this.tags = tags;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getGmtCreate() {
        return timeUtil.getTimeFormat(gmtCreate);
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return timeUtil.getTimeFormat(gmtModified);
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    @Override
    public String toString() {
        return "BlogInfSimple{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", nameType='" + nameType + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", views=" + views +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", isPublish=" + isPublish +
                '}';
    }
}
