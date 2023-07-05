package pers.ervinse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
