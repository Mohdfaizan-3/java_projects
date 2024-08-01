package com.lg.electronic_store.dao.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartResponse {
    private String message;
    private BigDecimal totalAmount;
}
