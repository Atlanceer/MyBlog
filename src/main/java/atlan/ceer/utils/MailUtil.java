package atlan.ceer.utils;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 使用163邮箱发送验证码
 */
public class MailUtil {
    //发送方
    private String fromUser = "AtlanBlog";
    //邮箱账号密码
    private String username = "atlanblog@163.com";
    private String password = "atlan1998";

    //邮件主机名
    private String mailHost = "smtp.163.com";
    //发送内容
    private String mailMessagePre = "<p>欢迎注册，您的验证码为:";
    private String getMailMessageTail = "情尽快填写</p>";

    public boolean sendCodeMail(String emailAddress, String code){
        // 定义properties对象，设置环境信息
        Properties properties = new Properties();

        //指定连接的邮件服务器的主机名
        properties.setProperty("mail.smtp.host", mailHost);

        //指定客户端是否要向邮件服务器提交验证
        properties.setProperty("mail.smtp.auth", "true");

        //指定邮件发送协议
        properties.setProperty("mail.transport.protocol", "smtp");

        //设置session对象
        Session session = Session.getDefaultInstance(properties);

        //当设置为true，JavaMail AP就会将其运行过程和邮件服务器的交互命令信息输出到console中，用于JavaMail的调试
        session.setDebug(true);

        try {
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);

            //设置邮件发送方
            message.setFrom(new InternetAddress(username));

            //设置邮件主题 标题
            message.setSubject("邮箱验证码");

            //设置邮件发送内容
            message.setContent(mailMessagePre+" "+code+" "+getMailMessageTail,"text/html;charset=utf-8");
            Transport transport = session.getTransport();

            //连接邮件服务器
            transport.connect(mailHost, 25, username, password);
            transport.sendMessage(message, new Address[]{new InternetAddress(emailAddress)});
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        MailUtil mailUtil = new MailUtil();
        if (mailUtil.sendCodeMail("tolankai@163.com","1163")) {
            System.out.println("success");
        }else {
            System.out.println("false");
        }
    }
}
