package pers.ervinse.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.Logistics;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsInfoAll extends Logistics {
    Address LogisticDAddress;
    Address LogisticRAddress;

    public LogisticsInfoAll(Logistics logistics, Address LogisticDAddress, Address LogisticRAddress) {
        super(logistics);
        this.LogisticDAddress = LogisticDAddress;
        this.LogisticRAddress = LogisticRAddress;
    }
}
