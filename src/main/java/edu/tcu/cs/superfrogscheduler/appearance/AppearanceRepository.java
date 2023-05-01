package edu.tcu.cs.superfrogscheduler.appearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppearanceRepository extends JpaRepository<Appearance, Integer> {

    //Find appearances using a list of appearance id
    // this only works when the appearances is already assigned to a student
    List<Appearance> findAppearancesByIdIn(List<Integer> appearanceRequestIdList);

    //Find appearances by the student id that was assigned
    List<Appearance> findAppearancesByAssignedStudentSFSid(Integer SFSid);

    //Used for UC-19
    List<Appearance> findAppearancesByAssignedStudentSFSidInAndBeginningTimeBetweenAndStatusIs(List<Integer> studentIdList, LocalDateTime beginDate, LocalDateTime endDate, AppearanceStatus status);

    List<Appearance> findAppearancesByBeginningTimeBetween(LocalDateTime beginDate, LocalDateTime endDate);

    List<Appearance> findAppearancesByAssignedStudentSFSidAndStatusIs(Integer SFSid, AppearanceStatus status);

}
