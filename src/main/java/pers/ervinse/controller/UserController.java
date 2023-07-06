package pers.ervinse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.User;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.UserContextUtil;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据账号和密码登录
     *
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true, 否则返回false
     */
    @LogPrint
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
    @LogPrint
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
    @LogPrint
    @GetMapping("/getDescription/{UserAccount}")
    public ApiResponse<User> getUserInfo(@PathVariable String UserAccount) {
        log.info("getUserInfo :" + UserAccount);
        User userInfo = userService.getUserInfo(UserAccount);
        if (userInfo == null) return ApiResponse.fail(201, "没有该账号");
        return ApiResponse.success(userInfo);
    }

    @PostMapping("/location/add")
    public ApiResponse addUserLocation(@RequestBody Address address) {
        address.setUserID(UserContextUtil.get().getUserID());
        return userService.addUserLocation(address);
    }

    @GetMapping("/location/all")
    public ApiResponse<List<Address>> getUserLocation() {
        return ApiResponse.success(userService.getUserLocation());
    }

    @PutMapping("/location")
    public ApiResponse updateUserLocation(@RequestBody Address address) {
        return userService.updateUserLocation(address);
    }
    @PostMapping("head")
    public ApiResponse updateUserHead(){return null;}
}
