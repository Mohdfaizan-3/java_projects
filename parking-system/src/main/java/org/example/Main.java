package org.example;
import java.util.ArrayList;
import java.util.List;

 class Car {
    private String licencePlate;

    public Car(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}

class ParkingSpot {
    private int spotNumber;
    private boolean isAvailable;
    private Car car;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isAvailable = true;
        this.car = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Car getCar() {
        return car;
    }

    public void occupy(Car car) {
        this.car = car;
        this.isAvailable = false;
    }

    public void vaccant() {
        this.car = null;
        this.isAvailable = true;
    }
}

class ParkingLot {
    private List<ParkingSpot> lot;

    public ParkingLot(int capacity) {
        this.lot = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            lot.add(new ParkingSpot(i));
        }
    }

    public boolean parkTheCar(Car car) {
        for (ParkingSpot spot : lot) {
            if (spot.isAvailable()) {
                spot.occupy(car);
                System.out.println("car with number " + car.getLicencePlate()+" is parked at spot number: "+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("sorry parking not available");
        return false;
    }

    public boolean removeCar(String licensePlate) {
        for (ParkingSpot spot : lot) {
            if (!spot.isAvailable() && spot.getCar().getLicencePlate().equalsIgnoreCase(licensePlate)) {
                spot.vaccant();
                System.out.println("car with number " + licensePlate +" is remove at "+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("license plate number not found");
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car1 = new Car("HR 4F 2323");
        Car car2 = new Car("DL 1F 5455");
        Car car3 = new Car("MM 1G 2334");
        parkingLot.parkTheCar(car1);
        parkingLot.parkTheCar(car2);
        parkingLot.parkTheCar(car3);
        parkingLot.removeCar("HR 4F 2323");

    }
}