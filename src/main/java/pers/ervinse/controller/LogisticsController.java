package pers.ervinse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.domain.Logistics;
import pers.ervinse.domain.Order;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.OrderMapper;
import pers.ervinse.service.LogisticsService;
import pers.ervinse.utils.ApiResponse;

@RestController
@RequestMapping("/Logistics")
public class LogisticsController {
    private final LogisticsService logisticsService;

    @Autowired
    public LogisticsController(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }


    /**
     * 得到物流
     *
     * @param OrderID 订单id
     * @return {@link ApiResponse}<{@link Logistics}>
     */
    @GetMapping("/{OrderID}")
    ApiResponse<Logistics> getLogistic(@PathVariable Integer OrderID) {
        Logistics logistic = logisticsService.getLogistic(OrderID);
        if (logistic == null) {
            return ApiResponse.fail(ResponseCode.LOGISTICS_NOT_EXIT);
        }
        return ApiResponse.success(logistic);
    }
}
