package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("merchant_address")
public class Merchant_Address {
    Integer MerchantID;
    Integer AddressID;
}
