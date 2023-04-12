package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

import java.io.Serializable;

import java.time.LocalDateTime;

@Entity
public class Appearance implements Serializable{
    @Id
    private String E_id;

    private String eventTitle;

    @Column(name = "start")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "end")
    private LocalDateTime endTime;

    private String eventType;

    private String organizationName;

    private String eventAddress;

    private Boolean onCampus;



    private String specialInstructions;

    private String expenseBen;

    private String outsideOrganizations;

    private String eventDescription;

    //private String approvedStatus; // pending, approved, or denied
    @Enumerated(EnumType.STRING)
    private AppearanceStatus status;

    @ManyToOne
    private SuperFrogStudent superFrogStudent;

    @ManyToOne
    private Customer customer;


    public String getE_id() {
        return E_id;
    }

    public void setE_id(String e_id) {
        E_id = e_id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setOnCampus(Boolean onCampus) {
        this.onCampus = onCampus;
    }
    public Boolean getOnCampus() {
        return onCampus;
    }



    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getExpenseBen() {
        return expenseBen;
    }

    public void setExpenseBen(String expenseBen) {
        this.expenseBen = expenseBen;
    }

    public String getOutsideOrganizations() {
        return outsideOrganizations;
    }

    public void setOutsideOrganizations(String outsideOrganizations) {
        this.outsideOrganizations = outsideOrganizations;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public AppearanceStatus getStatus() {
        return status;
    }

    public void setStatus(AppearanceStatus status) {
        this.status = status;
    }
}
