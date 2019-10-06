package atlan.ceer.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5Util {

    //等到MD5加密之后的值
    public String getMd5(String content){
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }

    public static void main(String[] args) {
        Md5Util md5Util = new Md5Util();
        System.out.println(md5Util.getMd5("atlan1998"));
    }
}
