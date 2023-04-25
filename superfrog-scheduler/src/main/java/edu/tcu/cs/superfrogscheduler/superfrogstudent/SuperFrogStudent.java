package edu.tcu.cs.superfrogscheduler.superfrogstudent;


import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.EventType;
import edu.tcu.cs.superfrogscheduler.paymentform.PaymentForm;
import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.performancereport.PerformanceReport;
import edu.tcu.cs.superfrogscheduler.spiritdirector.SpiritDirector;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.util.PaymentEnum;
import edu.tcu.cs.superfrogscheduler.system.TransportationFeeCalculator;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class SuperFrogStudent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer SFSid;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String email;

    private Boolean international;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "assignedStudent")
    private List<Appearance> appearances = new ArrayList<>();

    public SpiritDirector getDirector() {
        return director;
    }

    public void setDirector(SpiritDirector director) {
        this.director = director;
    }

    @ManyToOne
    private SpiritDirector director;



    public Integer getSFS_id() {
        return SFSid;
    }

    public void setSFS_id(Integer SFS_id) {
        this.SFSid = SFS_id;
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

    public List<Appearance> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<Appearance> appearances) {
        this.appearances = appearances;
    }

    public void addAppearance(Appearance appearance){
        appearance.setStudent(this);
        this.appearances.add(appearance);
    }

    public void removeAllAppearances() {
        this.appearances.stream().forEach(appearance -> appearance.setStudent(null));
        this.appearances = new ArrayList<>();
    }

    public void removeAppearance(Appearance appearanceToBeRemoved) {
        // Remove artifact owner.
        appearanceToBeRemoved.setStudent(null);
        this.appearances.remove(appearanceToBeRemoved);
    }

    public PaymentForm generatePaymentForm(List<Appearance> requests, Period paymentPeriod) {
        /**
         * Group the given requests by their event type (TCU, NONPROFIT, and PRIVATE), then for each event type, calculate the number of hours
         * this SuperFrogStudent has worked. The result of this step is a Map<EventType, Double>.
         * For example:
         *  EventType.TCU -> 2.5 hrs
         *  EventType.NONPROFIT -> 3 hrs
         *  EventType.PRIVATE -> 2 hrs
         */
        Map<EventType, Double> eventTypeHoursMap = requests.stream().collect(Collectors.groupingBy(request -> request.getEventType(),
                Collectors.mapping(request -> request.getBeginning_time().until(request.getEnding_time(), ChronoUnit.MINUTES) / 60.0,
                        Collectors.reducing(0.0, Double::sum))));

        BigDecimal totalAppearanceFee = new BigDecimal(0.0);

        // Calculate the total appearance fee by going over the map.
        for (Map.Entry<EventType, Double> entry : eventTypeHoursMap.entrySet()) {
            totalAppearanceFee = totalAppearanceFee
                    .add(BigDecimal.valueOf(entry.getKey().getHourlyRate())
                            .multiply(BigDecimal.valueOf(entry.getValue())));
        }

        // We also need to consider transportation fee.
        BigDecimal transportationFee = TransportationFeeCalculator.INSTANCE.calculateTransportationFee(requests);

        BigDecimal totalAmount = totalAppearanceFee.add(transportationFee);

        return new PaymentForm(this.firstName, this.lastName, this.SFSid, paymentPeriod, totalAmount);

    }


    public PerformanceReport generatePerformanceReport(List<Appearance> completedRequests, Period periodRange){
        //this should be the number of completed appearances within the period range
        Integer numberOfCompletedAppearances = completedRequests.size();
        return new PerformanceReport(this.firstName, this.lastName, periodRange, numberOfCompletedAppearances);
    }








}
