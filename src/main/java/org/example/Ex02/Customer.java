package org.example.Ex02;

public class Customer {
    enum Genders {
        FEMALE,
        MALE
    };

    private String name;
    private String phoneNumber;
    private int age;
    private Genders gender;

    public Customer(String name, String phoneNumber, int age, Genders gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
