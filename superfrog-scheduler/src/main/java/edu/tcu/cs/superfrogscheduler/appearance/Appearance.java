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
    private String Id;

    //Customer information
    private String C_firstName;

    private String C_lastName;

    private String C_phone;

    private String C_email;



//Event information

    private String eventTitle;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime beginning_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ending_time;

    public LocalDateTime getBeginning_time() {
        return beginning_time;
    }

    public void setBeginning_time(LocalDateTime beginning_time) {
        this.beginning_time = beginning_time;
    }

    public LocalDateTime getEnding_time() {
        return ending_time;
    }

    public void setEnding_time(LocalDateTime ending_time) {
        this.ending_time = ending_time;
    }

    private EventType eventType;

    private String organizationName;

    private String eventAddress;

    private Boolean onCampus;

    private String specialInstructions;

    private String expenseBen;

    private String outsideOrganizations;

    private String eventDescription;

    private Double mileage;

    @Enumerated(EnumType.STRING)
    private AppearanceStatus status;

    @ManyToOne
    private SuperFrogStudent student;

    public Appearance() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
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


    public Double getMileageOver(Double freeMileage) {
        return this.mileage.compareTo(freeMileage) <= 0 ? 0.0 : this.mileage - freeMileage;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
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

    public void setC_firstName(String c_firstName) {
        C_firstName = c_firstName;
    }
    public String getC_firstName() {
        return C_firstName;
    }
    public String getC_lastName() {
        return C_lastName;
    }

    public void setC_lastName(String c_lastName) {
        C_lastName = c_lastName;
    }

    public String getC_phone() {
        return C_phone;
    }

    public void setC_phone(String c_phone) {
        C_phone = c_phone;
    }

    public String getC_email() {
        return C_email;
    }

    public void setC_email(String c_email) {
        C_email = c_email;
    }

    public SuperFrogStudent getStudent() {
        return student;
    }

    public void setStudent(SuperFrogStudent student) {
        this.student = student;
    }
}
