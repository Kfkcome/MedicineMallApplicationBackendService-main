package pers.ervinse.domain.vo;

import lombok.Data;

@Data
public class CommodityVo {
    private Integer CommodityID;//药品id
    private Integer MerchantID;//商家id
    private Integer CommodityType;//药品类型
    private String CommodityName; //药品名称
    private String CommodityDesc;//药品描述
    private Integer CommodityPrice;//药品价格
    private Long CommoditySales;//药品销量
    /**
     * 商品数量
     */
    private Integer commoditynum;
}
