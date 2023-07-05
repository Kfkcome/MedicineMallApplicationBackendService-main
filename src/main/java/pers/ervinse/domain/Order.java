package pers.ervinse.domain;

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

    /**
     *
     */

    private Integer orderid;
    /**
     *
     */
    private Integer logisticsid;
    /**
     *
     */
    private Integer orlogid;
    /**
     *
     */
    private Integer commodityid;
    /**
     *
     */
    private Integer userid;
    /**
     *
     */
    private Date ordertime;
    /**
     *
     */
    private Integer orderpaystate;
    /**
     *
     */
    private Integer orderfullamount;

}
