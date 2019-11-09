package atlan.ceer.service.impl;

import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.mapper.UserMapper;
import atlan.ceer.model.UserInfSimple;
import atlan.ceer.pojo.UserExample;
import atlan.ceer.service.UserService;
import atlan.ceer.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QueryMapper queryMapper;
    @Autowired
    private Md5Util md5Util;

    @Override
    public boolean login(String username, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        //得到加密之后的值
        criteria.andPasswordEqualTo(md5Util.getMd5(password));
        if (userMapper.selectByExample(userExample).isEmpty()) {
            return false;
        }else {
            return true;
        }
        /*long count = userMapper.countByExample(userExample);
        if (count>0){
            return true;
        }else {
            return false;
        }*/
    }

    @Override
    public boolean register() {
        return false;
    }

    //获取用户简易信息
    @Override
    public UserInfSimple getUserInfSimple(Map<String, Object> map) {
        try {
            UserInfSimple userInfSimple = queryMapper.getUserInfSimple(map);
            return userInfSimple;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询用户简易信息失败");
            return null;
        }
    }

    /*public UserInfSimple getUserInfSimpleById(int id){
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        return queryMapper.getUserInfSimple(map);
    }*/
}
