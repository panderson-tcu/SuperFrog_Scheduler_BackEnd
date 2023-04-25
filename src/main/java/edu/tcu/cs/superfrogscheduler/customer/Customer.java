package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import java.io.Serializable;

@Entity
public class Customer implements Serializable{
    @Id
    private String C_id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @OneToMany
    private List <Appearance> eventsList;

    public Customer(){}

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appearance> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Appearance> eventsList) {
        this.eventsList = eventsList;
    }
}
