package pers.ervinse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.ervinse.controller.UserController;
import pers.ervinse.domain.Goods;
import pers.ervinse.domain.User;
import pers.ervinse.mapper.GoodsMapper;
import pers.ervinse.mapper.UserMapper;
import pers.ervinse.service.UserService;

import java.sql.Wrapper;

@SpringBootTest
class MedicinesMallApplicationBackendServiceApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount","123456");
        wrapper.eq("UserPassword","123456789");
        System.out.println(userMapper.searchAllByUserAccountAndUserPasswordUser("123456","123456789"));
    }
    @Test
    void testRegister(){
        System.out.println(userService.getUserInfo("123456").getUserExtendInfo());

    }
    @Test
    void testGetGoods(){
        for (Goods selectHotGood : goodsMapper.selectHotGoods()) {
            System.out.println(selectHotGood.getCommodityName());
        }
    }

}
