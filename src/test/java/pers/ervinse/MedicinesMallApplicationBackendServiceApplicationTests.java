package pers.ervinse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.Order;
import pers.ervinse.domain.User;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.mapper.OrderMapper;
import pers.ervinse.mapper.UserMapper;
import pers.ervinse.service.UserService;
import pers.ervinse.utils.DateTimeUtils;
import pers.ervinse.utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
class MedicinesMallApplicationBackendServiceApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount", "123456");
        wrapper.eq("UserPassword", "123456789");
        System.out.println(userMapper.searchAllByUserAccountAndUserPasswordUser("123456", "123456789"));
    }

    @Test
    void testRegister() {
        System.out.println(userService.getUserInfo("123456").getUserExtendInfo());

    }

    @Test
    void testGetCommodity() {
        for (Commodity selectHotGood : commodityMapper.selectHotCommodity()) {
            System.out.println(selectHotGood.getCommodityName());
        }
    }

    @Test
    void testReadPhoto() {
        System.out.println(Arrays.toString(PhotoUtils.convertPhotoToByte("src/main/resources/MedicinePicture/999感冒灵.png")));

    }

    @Test
    void testDATE() {
        Date now = new Date();
        System.out.println(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
        Order order = new Order();
        order.setOrderTime(now);
        order.setOrderPayState(1);
        order.setOrderFullAmount(500);
        orderMapper.insert(order);
    }

}
