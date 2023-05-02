package edu.tcu.cs.superfrogscheduler.performancereport;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceStatus;
import edu.tcu.cs.superfrogscheduler.paymentform.PaymentForm;
import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PerformanceReportService {
    private PerformanceReportRepository performanceReportRepository;

    private AppearanceRepository appearanceRepository;

    public PerformanceReportService(PerformanceReportRepository performanceReportRepository, AppearanceRepository appearanceRepository) {
        this.performanceReportRepository = performanceReportRepository;
        this.appearanceRepository = appearanceRepository;
    }

    //UC-19: Generate performance reports based on selected student IDs, and selected period
    //Input: list of student IDs, and a period range
    //Output: Performance report
    public List<PerformanceReport> generatePerformanceReports(List<Integer> studentIdList, Period periodRange) {
        //Find all appearances by the list of student ids, with status of completed, and between the two dates
        List<Appearance> selectedRequests = this.appearanceRepository.findAppearancesByAssignedStudentSFSidInAndBeginningTimeBetweenAndStatusIs(studentIdList,
                                                                                                                            periodRange.getBeginDate().atStartOfDay(),
                                                                                                                            periodRange.getEndDate().atTime(23,59,59),
                                                                                                                            AppearanceStatus.COMPLETED);

        //Group the appearances by superfrog students
        Map<SuperFrogStudent, List<Appearance>> studentRequestsMap = groupRequestsBySuperFrogStudent(selectedRequests);

        // For each SuperFrogStudent, generate a performance report, and then collect the performance reports into a list.
        //Find all appearances in a periodRange
        List<Appearance> allAppereancesInRange = this.appearanceRepository
                .findAppearancesByBeginningTimeBetween(periodRange.getBeginDate().atStartOfDay(), periodRange.getEndDate().atTime(23,59,59));

        //Group the appearances by superfrog student
        Map<SuperFrogStudent, List<Appearance>> studentRequestsMap = groupRequestsBySuperFrogStudent(allAppereancesInRange);

        // For each SuperFrogStudent, generate a performace report, and then collect the performance reports into a list.
        List<PerformanceReport> performanceReports = studentRequestsMap.entrySet().stream()
                .map(entry -> entry.getKey().generatePerformanceReport(entry.getValue(), periodRange))
                .collect(Collectors.toList());

        // Persist the generated payment forms and then return them.
        return this.performanceReportRepository.saveAll(performanceReports);
    }


    private Map<SuperFrogStudent, List<Appearance>> groupRequestsBySuperFrogStudent(List<Appearance> selectedRequests) {
        Map<SuperFrogStudent, List<Appearance>> studentRequestsMap = selectedRequests.stream()
                .collect(Collectors.groupingBy(Appearance::getStudent));
        return studentRequestsMap;
    }
}
