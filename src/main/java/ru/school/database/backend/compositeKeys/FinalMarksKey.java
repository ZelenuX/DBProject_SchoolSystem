package ru.school.database.backend.compositeKeys;

import java.io.Serializable;

public class FinalMarksKey implements Serializable {
    private Long studentId;
    private Long disciplineId;

    private boolean longsEqual(Long long1, Long long2){
        if (long1 == null){
            return long2 == null;
        }
        else {
            return long1.equals(long2);
        }
    }

    public FinalMarksKey(){}
    public FinalMarksKey(Long studentId, Long disciplineId){
        this.studentId = studentId;
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object another){
        try {
            FinalMarksKey anotherKey = (FinalMarksKey)another;
            return longsEqual(studentId, anotherKey.studentId)
                    && longsEqual(disciplineId, anotherKey.disciplineId);
        }
        catch (ClassCastException e){
            return false;
        }
    }
    @Override
    public int hashCode(){
        return studentId.hashCode() + 10 * disciplineId.hashCode();
    }
}
