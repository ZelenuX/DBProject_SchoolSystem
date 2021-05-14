package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.entities.Hometasks;
import ru.school.database.backend.entities.Students;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "homeworks")
public class Homeworks implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "student_id")
    private Long studentId;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Students student;

    @Column(name = "hometask_id")
    private Long hometaskId;
    @ManyToOne
    @JoinColumn(name = "hometask_id", insertable = false, updatable = false)
    private Hometasks hometask;

    @Column(name = "homework")
    private String homework;

    @Column(name = "students_comment")
    private String studentsComment;

    @Column(name = "mark")
    private Long mark;

    @Column(name = "teachers_comment")
    private String teachersComment;

}
