package pers.ervinse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ervinse.domain.dto.CommodityDto;
import pers.ervinse.service.ShoppingCartService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.UserContextUtil;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 查询用户购物车所有商品
     *
     * @return
     */
    @GetMapping("list")
    public ApiResponse listShoppingCart() {
        Integer userID = UserContextUtil.get().getUserID();

        return shoppingCartService.listByUserId(userID);

    }

    /**
     * 购物车新增商品
     *
     * @param commodityDto
     * @return
     */
    @PostMapping
    public ApiResponse addCommodity(@RequestBody CommodityDto commodityDto) {
        return shoppingCartService.addCommodity(commodityDto);
    }

    /**
     * 删除购物车某商品
     *
     * @param CommodityID
     * @return
     */
    @DeleteMapping("/{CommodityID}")
    public ApiResponse deleteCommodity(@PathVariable("CommodityID") Integer CommodityID) {
        return shoppingCartService.deleteCommodity(CommodityID);
    }

    /**
     * 更新购物车某商品的数量
     *
     * @param commodityDto
     * @return
     */
    @PutMapping
    public ApiResponse uodateCommodity(@RequestBody CommodityDto commodityDto) {
        return shoppingCartService.updateCommodity(commodityDto);
    }
}
