package atlan.ceer.model;

import java.io.Serializable;

/**
 * cookie里面存放的字段
 */
public class UserInfSimple implements Serializable {
    //用户名
    private String username;
    //用户id
    private String id;
    //头像
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
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
