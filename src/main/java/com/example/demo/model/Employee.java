package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employee")
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)

    List<Calender> calender = new ArrayList<>();

    public Employee() {
    }

    public Employee(Long id, String name, String email) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Calender> getCalender() {
        return calender;
    }

    public void setCalender(List<Calender> calender) {
        this.calender = calender;
    }
}
