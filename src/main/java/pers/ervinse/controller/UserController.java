package pers.ervinse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ervinse.domain.User;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.LogPrint;

/**
 * 用户
 */
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
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true,否则返回false
     */
    @LogPrint
    @PostMapping("/login")
    public ApiResponse<Boolean> login(@RequestBody User user){
        log.info("login :" + user);
        return ApiResponse.success(userService.login(user));
    }

    /**
     * 根据账号和密码登录
     * @param user 包含用户名和密码的对象
     * @return 登录成功返回true,否则返回false
     */
    @LogPrint
    @PostMapping("/register")
    public ApiResponse<Integer> register(@RequestBody User user){
        log.info("register :" + user);
        int state= userService.register(user);
        switch (state){
            case 1:return ApiResponse.success(200,1);
            case 0:return ApiResponse.fail(202,"注册失败因为账号已经存在");
            case -1:return ApiResponse.fail(201,"注册失败因为账号信息输入不全");
            default:return ApiResponse.success();
        }

    }


    /**
     * 获取描述
     * @param name
     * @return
     */
    @LogPrint
    @GetMapping("/getDescription/{name}")
    public ApiResponse<String> getDescription(@PathVariable String name){
        log.info("getDescription :" + name);
        return ApiResponse.success(userService.getDescription(name));
    }
}
