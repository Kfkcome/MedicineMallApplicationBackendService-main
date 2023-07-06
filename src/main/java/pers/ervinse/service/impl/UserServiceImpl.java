package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.controller.UserController;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.User;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.AddressMapper;
import pers.ervinse.mapper.UserMapper;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.JwtUtil;
import pers.ervinse.utils.UserContextUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, AddressMapper addressMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 根据用户名和密码判断是否登录成功
     *
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true, 否则返回false
     */
    @Override
    public ApiResponse login(User user) {
        User userByLogin = userMapper.searchAllByUserAccountAndUserPasswordUser
                (user.getUserAccount(), user.getUserPassword());
        Map<String, Object> map = new HashMap<>();
        if (userByLogin != null) {
            String token = jwtUtil.generateToken(userByLogin);
            map.put("token", token);
        } else {
            return ApiResponse.fail(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return ApiResponse.success(map);
    }


    /**
     * 根据用户名判断是否重名
     * 无重名添加数据到数据库
     *
     * @param user 注册成功返回true ,注册失败返回false
     * @return state -1：信息有误 0：账号已经存在 1：注册成功
     */
    @Override
    public int register(User user) {
        if (user == null || user.getUserName() == null
                || user.getUserAccount() == null || user.getUserPassword() == null) {
            return -1;
        }//如果前端传入的user为空
        log.info("register :" + user);
        //根据注册的名字查询有无同名
        List<User> userList = userMapper.searchAllByUserAccountUserList(user.getUserAccount());
        //查到同名
        if (userList.size() > 0) {
            return 0;
            //未查到同名
        } else {
            int affectRows = userMapper.insert(user);
            log.info("插入成功.影响了" + affectRows + "行");
            return 1;
        }
    }


    /**
     * 获取用户信息
     *
     * @param UserAccount 用户账号
     * @return {@link User}
     */
    @Override
    public User getUserInfo(String UserAccount) {

        log.info("getDescription :" + UserAccount);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount", UserAccount);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public ApiResponse addUserLocation(Address address) {
        address.setAddressID(null);
        return ApiResponse.success(addressMapper.insert(address));
    }

    @Override
    public List<Address> getUserLocation() {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        return addressMapper.selectList(queryWrapper);
    }

    @Override
    public ApiResponse updateUserLocation(Address address) {
        if (address.getUserID() != null && address.getUserID().equals(UserContextUtil.get().getUserID())) {
            return ApiResponse.fail(ResponseCode.UPDATE_ERROR);
        }
        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("UserID", UserContextUtil.get().getUserID()).eq("AddressID", address.getAddressID());
        int update = addressMapper.update(address, updateWrapper);
        if (update != 0)
            return ApiResponse.success(update);
        else return ApiResponse.fail(ResponseCode.UPDATE_ERROR_NOT_EXIT);
    }

    @Override
    public ApiResponse addUserPhoto() {
        return null;
    }


}
