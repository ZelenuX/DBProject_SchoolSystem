package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.compositeKeys.FinalMarksKey;
import ru.school.database.backend.entities.Disciplines;
import ru.school.database.backend.entities.Students;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "final_marks")
@IdClass(FinalMarksKey.class)
public class FinalMarks implements Serializable {

    @Id
    @Column(name = "student_id", nullable = false)
    @NotNull
    private Long studentId;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Students student;

    @Id
    @Column(name = "discipline_id", nullable = false)
    @NotNull
    private Long disciplineId;
    @ManyToOne
    @JoinColumn(name = "discipline_id", insertable = false, updatable = false)
    private Disciplines discipline;

    @Column(name = "value", nullable = false)
    @NotNull
    private Long value;

    @Column(name = "comment")
    private String comment;

}
