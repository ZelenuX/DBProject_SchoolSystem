package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.compositeKeys.TeachersDisciplinesKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "teachers_disciplines")
@IdClass(TeachersDisciplinesKey.class)
public class TeachersDisciplines implements Serializable {

    @Id
    @Column(name = "teacher_id", nullable = false)
    @NotNull
    private Long teacherId;
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teachers teacher;

    @Id
    @Column(name = "discipline_id", nullable = false)
    @NotNull
    private Long disciplineId;
    @ManyToOne
    @JoinColumn(name = "discipline_id", insertable = false, updatable = false)
    private Disciplines discipline;

    @Column(name = "teachers_comment")
    private String teachersComment;
}
