package edu.tcu.cs.superfrogscheduler.performancereport;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
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


    public List<PerformanceReport> generatePerformanceReports(Period periodRange) {

        //Find all appearances in a periodRange
        List<Appearance> allAppereancesInRange = this.appearanceRepository
                .findAppearancesByBeginningTimeBetween(periodRange.getBeginDate().atStartOfDay(), periodRange.getEndDate().atTime(23,59,59));

        //Group the appearances by superfrog student - problem here
        Map<SuperFrogStudent, List<Appearance>> studentRequestsMap = groupRequestsBySuperFrogStudent(allAppereancesInRange);


        // For each SuperFrogStudent, generate a performace report, and then collect the performance reports into a list.
        List<PerformanceReport> performanceReports = studentRequestsMap.entrySet().stream()
                .map(entry -> entry.getKey().generatePerformanceReport(entry.getValue(), periodRange))
                .collect(Collectors.toList());

        // Persist the generated payment forms and then return them.
        System.out.println(studentRequestsMap.keySet().size());
        System.out.println(studentRequestsMap.values().size());
        System.out.println(allAppereancesInRange.size());

        return this.performanceReportRepository.saveAll(performanceReports);
        //return performanceReports;
    }


    private Map<SuperFrogStudent, List<Appearance>> groupRequestsBySuperFrogStudent(List<Appearance> selectedRequests) {
        Map<SuperFrogStudent, List<Appearance>> studentRequestsMap = selectedRequests.stream()
                .collect(Collectors.groupingBy(Appearance::getStudent));
        return studentRequestsMap;
    }
}
