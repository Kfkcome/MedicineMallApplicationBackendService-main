package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName order_info
 */
@Data
@TableName("order_info")
public class Order implements Serializable {
    private Integer OrderID;//订单id
    private Integer LogisticsID;//物流id
    private Integer OrLogID;
    private Integer CommodityID;
    private Integer UserID;
    private Date OrderTime;
    private Integer OrderPayState;///订单状体 1：未支付 2：未发货 3：待收货 4：待评价
    private Integer OrderFullAmount;
    private Integer CommodityNum;
}
