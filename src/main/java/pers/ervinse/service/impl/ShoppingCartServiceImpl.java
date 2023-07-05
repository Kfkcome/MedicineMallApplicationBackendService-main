package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.ShoppingCart;
import pers.ervinse.domain.ShoppingcartCommodity;
import pers.ervinse.domain.dto.CommodityDto;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.exception.SystemException;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.mapper.ShoppingCartMapper;
import pers.ervinse.mapper.ShoppingcartCommodityMapper;
import pers.ervinse.service.ShoppingCartService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.ObjectUtil;
import pers.ervinse.utils.UserContextUtil;

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

    @Autowired
    private CommodityMapper commodityMapper;

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

    @Transactional
    @Override
    public ApiResponse addCommodity(CommodityDto commodityDto) {

        ShoppingcartCommodity shoppingcartCommodity = getShoppingcartCommodity(commodityDto);

        int i = shoppingcartCommodityMapper.insert(shoppingcartCommodity);
        if (i == 0) {
            throw new SystemException(ResponseCode.FAILURE);
        }
        return ApiResponse.success();
    }

    @Transactional
    @Override
    public ApiResponse deleteCommodity(Integer commodityID) {

        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getUserID, UserContextUtil.get().getUserID()));

        Integer shoppingCartID = shoppingCart.getShoppingCartID();

        LambdaQueryWrapper<ShoppingcartCommodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingcartCommodity::getCommodityid, commodityID);
        wrapper.eq(ShoppingcartCommodity::getShoppingcartid, shoppingCartID);

        int delete = shoppingcartCommodityMapper.delete(wrapper);

        if (delete == 0)
            throw new SystemException(ResponseCode.COMMODITY_NOT_EXITS);
        return ApiResponse.success();
    }

    @Transactional
    @Override
    public ApiResponse updateCommodity(CommodityDto commodityDto) {

        ShoppingcartCommodity shoppingcartCommodity = getShoppingcartCommodity(commodityDto);
        int i = shoppingcartCommodityMapper.update(shoppingcartCommodity);
        if (i == 0) {
            throw new SystemException(ResponseCode.COMMODITY_NOT_EXITS);
        }
        return ApiResponse.success();
    }

    /**
     * 获取将更新的购物车商品
     *
     * @param commodityDto
     * @return
     */
    private ShoppingcartCommodity getShoppingcartCommodity(CommodityDto commodityDto) {
        if (ObjectUtil.isNull(commodityDto)) {
            throw new SystemException(ResponseCode.PARAM_ERROR);
        }
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserID, UserContextUtil.get().getUserID());
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(wrapper);

        ShoppingcartCommodity shoppingcartCommodity = new ShoppingcartCommodity();

        Integer commodityID = commodityDto.getCommodityID();

        Commodity commodity = commodityMapper.selectOne(new LambdaQueryWrapper<Commodity>()
                .eq(Commodity::getCommodityID, commodityID));
        if (commodity == null) {
            throw new SystemException(ResponseCode.COMMODITY_NOT_EXITS);
        }

        shoppingcartCommodity.setShoppingcartid(shoppingCart.getShoppingCartID());
        shoppingcartCommodity.setCommodityid(commodityDto.getCommodityID());
        shoppingcartCommodity.setCommoditynum(commodityDto.getCommodityNum());
        return shoppingcartCommodity;
    }
}




