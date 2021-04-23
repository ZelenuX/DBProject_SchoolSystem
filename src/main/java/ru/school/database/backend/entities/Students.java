package ru.school.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
public class Students {

    @Id
    @Column(name = "people_id", nullable = false)
    @NotNull
    private Long peopleId;
    @OneToOne
    @JoinColumn(name = "people_id")
    private People people;

    @Column(name = "education_start_date", nullable = false)
    @NotNull
    private LocalDate educationStartDate;

}
