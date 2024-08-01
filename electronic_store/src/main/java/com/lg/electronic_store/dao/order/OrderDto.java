package com.lg.electronic_store.dao.order;

import com.lg.electronic_store.entity.shoppingCartItem.ShoppingCartItem;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long userId;
    private String orderDescription;
    private List<ShoppingCartItem> cartItems;
}




