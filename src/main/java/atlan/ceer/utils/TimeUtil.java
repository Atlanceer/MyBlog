package atlan.ceer.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("timeUtil")
public class TimeUtil {
    public String getTimeFormat(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()));
    }
    public Date getTime(){
        return new Date(System.currentTimeMillis());
    }
}
