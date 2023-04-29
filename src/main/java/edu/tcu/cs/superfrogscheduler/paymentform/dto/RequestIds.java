package edu.tcu.cs.superfrogscheduler.paymentform.dto;

import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;

import java.util.List;

public class RequestIds {
    private List<Integer> requestIds;

    private Period paymentPeriod;


    public RequestIds() {

    }

    public RequestIds(List<Integer> requestIds, Period paymentPeriod) {
        this.requestIds = requestIds;
        this.paymentPeriod = paymentPeriod;
    }

    public List<Integer> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(List<Integer> requestIds) {
        this.requestIds = requestIds;
    }

    public Period getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(Period paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }
}
