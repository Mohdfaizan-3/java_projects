package com.uber.uberApp.entities.enums;

/**
 * Represents the methods through which transactions can occur in a user's wallet within the UberApp system.
 */
public enum TransactionMethod {
    /**
     * Represents transactions that involve direct banking operations.
     * This could include adding funds to the wallet from a bank account,
     * or withdrawing funds from the wallet to a bank account.
     */
    BANKING,

    /**
     * Represents transactions that are directly related to ride services.
     * This could include payments for rides, refunds for cancelled rides,
     * or driver payouts for completed rides.
     */
    RIDE
}