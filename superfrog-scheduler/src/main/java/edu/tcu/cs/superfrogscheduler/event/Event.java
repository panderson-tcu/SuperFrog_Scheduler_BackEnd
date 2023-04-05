package edu.tcu.cs.superfrogscheduler.event;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Event implements Serializable{
    @Id
    private String E_id;

    private String eventTitle;

    private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String eventType;

    private String organizationName;

    private String eventAddress;

    private String onCampus;

    private String specialInstructions;

    private String expenseBen;

    private String outsideOrganizations;

    private String eventDescription;

    private String approvedStatus; // pending, approved, or denied

    @ManyToOne
    private SuperFrogStudent superFrogStudent;

    @ManyToOne
    private Customer customer;

    public Event() {
    }

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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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

    public String getOnCampus() {
        return onCampus;
    }

    public void setOnCampus(String onCampus) {
        this.onCampus = onCampus;
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

    public SuperFrogStudent getSuperFrogStudent() {
        return superFrogStudent;
    }

    public void setSuperFrogStudent(SuperFrogStudent superFrogStudent) {
        this.superFrogStudent = superFrogStudent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
