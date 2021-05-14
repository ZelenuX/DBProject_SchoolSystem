package ru.school.database.backend.compositeKeys;

import java.io.Serializable;

public class ProgramsDisciplinesKey implements Serializable {
    private Long programId;
    private Long teacherId;
    private Long disciplineId;

    private boolean longsEqual(Long long1, Long long2){
        if (long1 == null){
            return long2 == null;
        }
        else {
            return long1.equals(long2);
        }
    }

    public ProgramsDisciplinesKey(){}
    public ProgramsDisciplinesKey(Long programId, Long teacherId, Long disciplineId){
        this.programId = programId;
        this.teacherId = teacherId;
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object another){
        try {
            ProgramsDisciplinesKey anotherKey = (ProgramsDisciplinesKey)another;
            return longsEqual(programId, anotherKey.programId)
                    && longsEqual(teacherId, anotherKey.teacherId)
                    && longsEqual(disciplineId, anotherKey.disciplineId);
        }
        catch (ClassCastException e){
            return false;
        }
    }
    @Override
    public int hashCode(){
        return programId.hashCode() + 10 * teacherId.hashCode() + 100 * disciplineId.hashCode();
    }
}
