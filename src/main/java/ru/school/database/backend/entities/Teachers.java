package ru.school.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "teachers")
public class Teachers {

    @Id
    @Column(name = "people_id", nullable = false)
    @NotNull
    private Long peopleId;
    @OneToOne
    @JoinColumn(name = "people_id")
    private People people;

    @Column(name = "experience_years", nullable = false)
    @NotNull
    private Long experienceYears;

    @Column(name = "education_degree")
    private String educationDegree;

    @Column(name = "max_hours_a_week", nullable = false)
    @NotNull
    private Long maxHoursAWeek;

    @Column(name = "about_me")
    private String aboutMe;

}
