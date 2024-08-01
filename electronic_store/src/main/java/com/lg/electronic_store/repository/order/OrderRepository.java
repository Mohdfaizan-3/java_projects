package com.lg.electronic_store.repository.order;

import com.lg.electronic_store.entity.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ShoppingCart, Long> {
}
