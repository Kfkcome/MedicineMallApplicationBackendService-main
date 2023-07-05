package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName shoppingcart_commodity
 */
@TableName("shoppingcart_commodity")
@Data
public class ShoppingcartCommodity implements Serializable {

    /**
     * 商品id
     */
    private Integer commodityid;
    /**
     * 购物车id
     */
    private Integer shoppingcartid;
    /**
     * 商品数量
     */
    private Integer commoditynum;

}
