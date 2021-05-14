package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.entities.Lessons;
import ru.school.database.backend.entities.Students;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lesson_marks")
public class LessonMarks implements Serializable {

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

    @Column(name = "lesson_id")
    private Long lessonId;
    @ManyToOne
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    private Lessons lesson;

    @Column(name = "value", nullable = false)
    @NotNull
    private Long value;

    @Column(name = "comment")
    private String comment;

}
