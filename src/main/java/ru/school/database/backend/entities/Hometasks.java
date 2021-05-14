package ru.school.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "hometasks")
public class Hometasks implements Serializable {

    @Id
    @Column(name = "lesson_id", nullable = false)
    @NotNull
    private Long lessonId;
    @OneToOne
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    private Lessons lesson;

    @Column(name = "text")
    private String text;

    @Column(name = "deadline")
    private Date deadline;

}
