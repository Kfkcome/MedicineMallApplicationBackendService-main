package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("logistics_info")
public class Logistics {
    private Integer LogisticsID;
    private String LogisticTrackingNum;//物流账号
    private String LogisticCompany;//物流公司
    private Integer LogisticDAddressID;//发货地址
    private Integer LogisticRAddressID;//收货地址
    private Date LogisticDTime;//发货时间
    private Date LogisticRTime;//到货时间

}
