package ru.school.database.backend.entities;

import lombok.Data;
import ru.school.database.backend.compositeKeys.StudentsProgramsKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "students_programs")
@IdClass(StudentsProgramsKey.class)
public class StudentsPrograms implements Serializable {

    @Id
    @Column(name = "student_id", nullable = false)
    @NotNull
    private Long studentId;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Students student;

    @Id
    @Column(name = "program_id", nullable = false)
    @NotNull
    private Long programId;
    @ManyToOne
    @JoinColumn(name = "program_id", insertable = false, updatable = false)
    private Programs program;

}
