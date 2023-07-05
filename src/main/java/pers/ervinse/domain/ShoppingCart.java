package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shoppingcart_info")
public class ShoppingCart {
    Integer ShoppingCartID;
    Integer UserID;
    Integer ShoppingCartAmount;
}
