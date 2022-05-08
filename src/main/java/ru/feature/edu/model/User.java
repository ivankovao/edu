package ru.feature.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_D")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_k")
    private String name;
    @Column(name = "city_l")
    private String city;
    @Column(name = "age_h")
    private int age;

    public User() {
    }

    public User(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "User{id=" + this.id + ", name='" + this.name + '\'' + ", city='" + this.city + '\'' + ", age=" + this.age + '}';
    }
}
