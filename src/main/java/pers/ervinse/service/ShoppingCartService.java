package pers.ervinse.service;

import pers.ervinse.domain.dto.CommodityDto;
import pers.ervinse.utils.ApiResponse;

/**
 * @author YangLin
 * @description 针对表【shoppingcart_info】的数据库操作Service
 * @createDate 2023-07-05 11:02:16
 */
public interface ShoppingCartService {

    ApiResponse listByUserId(Integer userID);

    ApiResponse addCommodity(CommodityDto commodityDto);

    ApiResponse deleteCommodity(Integer commodityID);

    ApiResponse updateCommodity(CommodityDto commodityDto);
}
