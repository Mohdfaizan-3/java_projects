package com.lg.electronic_store.service.order;

import com.lg.electronic_store.entity.shoppingCart.ShoppingCart;
import com.lg.electronic_store.repository.order.OrderRepository;
import com.lg.electronic_store.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public ShoppingCart saveOrder(ShoppingCart shoppingCart) {
        return orderRepository.save(shoppingCart);
    }

    public BigDecimal calculateTotalAmount(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(item -> {
                    BigDecimal price = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"))
                            .getPrice();
                    return price.multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional(readOnly = true)
    public ShoppingCart getOrderDetail(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}