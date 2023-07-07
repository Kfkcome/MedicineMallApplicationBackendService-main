package pers.ervinse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.Order;
import pers.ervinse.domain.Review;
import pers.ervinse.domain.dto.LogisticsInfoAll;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.service.OrderService;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

@Slf4j
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
    @LogPrint
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
    @LogPrint
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
    @LogPrint
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
    @LogPrint(title = "删除订单")
    @DeleteMapping("/{OrderID}")
    ApiResponse deleteOrder(@PathVariable Integer OrderID) {
        return orderService.deleteOrder(OrderID);
    }

    @LogPrint(title = "支付订单")
    @PutMapping("/pay/{OrderID}")
    ApiResponse payOrder(@PathVariable Integer OrderID) {
        return orderService.payOrder(OrderID);
    }

    @LogPrint(title = "评论订单")
    @PostMapping("/review")
    ApiResponse reviewOrder(@RequestBody Review review) {
        return orderService.reviewToOrder(review);
    }

    @LogPrint(title = "确认收货")
    @PutMapping("/confirm/{OrderID}")
    ApiResponse confirmOrder(@PathVariable Integer OrderID) {
        return orderService.confirmOrder(OrderID);
    }

    @LogPrint(title = "获取订单地址")
    @GetMapping("/address/{OrderID}")
    ApiResponse<LogisticsInfoAll> getOrderAddress(@PathVariable Integer OrderID) {
        LogisticsInfoAll orderLogistic = orderService.getOrderLogistic(OrderID);
        if (orderLogistic == null) {
            return ApiResponse.fail(ResponseCode.ORDER_NOT_EXIT);
        }
        return ApiResponse.success(orderLogistic);
    }
}
