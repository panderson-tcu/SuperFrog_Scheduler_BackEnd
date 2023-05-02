package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;





@Component
public class AppearanceSpecifications{
    public Specification<Appearance> appearanceFilters(String eventTitle, String C_firstName, String C_lastName, AppearanceStatus status ){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (eventTitle != null){
                predicates.add(criteriaBuilder.equal(root.get("eventTitle"), eventTitle));
            }
            if (C_firstName != null){
                predicates.add(criteriaBuilder.equal(root.get("C_firstName"), C_firstName));
            }
            if (C_lastName != null){
                predicates.add(criteriaBuilder.equal(root.get("C_lastName"), C_lastName));
            }
            if (status != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
