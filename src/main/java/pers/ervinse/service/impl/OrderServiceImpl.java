package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.Order;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.mapper.OrderMapper;
import pers.ervinse.service.OrderService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.DateTimeUtils;
import pers.ervinse.utils.UserContextUtil;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 订单服务impl
 *
 * @author kfk
 * @date 2023/07/05
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final CommodityMapper commodityMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, CommodityMapper commodityMapper) {
        this.orderMapper = orderMapper;
        this.commodityMapper = commodityMapper;
    }

    /**
     * 生成订单
     *
     * @param CommodityID  商品
     * @param CommodityNum 商品num
     * @return {@link ApiResponse}
     *///订单状体 1：未支付 2：未发货 3：待收货 4：待评价
    @Override
    public ApiResponse generateOrder(Integer CommodityID, Integer CommodityNum) {
        Commodity commodity = commodityMapper.selectByCommodityIDCommodity(CommodityID);
        if (commodity == null || CommodityNum == 0) {
            return ApiResponse.fail(ResponseCode.CANNOT_GENERATE_ORDER);
        }
        Order order = new Order();
        order.setOrderID(null);
        order.setOrderTime(new Date());
        order.setOrderPayState(1);
        order.setUserID(UserContextUtil.get().getUserID());
        order.setCommodityNum(CommodityNum);
        order.setCommodityID(CommodityID);
        order.setOrderFullAmount(commodity.getCommodityPrice() * CommodityNum);
        int insert = orderMapper.insert(order);
        if (insert == 0) return ApiResponse.fail(ResponseCode.UPDATE_ERROR_NOT_EXIT);
        return ApiResponse.success(insert);
    }

    /**
     * 获取订单信息
     *
     * @param OrderID 订单id
     * @return {@link Order}
     */
    @Override
    public Order getOrderInfo(Integer OrderID) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OrderID", OrderID);
        return orderMapper.selectOne(queryWrapper);
    }

    /**
     * 得到所有用户订单
     *
     * @return {@link List}<{@link Order}>
     */
    @Override
    public List<Order> getAllUserOrder() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        return orderMapper.selectList(queryWrapper);
    }

    /**
     * 删除订单
     *
     * @param OrderID 订单id
     * @return {@link ApiResponse}
     */
    @Override
    public ApiResponse deleteOrder(Integer OrderID) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OrderID", OrderID);
        Order order = orderMapper.selectOne(queryWrapper);
        if (order == null) {
            return ApiResponse.fail(ResponseCode.ORDER_NOT_EXIT);
        } else if (!order.getUserID().equals(UserContextUtil.get().getUserID())) {
            return ApiResponse.fail(ResponseCode.CANNOT_CHANGE_OTHER_ORDER);
        } else if (order.getOrderPayState().equals(1)) {
            return ApiResponse.fail(2505, "无法删除已经支付订单，请联系管理员删除");
        }
        return ApiResponse.success(orderMapper.delete(queryWrapper));
    }

    /**
     * 支付订单
     *
     * @param OrderID 订单id
     * @return {@link ApiResponse}
     */
    @Override
    public ApiResponse payOrder(Integer OrderID) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OrderID", OrderID);
        updateWrapper.eq("OrderID", OrderID).set("OrderPayState", 2);
        Order order = orderMapper.selectOne(queryWrapper);
        if (order == null) {
            return ApiResponse.fail(ResponseCode.ORDER_NOT_EXIT);
        } else if (!order.getUserID().equals(UserContextUtil.get().getUserID())) {
            return ApiResponse.fail(ResponseCode.CANNOT_CHANGE_OTHER_ORDER);
        }
        return ApiResponse.success(orderMapper.update(null, updateWrapper));
    }
}
