package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.entities.People;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "helpers")
public class Helpers implements Serializable {

    @Id
    @Column(name = "people_id", nullable = false)
    @NotNull
    private Long peopleId;
    @OneToOne
    @JoinColumn(name = "people_id")
    private People people;

    @Column(name = "work", nullable = false)
    @NotNull
    private String work;

}
