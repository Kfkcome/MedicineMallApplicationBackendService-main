package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("merchant_info")
public class Merchant {
    private Integer MerchantID;
    private String MerchantName;
    private String MerchantDesc;//商家描述
    private Integer MerchantEvalue;//商家评级
}
