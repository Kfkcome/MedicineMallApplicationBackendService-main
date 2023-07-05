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

    @GetMapping("list")
    public ApiResponse listShoppingCart() {
        Integer userID = UserContextUtil.get().getUserID();

        return shoppingCartService.listByUserId(userID);

    }

    @PostMapping
    public ApiResponse addCommodity(@RequestBody CommodityDto commodityDto) {
        return shoppingCartService.addCommodity(commodityDto);
    }
}
