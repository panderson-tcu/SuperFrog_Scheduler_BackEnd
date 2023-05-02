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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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



}
