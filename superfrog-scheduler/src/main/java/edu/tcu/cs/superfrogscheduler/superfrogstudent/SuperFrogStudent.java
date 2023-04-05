package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.spiritdirector.SpiritDirector;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.util.PaymentEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class SuperFrogStudent implements Serializable {
    @Id
    private String SFS_id;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String email;

    private Boolean international;

    public SpiritDirector getDirector() {
        return director;
    }

    public void setDirector(SpiritDirector director) {
        this.director = director;
    }

    @ManyToOne
    private SpiritDirector director;

    public String getSFS_id() {
        return SFS_id;
    }

    public void setSFS_id(String SFS_id) {
        this.SFS_id = SFS_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public PaymentEnum getPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(PaymentEnum paymentPreference) {
        this.paymentPreference = paymentPreference;
    }

    private PaymentEnum paymentPreference;


}
