package pers.ervinse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ervinse.domain.Order;
import pers.ervinse.domain.Review;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.service.OrderService;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 添加订单
     *
     * @param CommodityID  商品id
     * @param CommodityNum 商品num
     * @return {@link ApiResponse}
     */
    @PostMapping("/add")
    ApiResponse addOrder(Integer CommodityID, Integer CommodityNum) {
        return orderService.generateOrder(CommodityID, CommodityNum);
    }

    /**
     * 获取订单信息
     *
     * @param orderID 订单id
     * @return {@link ApiResponse}<{@link Order}>
     */
    @GetMapping("/info/{orderID}")
    ApiResponse<Order> getOrderInfo(@PathVariable Integer orderID) {
        Order orderInfo = orderService.getOrderInfo(orderID);
        if (orderInfo == null) return ApiResponse.fail(ResponseCode.CANNOT_FIND_ORDER);
        return ApiResponse.success(orderInfo);
    }

    /**
     * 把所有订单
     *
     * @return {@link ApiResponse}<{@link List}<{@link Order}>>
     */
    @GetMapping("/all")
    ApiResponse<List<Order>> getAllOrder() {
        return ApiResponse.success(orderService.getAllUserOrder());
    }

    /**
     * 删除订单
     *
     * @param OrderID 订单id
     * @return {@link ApiResponse}
     */
    @DeleteMapping("/{OrderID}")
    ApiResponse deleteOrder(@PathVariable Integer OrderID) {
        return orderService.deleteOrder(OrderID);
    }

    @PutMapping("/pay/{OrderID}")
    ApiResponse payOrder(@PathVariable Integer OrderID) {
        return orderService.payOrder(OrderID);
    }

    @PostMapping("/review")
    ApiResponse reviewOrder(@RequestBody Review review) {
        return orderService.reviewToOrder(review);
    }
}
