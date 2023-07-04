package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.User;
import pers.ervinse.mapper.UserMapper;
import pers.ervinse.service.UserService;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名和密码判断是否登录成功
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true,否则返回false
     */
    @Override
    public boolean login(User user){
        User userByLogin = userMapper.searchAllByUserAccountAndUserPasswordUser
                (user.getUserAccount(), user.getUserPassword());
        return userByLogin != null;
    }


    /**
     * 根据用户名判断是否重名
     * 无重名添加数据到数据库
     * @param user 注册成功返回true ,注册失败返回false
     * @return
     */
    @Override
    public int register(User user){
        if(user==null){return -1;}//如果前端传入的user为空
        else if(user.getUserName()==null||user.getUserAccount()==null||user.getUserPassword()==null){return -1;}
        log.info("register :" + user);
        //根据注册的名字查询有无同名
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount", user.getUserAccount());
        List<User> userList = userMapper.selectList(wrapper);
        //查到同名
        if (userList.size() > 0){
            return 0;
        //未查到同名
        }else {
            int affectRows = userMapper.insert(user);
            log.info("插入成功.影响了" + affectRows + "行");
            return 1;
        }
    }


    @Override
    public String getDescription(String name){

        log.info("getDescription :" + name);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount", name);
        User userBySelect = userMapper.selectOne(wrapper);
        return userBySelect.getUserExtendInfo();

    }




}
