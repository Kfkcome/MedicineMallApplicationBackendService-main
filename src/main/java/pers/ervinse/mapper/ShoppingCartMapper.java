package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ervinse.domain.ShoppingCart;

/**
 * @author YangLin
 * @description 针对表【shoppingcart_info】的数据库操作Mapper
 * @createDate 2023-07-05 11:02:16
 * @Entity .domain.Shoppingcart
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




