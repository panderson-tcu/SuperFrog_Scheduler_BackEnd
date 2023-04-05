package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, String> {
}
