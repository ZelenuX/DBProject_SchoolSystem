package ru.school.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Override
    public String toString(){
        if (secondName != null) {
            return firstName + " " + secondName + " " + lastName;
        }
        else {
            return firstName + " " + lastName;
        }
    }

}
