package atlan.ceer.model;

import java.util.Date;

public class Blog {

    //
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String firstPictures;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏开启
    private boolean appreciation;
    //版权开启
    private boolean shareStatment;
    //评论开启
    private boolean commentable;
    //发布
    private boolean publish;
    //创建时间
    private Date gmtCreate;
    //更新时间
    private Date gmtModified;
}
