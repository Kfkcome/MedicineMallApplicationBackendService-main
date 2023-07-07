package pers.ervinse.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("address_info")
public class Address {

    @JSONField(name = "AddressID")
    Integer AddressID;
    @JSONField(name = "UserID")
    Integer UserID;
    @JSONField(name = "CountryforAddress")
    String CountryforAddress;//国家
    @JSONField(name = "ProvinceforAddress")
    String ProvinceforAddress;//省份
    @JSONField(name = "TownforAddress")
    String TownforAddress;//市
    @JSONField(name = "DistrictforAddress")
    String DistrictforAddress;//县区
    @JSONField(name = "StreetforAddress")
    String StreetforAddress;//街道
    @JSONField(name = "DetailAddress")
    String DetailAddress;//详细地址
    @JSONField(name = "ReceiveName")
    String ReceiveName;//收货人姓名
    @JSONField(name = "ReceiveTel")
    String ReceiveTel;//收货人电话
}
