package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.entities.Disciplines;
import ru.school.database.backend.entities.Programs;
import ru.school.database.backend.entities.Teachers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "timetable")
public class Timetable implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "program_id")
    private Long programId;
    @ManyToOne
    @JoinColumn(name = "program_id", insertable = false, updatable = false)
    private Programs program;

    @Column(name = "teacher_id")
    private Long teacherId;
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teachers teacher;

    @Column(name = "discipline_id")
    private Long disciplineId;
    @ManyToOne
    @JoinColumn(name = "discipline_id", insertable = false, updatable = false)
    private Disciplines discipline;

    @Column(name = "day", nullable = false)
    @NotNull
    private Long day;

    @Column(name = "time", nullable = false)
    @NotNull
    private LocalTime time;

    @Column(name = "room", nullable = false)
    @NotNull
    private Long room;

}
