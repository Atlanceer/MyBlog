package atlan.ceer.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlogInfForModify {
    //图片的服务器地址
    private String preUrl = "http://localhost:8080";

    private Integer id;

    //private Integer idUser;

    private String title;

    private String content;

    private String firstPicture;

    private String flag;

    //private Integer views;

    //private Boolean isAppreciation;

    private Boolean isShare;

    private Boolean isComment;

    private Boolean isPublish;

    private Date gmtCreate;

    private Date gmtModified;

    private List<Integer> tags;

    private List<String> tagsName;

    private int type;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(firstPicture==null||firstPicture.equals(""))){
            return preUrl+firstPicture;
        }else {
            return firstPicture;
        }
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getShare() {
        return isShare;
    }

    public void setShare(Boolean share) {
        isShare = share;
    }

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(List<String> tagsName) {
        this.tagsName = tagsName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "BlogInfForModify{" +
                "preUrl='" + preUrl + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", isShare=" + isShare +
                ", isComment=" + isComment +
                ", isPublish=" + isPublish +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", tags=" + tags +
                ", tagsName=" + tagsName +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
