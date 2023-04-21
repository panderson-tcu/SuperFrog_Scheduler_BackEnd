package edu.tcu.cs.superfrogscheduler.paymentform.dto;

import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;

import java.util.List;

public class RequestIds {
    private List<String> requestIds;

    private Period paymentPeriod;


    public RequestIds() {

    }

    public RequestIds(List<String> requestIds, Period paymentPeriod) {
        this.requestIds = requestIds;
        this.paymentPeriod = paymentPeriod;
    }

    public List<String> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(List<String> requestIds) {
        this.requestIds = requestIds;
    }

    public Period getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(Period paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }
}
