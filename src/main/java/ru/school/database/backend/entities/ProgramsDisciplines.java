package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.compositeKeys.ProgramsDisciplinesKey;
import ru.school.database.backend.entities.Disciplines;
import ru.school.database.backend.entities.Programs;
import ru.school.database.backend.entities.Teachers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "programs_disciplines")
@IdClass(ProgramsDisciplinesKey.class)
public class ProgramsDisciplines implements Serializable {

    @Id
    @Column(name = "program_id", nullable = false)
    @NotNull
    private Long programId;
    @ManyToOne
    @JoinColumn(name = "program_id", insertable = false, updatable = false)
    private Programs program;

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

}
