package com.lg.electronic_store.repository.shoppingCart;

import com.lg.electronic_store.entity.shoppingCartItem.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCarRepository extends JpaRepository<ShoppingCartItem, Long> {
}
