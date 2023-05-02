package edu.tcu.cs.superfrogscheduler.paymentform.dto;

import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;

import java.util.List;

public class RequestIds {
    private List<Integer> requestIds;

    private Period periodRange;


    public RequestIds() {

    }

    public RequestIds(List<Integer> requestIds, Period periodRange) {
        this.requestIds = requestIds;
        this.periodRange = periodRange;
    }

    public List<Integer> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(List<Integer> requestIds) {
        this.requestIds = requestIds;
    }

    public Period getPeriodRange() {
        return periodRange;
    }

    public void setPeriodRange(Period periodRange) {
        this.periodRange = periodRange;
    }
}
