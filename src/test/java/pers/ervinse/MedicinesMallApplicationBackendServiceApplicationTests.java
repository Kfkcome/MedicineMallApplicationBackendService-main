package pers.ervinse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.ervinse.domain.User;
import pers.ervinse.mapper.UserMapper;

import java.sql.Wrapper;

@SpringBootTest
class MedicinesMallApplicationBackendServiceApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("UserAccount","123456");
        wrapper.eq("UserPassword","123456789");
        System.out.println(userMapper.searchAllByUserAccountAndUserPasswordUser("123456","123456789"));
    }

}
