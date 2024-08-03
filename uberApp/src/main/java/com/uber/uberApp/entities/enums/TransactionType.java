package com.uber.uberApp.entities.enums;

/**
 * Represents the types of transactions that can occur in a user's wallet within the UberApp system.
 */
public enum TransactionType {
        /**
         * Represents a transaction that adds money to the wallet balance.
         * This could be from adding funds, receiving a refund, or other forms of incoming money.
         */
        CREDIT,

        /**
         * Represents a transaction that subtracts money from the wallet balance.
         * This could be from paying for a ride, service fees, or other forms of outgoing money.
         */
        DEBIT
}