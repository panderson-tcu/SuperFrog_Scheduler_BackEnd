package edu.tcu.cs.superfrogscheduler.performancereport;


import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
@Entity
public class PerformanceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reportId;
    private String firstName;

    private String lastName;

    @Embedded
    private Period periodRange;

    private Integer numberOfCompletedAppearances;


    public PerformanceReport() {

    }
    public PerformanceReport(String firstName, String lastName, Period periodRange, Integer numberOfCompletedAppearances) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.periodRange = periodRange;
        this.numberOfCompletedAppearances = numberOfCompletedAppearances;
    }


    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Period getPeriodRange() {
        return periodRange;
    }

    public void setPeriodRange(Period periodRange) {
        this.periodRange = periodRange;
    }

    public Integer getNumberOfCompletedAppearances() {
        return numberOfCompletedAppearances;
    }

    public void setNumberOfCompletedAppearances(Integer numberOfCompletedAppearances) {
        this.numberOfCompletedAppearances = numberOfCompletedAppearances;
    }
}

