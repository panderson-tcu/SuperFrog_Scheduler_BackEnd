package edu.tcu.cs.superfrogscheduler.appointment;

public enum AppointmentStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED_BY_CO,
    CANCELLED_BY_SD,
    CANCELLED_NO_PAYMENT,

    ASSIGNED,
    COMPLETED,
    INCOMPLETE,
    PAYROLL,
}
