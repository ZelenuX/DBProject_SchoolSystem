package ru.school.database.backend.compositeKeys;

import java.io.Serializable;

public class StudentsProgramsKey implements Serializable {
    private Long studentId;
    private Long programId;

    private boolean longsEqual(Long long1, Long long2){
        if (long1 == null){
            return long2 == null;
        }
        else {
            return long1.equals(long2);
        }
    }

    public StudentsProgramsKey(){}
    public StudentsProgramsKey(Long studentId, Long programId){
        this.studentId = studentId;
        this.programId = programId;
    }

    @Override
    public boolean equals(Object another){
        try {
            StudentsProgramsKey anotherKey = (StudentsProgramsKey)another;
            return longsEqual(studentId, anotherKey.studentId)
                    && longsEqual(programId, anotherKey.programId);
        }
        catch (ClassCastException e){
            return false;
        }
    }
    @Override
    public int hashCode(){
        return studentId.hashCode() + 10 * programId.hashCode();
    }
}
