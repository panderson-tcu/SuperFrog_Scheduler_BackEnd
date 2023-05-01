package edu.tcu.cs.superfrogscheduler.performancereport;

import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PerformanceReportController {
    private PerformanceReportService performanceReportService;

    public PerformanceReportController(PerformanceReportService performanceReportService) {
        this.performanceReportService = performanceReportService;
    }

    @PostMapping("/api/v1/performance-reports")
    public Result generatePaymentForms(@RequestBody Period periodRange) {
        List<PerformanceReport> performanceReports = this.performanceReportService.generatePerformanceReports(periodRange);
        return new Result(true, StatusCode.SUCCESS, "Payment forms are generated successfully.", performanceReports);
    }



}
