package org.example;

public class Alien {
    private int age;
    private Laptop lap;

    public void setAge(int age) {
        this.age = age;
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


    public void code () {
        System.out.println("coding");
        System.out.println(age);
    }
}
