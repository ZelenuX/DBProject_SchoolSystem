package ru.school.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lessons")
public class Lessons implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "description")
    private String description;

    @Column(name = "timetablecell_id")
    private Long timetablecellId;
    @ManyToOne
    @JoinColumn(name = "timetablecell_id", insertable = false, updatable = false)
    private Timetable timetablecell;

    @Column(name = "date")
    private LocalDate date;

    @Override
    public String toString(){
        return date.toString() + ": " + theme;
    }

}
