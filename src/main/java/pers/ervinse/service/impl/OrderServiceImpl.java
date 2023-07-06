package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ervinse.domain.*;
import pers.ervinse.domain.dto.LogisticsInfoAll;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.mapper.*;
import pers.ervinse.service.OrderService;
import pers.ervinse.utils.ApiResponse;
import pers.ervinse.utils.UserContextUtil;

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
    private final ReviewMapper reviewMapper;
    private final AddressMapper addressMapper;
    private final LogisticsMapper logisticsMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, CommodityMapper commodityMapper, ReviewMapper reviewMapper, AddressMapper addressMapper, LogisticsMapper logisticsMapper) {
        this.orderMapper = orderMapper;
        this.commodityMapper = commodityMapper;
        this.reviewMapper = reviewMapper;
        this.addressMapper = addressMapper;
        this.logisticsMapper = logisticsMapper;
    }

    /**
     * 生成订单
     * 首先生成物流信息
     * 其次生成订单信息时关联物流信息
     * 最后插入订单
     *
     * @param CommodityID  商品
     * @param CommodityNum 商品num
     * @return {@link ApiResponse}
     *///订单状体 1：未支付 2：未发货 3：待收货 4：待评价
    @Override
    @Transactional//事务处理 如果生成订单失败就回滚
    public ApiResponse generateOrder(Integer CommodityID, Integer CommodityNum) {
        Commodity commodity = commodityMapper.selectByCommodityIDCommodity(CommodityID);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", UserContextUtil.get().getUserID());
        List<Address> address = addressMapper.selectList(queryWrapper);
        if (address == null || address.isEmpty()) {
            return ApiResponse.fail(2090, "账户地址不存在，请先添加地址");
        }
        if (commodity == null || CommodityNum == 0) {
            return ApiResponse.fail(ResponseCode.CANNOT_GENERATE_ORDER);
        }
        Logistics logistics = new Logistics();
        logistics.setLogisticRAddressID(address.get(0).getAddressID());
        logistics.setLogisticsID(Math.toIntExact(logisticsMapper.selectCount(null)) + 1);
        logisticsMapper.insert(logistics);
        Order order = new Order();
        order.setOrderID(null);
        order.setOrderTime(new Date());
        order.setOrderPayState(1);
        order.setUserID(UserContextUtil.get().getUserID());
        order.setCommodityNum(CommodityNum);
        order.setCommodityID(CommodityID);
        order.setLogisticsID(logistics.getLogisticsID());
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
        } else if (order.getOrderPayState().equals(2)) {
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

    @Override
    public ApiResponse<Integer> reviewToOrder(Review review) {
        review.setReviewID(null);
        review.setUserID(UserContextUtil.get().getUserID());
        int insert = reviewMapper.insert(review);
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("OrderID", review.getOrderID())
                .set("OrderPayState", 5);
        orderMapper.update(null, updateWrapper);
        if (insert == 0) return ApiResponse.fail(ResponseCode.INSERT_ERROR);
        return ApiResponse.success(insert);
    }

    @Override
    public ApiResponse confirmOrder(Integer OrderID) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("OrderID", OrderID);
        updateWrapper.eq("UserID", UserContextUtil.get().getUserID());
        updateWrapper.set("OrderPayState", 4);//切换订单为待评价状态

        return ApiResponse.success(orderMapper.update(null, updateWrapper));
    }

    @Override
    public LogisticsInfoAll getOrderLogistic(Integer OrderID) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OrderID", OrderID);
        Order order = orderMapper.selectOne(queryWrapper);
        if (order == null) return null;
        QueryWrapper<Logistics> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("LogisticsID", order.getLogisticsID());
        Logistics logistics = logisticsMapper.selectOne(queryWrapper1);
        QueryWrapper<Address> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("AddressID", logistics.getLogisticRAddressID());
        Address RAaddress = addressMapper.selectOne(queryWrapper2);
        Address DAaddress;
        QueryWrapper<Address> queryWrapper3 = new QueryWrapper<>();
        if (logistics.getLogisticDAddressID() == null) {
            DAaddress = null;
        } else {
            queryWrapper3.eq("AddressID", logistics.getLogisticDAddressID());
            DAaddress = addressMapper.selectOne(queryWrapper3);
        }
        return new LogisticsInfoAll(logistics, DAaddress, RAaddress);
    }
}
