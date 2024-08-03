package com.uber.uberApp.entities.enums;

/**
 * Represents the various states a payment can be in within the UberApp system.
 */
public enum PaymentStatus {
    /**
     * The payment has been initiated but not yet completed or confirmed.
     */
    PENDING,

    /**
     * The payment has been successfully processed and confirmed.
     */
    CONFIRMED,

    /**
     * The payment was reversed, and the amount has been returned to the user.
     */
    REFUNDED
}