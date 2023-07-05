package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.ervinse.domain.ShoppingcartCommodity;
import pers.ervinse.domain.vo.CommodityVo;

import java.util.List;

/**
 * @author YangLin
 * @description 针对表【shoppingcart_commodity】的数据库操作Mapper
 * @createDate 2023-07-05 11:12:26
 * @Entity .domain.ShoppingcartCommodity
 */
public interface ShoppingcartCommodityMapper extends BaseMapper<ShoppingcartCommodity> {

    List<CommodityVo> selectListByShoppingCartID(Integer shoppingCartID);

    int update(ShoppingcartCommodity shoppingcartCommodity);
}



