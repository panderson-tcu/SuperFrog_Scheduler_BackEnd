package edu.tcu.cs.superfrogscheduler.appearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppearanceRepository extends JpaRepository<Appearance, Integer> {

    List<Appearance> findAppearancesByIdIn(List<Integer> appearanceRequestIdList);

    List<Appearance> findAppearancesByAssignedStudentSFSid(Integer SFSid);

    List<Appearance> findAppearancesByBeginningTimeBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
