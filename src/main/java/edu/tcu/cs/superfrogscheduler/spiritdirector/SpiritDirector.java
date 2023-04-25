package edu.tcu.cs.superfrogscheduler.spiritdirector;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class SpiritDirector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer SD_id;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String address;

    @OneToMany(mappedBy = "director")
    private List<SuperFrogStudent> students;

    public Integer getSD_id() {
        return SD_id;
    }

    public void setSD_id(Integer SD_id) {
        this.SD_id = SD_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SuperFrogStudent> getStudents() {
        return students;
    }

    public void setStudents(List<SuperFrogStudent> students) {
        this.students = students;
    }
}
