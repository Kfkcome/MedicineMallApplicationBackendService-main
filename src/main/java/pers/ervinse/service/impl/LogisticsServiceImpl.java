package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Logistics;
import pers.ervinse.domain.Order;
import pers.ervinse.mapper.LogisticsMapper;
import pers.ervinse.mapper.OrderMapper;
import pers.ervinse.service.LogisticsService;

@Service
public class LogisticsServiceImpl implements LogisticsService {
    private final LogisticsMapper logisticsMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public LogisticsServiceImpl(LogisticsMapper logisticsMapper, OrderMapper orderMapper) {
        this.logisticsMapper = logisticsMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Logistics getLogistic(Integer OrderID) {
        QueryWrapper<Logistics> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Order> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("OrderID", OrderID);
        Order order = orderMapper.selectOne(queryWrapper1);
        if (order == null) return null;
        queryWrapper.eq("LogisticsID", order.getLogisticsID());
        return logisticsMapper.selectOne(queryWrapper);
    }
}
