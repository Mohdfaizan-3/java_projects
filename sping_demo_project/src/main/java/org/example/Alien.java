package org.example;

public class Alien {
    private int age;
    private Laptop lap;
    int salary;

    public Alien () {}

    public Alien(int age, Laptop lap, int salary) {
        this.age = age;
        this.lap = lap;
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setLap(Laptop lap) {
        this.lap = lap;
    }

    public int getAge() {
        return age;
    }

    public Laptop getLap() {
        return lap;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void code () {
        System.out.println("coding");
        System.out.println(age);
    }
}
