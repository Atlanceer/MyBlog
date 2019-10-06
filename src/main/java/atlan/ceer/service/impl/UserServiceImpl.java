package atlan.ceer.service.impl;

import atlan.ceer.mapper.UserMapper;
import atlan.ceer.pojo.UserExample;
import atlan.ceer.service.UserService;
import atlan.ceer.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
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
}
