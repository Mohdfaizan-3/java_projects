package org.example;

public class Alien {
    private int age;
    private Computer com;
    //private Laptop lap;
    int salary;

    public Alien () {}

//    public Alien(int age, Laptop lap, int salary) {
//        this.age = age;
//        this.lap = lap;
//        this.salary = salary;
//    }

        public Alien(int age, Computer com, int salary) {
        this.age = age;
        this.com = com;
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setCom(Computer com) {
        this.com = com;
   }
//    public void setLap(Laptop lap) {
//        this.lap = lap;
//    }

    public int getAge() {
        return age;
    }

    public Computer getCom() {
        return com;
    }
//    public Laptop getLap() {
//        return lap;
//    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

//    public void code () {
//        System.out.println("coding");
//        System.out.println(age);
//    }
}
