package atlan.ceer.pojo;

public class TagBlog {
    private Integer id;

    private String nameTag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag == null ? null : nameTag.trim();
    }
}