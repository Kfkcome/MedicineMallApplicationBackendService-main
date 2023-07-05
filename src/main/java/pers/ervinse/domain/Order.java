package pers.ervinse.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName order_info
 */
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
