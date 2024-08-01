package com.lg.electronic_store.dao.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseOrderDto {
    private Long orderId;
    private BigDecimal amount;
    private String date;
    private int invoiceNumber;
    private String orderDescription;
}
