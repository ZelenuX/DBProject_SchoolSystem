package com.vaadin.tutorial.crm.backend.entities;

import javax.persistence.*;

@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    @ManyToOne
    private Flat flat;

    public Person() {}
    public Person(String name, String surname, Integer age, Flat flat) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.flat = flat;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Integer getAge() {
        return age;
    }
    public Flat getFlat(){
        return flat;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public boolean isInitialized(){
        return !(name == null || surname == null || age == null);
    }
}
