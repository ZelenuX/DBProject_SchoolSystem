package ru.school.database.backend.forComplexQueries;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialInfoExtractor {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BestStudent> getBestStudents(){
        String requestFilePath = this.getClass().getResource("/findBestStudents.sql").getPath().substring(1);
        String requestString = null;
        try {
            requestString = new String(Files.readAllBytes(Path.of(requestFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query query = entityManager.createNativeQuery(requestString);
        List<String[]> bestStudents = query.getResultList();
        List<BestStudent> res = new ArrayList<>();
        for (Object[] bestStudentStrs : bestStudents){
            res.add(new BestStudent((String)bestStudentStrs[0], (String)bestStudentStrs[1],
                    (String)bestStudentStrs[2], (String)bestStudentStrs[3]));
        }
        return res;
    }
}
