package pers.ervinse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    Integer AddressID;
    Integer UserID;
    String CountryforAddress;//国家
    String ProvinceforAddress;//省份
    String TownforAddress;//市
    String DistrictforAddress;//县区
    String StreetforAddress;//街道
    String DetailAddress;//详细地址
    String ReceiveName;//收货人姓名
    String ReceiveTel;//收货人电话
}
