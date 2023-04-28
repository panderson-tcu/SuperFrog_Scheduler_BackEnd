package edu.tcu.cs.superfrogscheduler.performancereport;

import com.lowagie.text.DocumentException;
import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PerformanceReportController {
    private PerformanceReportService performanceReportService;

    private PerformanceReportRepository performanceReportRepository;

    public PerformanceReportController(PerformanceReportService performanceReportService, PerformanceReportRepository performanceReportRepository) {
        this.performanceReportService = performanceReportService;
        this.performanceReportRepository = performanceReportRepository;
    }

    @PostMapping("/api/v1/performance-reports")
    public Result generatePaymentForms(@RequestBody Period periodRange) {
        List<PerformanceReport> performanceReports = this.performanceReportService.generatePerformanceReports(periodRange);
        return new Result(true, StatusCode.SUCCESS, "Payment forms are generated successfully.", performanceReports);
    }

    @GetMapping("/api/v1/performance-reports/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<PerformanceReport> performanceReports = performanceReportRepository.findAll();

        PerformanceReportPDFExporter exporter = new PerformanceReportPDFExporter(performanceReports);
        exporter.export(response);


    }



}
