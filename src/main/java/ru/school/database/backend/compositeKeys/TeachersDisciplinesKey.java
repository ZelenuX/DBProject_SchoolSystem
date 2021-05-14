package ru.school.database.backend.compositeKeys;

import java.io.Serializable;

public class TeachersDisciplinesKey implements Serializable {
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

    public TeachersDisciplinesKey(){}
    public TeachersDisciplinesKey(Long teacherId, Long disciplineId){
        this.teacherId = teacherId;
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object another){
        try {
            TeachersDisciplinesKey anotherKey = (TeachersDisciplinesKey)another;
            return longsEqual(teacherId, anotherKey.teacherId)
                    && longsEqual(disciplineId, anotherKey.disciplineId);
        }
        catch (ClassCastException e){
            return false;
        }
    }
    @Override
    public int hashCode(){
        return teacherId.hashCode() + 10 * disciplineId.hashCode();
    }
}
