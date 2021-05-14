package ru.school.database.backend.filteringUtils;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntitySpecification<Entity> implements Specification<Entity> {
    private List<SearchCriteria> searchCriteriaList = new ArrayList<>();

    public void addCriteria(SearchCriteria criteria){
        searchCriteriaList.add(criteria);
    }
    @Override
    public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        boolean invalidObjectException = false;
        for (SearchCriteria criteria : searchCriteriaList){
            try {
                Object filterValue = criteria.getValue();
                if (filterValue != null) {
                    String key = criteria.getKey();
                    predicates.add(criteriaBuilder.equal(root.get(key), filterValue));
                }
            } catch (InvalidObjectException e) {
                invalidObjectException = true;
            }
        }
        if (invalidObjectException){
            predicates.clear();
            Notification.show("Invalid filter value format.");
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
