package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer>, JpaSpecificationExecutor<SuperFrogStudent> {


}
