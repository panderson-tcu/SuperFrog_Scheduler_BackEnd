package edu.tcu.cs.superfrogscheduler.performancereport;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReportRepository extends JpaRepository<PerformanceReport, Integer> {
}
