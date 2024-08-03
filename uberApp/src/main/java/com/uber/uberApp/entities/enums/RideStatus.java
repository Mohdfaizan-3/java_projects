package com.uber.uberApp.entities.enums;

/**
 * Represents the various states a ride can be in within the UberApp system.
 */
public enum RideStatus {
   /**
    * The ride has been confirmed but has not yet started.
    */
   CONFIRMED,

   /**
    * The ride was cancelled before it started or completed.
    */
   CANCELLED,

   /**
    * The ride has been completed.
    */
   ENDED,

   /**
    * The ride is currently in progress.
    */
   ONGOING
}