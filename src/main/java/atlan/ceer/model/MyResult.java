package atlan.ceer.model;

/**
 * 网页响应体模板
 */
public class MyResult {
    //存储数据
    private Object content;
    //存储成功状态
    private boolean status;
    //存储错误或者成功信息
    private String message;
    //各种状态对应的响应码
    private int code;

    public MyResult(Object content, boolean status, String message, int code) {
        this.content = content;
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public MyResult(boolean status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "content=" + content +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
