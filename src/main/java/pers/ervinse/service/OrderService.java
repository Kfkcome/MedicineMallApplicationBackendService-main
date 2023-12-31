package pers.ervinse.service;

import pers.ervinse.domain.Address;
import pers.ervinse.domain.Order;
import pers.ervinse.domain.Review;
import pers.ervinse.domain.dto.LogisticsInfoAll;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

public interface OrderService {
    ApiResponse generateOrder(Integer Commodity, Integer CommodityNum);

    ApiResponse generateOrderFromCart(Integer Commodity, Integer CommodityNum);

    Order getOrderInfo(Integer OrderID);

    List<Order> getAllUserOrder();

    ApiResponse deleteOrder(Integer OrderID);

    ApiResponse payOrder(Integer OrderID);

    ApiResponse reviewToOrder(Review review);

    ApiResponse confirmOrder(Integer OrderID);

    LogisticsInfoAll getOrderLogistic(Integer OrderID);
}
