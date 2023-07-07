package pers.ervinse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.Photo;
import pers.ervinse.domain.User;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.PhotoMapper;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.PhotoUtils;
import pers.ervinse.utils.UserContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PhotoMapper photoMapper;


    @Autowired
    public UserController(UserService userService, PhotoMapper photoMapper) {
        this.userService = userService;
        this.photoMapper = photoMapper;
    }

    /**
     * 根据账号和密码登录
     *
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true, 否则返回false
     */
    @LogPrint(title = "登录")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody User user) {

        log.info("login :" + user);
        return userService.login(user);
    }

    /**
     * 根据账号和密码登录
     *
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true, 否则返回false
     */
    @LogPrint(title = "注册")
    @PostMapping("/register")
    public ApiResponse<Integer> register(@RequestBody User user) {
        log.info("register :" + user);
        int state = userService.register(user);
        switch (state) {
            case 1:
                return ApiResponse.success(200, 1);
            case 0:
                return ApiResponse.fail(202, "注册失败因为账号已经存在");
            case -1:
                return ApiResponse.fail(201, "注册失败因为账号信息输入不全");
            default:
                return ApiResponse.fail(250, "未知错误");
        }

    }


    /**
     * 获取描述
     *
     * @param UserAccount 用户账号
     * @return ApiResponse<User> 用户所有信息
     */
    @LogPrint(title = "获取个人信息")
    @GetMapping("/getDescription/{UserAccount}")
    public ApiResponse<User> getUserInfo(@PathVariable String UserAccount) {
        log.info("getUserInfo :" + UserAccount);
        User userInfo = userService.getUserInfo(UserAccount);
        if (userInfo == null) return ApiResponse.fail(201, "没有该账号");
        return ApiResponse.success(userInfo);
    }

    @LogPrint(title = "添加地址")
    @PostMapping("/location/add")
    public ApiResponse addUserLocation(@RequestBody Address address) {
        address.setUserID(UserContextUtil.get().getUserID());
        return userService.addUserLocation(address);
    }

    @LogPrint(title = "获取地址")
    @GetMapping("/location/all")
    public ApiResponse<List<Address>> getUserLocation() {
        return ApiResponse.success(userService.getUserLocation());
    }

    @LogPrint(title = "修改地址")
    @PutMapping("/location")
    public ApiResponse updateUserLocation(@RequestBody Address address) {
        return userService.updateUserLocation(address);
    }

    @LogPrint(title = "上传头像")
    @PostMapping("/head")
    public ApiResponse updateUserHead(HttpServletRequest request, HttpServletResponse response) {
        return userService.addUserPhoto(request, response);
    }

    @LogPrint(title = "获取头像")
    @GetMapping("/head")
    public ApiResponse<Photo> getUserHead() {
        Photo userHead = userService.getUserHead();
        if (userHead == null) {
            ApiResponse.fail(ResponseCode.HEAD_NOT_EXIT);
        }
        return ApiResponse.success(userHead);
    }

    @LogPrint(title = "注销账号")
    @DeleteMapping("/unsubscribe")
    public ApiResponse unsubscribeAccount() {
        return userService.unsubscribeAccount();
    }
}
