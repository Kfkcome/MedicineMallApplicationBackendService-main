package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 药品
 *
 * @author kfk
 * @date 2023/07/04
 */
@Data//为当前实体类在编译期设置对应的get/set方法，toString方法，hashCode方法，equals方法等
@NoArgsConstructor
@AllArgsConstructor
@TableName("commodity_info")
public class Goods implements Serializable {

    private Integer CommodityID;//药品id
    private Integer MerchantID;//商家id
    private Integer CommodityType;//药品类型
    private String CommodityName, //药品名称
            CommodityDsec;//药品描述
    private Integer CommodityPrice;//药品价格
    private Long CommoditySales;//药品销量
//    public Goods(String name, String description, double price, String location) {
//        this.name = name;
//        this.location = location;
//        this.description = description;
//        this.price = price;
//    }
}
