package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ervinse.domain.User;

import java.util.List;


/**
 * 用户映射器
 *
 * @author kfk
 * @date 2023/07/05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User searchAllByUserAccountAndUserPasswordUser(@Param("UserAccount") String Account,@Param("UserPassword") String Password);
    List<User> searchAllByUserAccountUserList(@Param("UserAccount")String UserAccount);
}
