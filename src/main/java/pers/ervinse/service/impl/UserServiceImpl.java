package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.*;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.*;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.JwtUtil;
import pers.ervinse.utils.PhotoUtils;
import pers.ervinse.utils.UserContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final PhotoMapper photoMapper;
    private final UserPhotoMapper userPhotoMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final OrderMapper orderMapper;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, AddressMapper addressMapper, PhotoMapper photoMapper, UserPhotoMapper userPhotoMapper, ShoppingCartMapper shoppingCartMapper, OrderMapper orderMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.photoMapper = photoMapper;
        this.userPhotoMapper = userPhotoMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.orderMapper = orderMapper;

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
    public ApiResponse addUserPhoto(HttpServletRequest request, HttpServletResponse response) {
        String path = "src/main/resources/UserHeadPhoto/" + UserContextUtil.get().getUserID() + ".png";
        Photo photo = new Photo();
        photo.setPhotoAddress(path);
        photo.setPhotosID(photoMapper.selectCount(null) + 1);
        photoMapper.insert(photo);
        PhotoUtils.savePhoto(request, path);
        QueryWrapper<User_Photo> QueryWrapper = new QueryWrapper<>();
        UpdateWrapper<User_Photo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("UserID", UserContextUtil.get().getUserID());
        updateWrapper.set("PhotosID", photo.getPhotosID());
        QueryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        if (!userPhotoMapper.exists(QueryWrapper))
            userPhotoMapper.insert(new User_Photo(UserContextUtil.get().getUserID(), photo.getPhotosID()));
        else
            userPhotoMapper.update(null, updateWrapper);
        return ApiResponse.success();
    }

    @Override
    public Photo getUserHead() {
        QueryWrapper<User_Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        User_Photo userPhoto = userPhotoMapper.selectOne(queryWrapper);
        QueryWrapper<Photo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("PhotosID", userPhoto.getPhotosID());
        Photo photo = photoMapper.selectOne(queryWrapper1);
        photo.setPhotoBytes(new String(PhotoUtils.convertPhotoToByte(photo.getPhotoAddress())));
        return photo;
    }

    @Override
    public ApiResponse unsubscribeAccount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        userMapper.delete(queryWrapper);
        QueryWrapper<ShoppingCart> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("UserID", UserContextUtil.get().getUserID());
        shoppingCartMapper.delete(queryWrapper1);
        QueryWrapper<Order> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("UserID", UserContextUtil.get().getUserID());
        orderMapper.delete(queryWrapper2);
        return ApiResponse.success();
    }

}
