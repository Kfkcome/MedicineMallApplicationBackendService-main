package pers.ervinse.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ervinse.domain.Order;

/**
 * @author YangLin
 * @description 针对表【order_info】的数据库操作Mapper
 * @createDate 2023-07-05 10:55:30
 * @Entity .domain.Order
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




