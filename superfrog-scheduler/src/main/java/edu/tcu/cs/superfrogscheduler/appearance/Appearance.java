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

    //Customer information
    private String C_firstName;

    private String C_lastName;

    private String C_phone;

    private String C_email;



//Event information

    private String eventTitle;


    //@Column(name = "start")
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    //private LocalDateTime start;

    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    //@Column(name = "end")
    //private LocalDateTime end;


    private String eventType;

    private String organizationName;

    private String eventAddress;

    private Boolean onCampus;

    private String specialInstructions;

    private String expenseBen;

    private String outsideOrganizations;

    private String eventDescription;

    @Enumerated(EnumType.STRING)
    private AppearanceStatus status;

    public Appearance() {
    }

    //@ManyToOne
    //private SuperFrogStudent superFrogStudent;

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

    /*
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    */

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
}
