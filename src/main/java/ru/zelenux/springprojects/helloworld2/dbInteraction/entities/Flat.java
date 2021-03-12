package ru.zelenux.springprojects.helloworld2.dbInteraction.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer price;
    private Integer sqrMeters;

    public Flat() {}
    public Flat(Integer price, Integer sqrMeters) {
        this.price = price;
        this.sqrMeters = sqrMeters;
    }
    public Long getId() {
        return id;
    }
    public Integer getPrice() {
        return price;
    }
    public Integer getSqrMeters() {
        return sqrMeters;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setSqrMeters(Integer sqrMeters) {
        this.sqrMeters = sqrMeters;
    }
}
