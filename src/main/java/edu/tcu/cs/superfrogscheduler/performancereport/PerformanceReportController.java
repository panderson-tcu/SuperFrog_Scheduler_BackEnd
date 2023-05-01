package edu.tcu.cs.superfrogscheduler.performancereport;

import com.lowagie.text.DocumentException;
import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;
import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.paymentform.dto.RequestIds;
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

    private AppearanceRepository appearanceRepository;

    private AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter;

    public PerformanceReportController(PerformanceReportService performanceReportService, PerformanceReportRepository performanceReportRepository, AppearanceRepository appearanceRepository, AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter) {
        this.performanceReportService = performanceReportService;
        this.performanceReportRepository = performanceReportRepository;
        this.appearanceRepository = appearanceRepository;
        this.appearanceToAppearanceDtoConverter = appearanceToAppearanceDtoConverter;
    }



    //UC-19: Generate performance reports based on selected student IDs, and selected period
    @PostMapping("/api/v1/performance-reports")
    public Result generatePerformanceReports(@RequestBody RequestIds requestIds){
        List<Integer> selectedIds = requestIds.getRequestIds();

        Period periodRange = requestIds.getPeriodRange();

        List<PerformanceReport> performanceReports = this.performanceReportService.generatePerformanceReports(selectedIds, periodRange);

        return new Result(true, StatusCode.SUCCESS, "Performance reports are generated successfully.", performanceReports);

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
