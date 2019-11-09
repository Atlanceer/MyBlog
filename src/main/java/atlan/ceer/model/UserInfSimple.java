package atlan.ceer.model;

import java.io.Serializable;

/**
 * cookie里面存放的字段
 */
public class UserInfSimple implements Serializable {
    //图片的服务器地址
    private String preUrl = "http://localhost:8080";
    //用户名
    private String username;
    //用户id
    private Integer id;
    //头像
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return preUrl+avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "BlogCookie{" +
                "username='" + username + '\'' +
                ", userId='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
