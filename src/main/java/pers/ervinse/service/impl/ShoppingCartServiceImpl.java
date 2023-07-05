package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.ShoppingCart;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.exception.SystemException;
import pers.ervinse.mapper.ShoppingCartMapper;
import pers.ervinse.mapper.ShoppingcartCommodityMapper;
import pers.ervinse.service.ShoppingCartService;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

/**
 * @author YangLin
 * @description 针对表【shoppingcart_info】的数据库操作Service实现
 * @createDate 2023-07-05 11:02:16
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingcartCommodityMapper shoppingcartCommodityMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public ApiResponse listByUserId(Integer userID) {
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserID, userID);
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(wrapper);
        if (shoppingCart == null) {
            throw new SystemException(ResponseCode.DONOT_HAVE_CART);
        }
        List list = shoppingcartCommodityMapper.selectListByShoppingCartID(shoppingCart.getShoppingCartID());

        return ApiResponse.success(list);
    }
}




