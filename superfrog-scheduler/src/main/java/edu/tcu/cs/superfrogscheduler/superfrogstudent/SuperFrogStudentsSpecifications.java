package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperFrogStudentsSpecifications{
    public Specification<SuperFrogStudent> superFrogStudentFilters(String firstName, String lastName, String phone, String email){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null){
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }
            if (lastName != null){
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
            }
            if (phone != null){
                predicates.add(criteriaBuilder.equal(root.get("phone"), phone));
            }
            if (email != null){
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
