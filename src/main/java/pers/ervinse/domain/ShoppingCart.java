package pers.ervinse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    Integer ShoppingCartID;
    Integer UserID;
    Integer ShoppingCartAmount;
}
