package edu.tcu.cs.superfrogscheduler.appearance.dto;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AppearanceDto(

        //String E_id,

        String C_firstName,

        String C_lastName,

        String C_phone,

        String C_email,

        String eventTitle,

        //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        //LocalDateTime start,

        //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        //LocalDateTime end,
        String eventType,

        String organizationName,

        String eventAddress,

        Boolean onCampus,

        String specialInstructions,

        String expenseBen,

        String outsideOrganizations,

        String eventDescription

        //@Enumerated(EnumType.STRING)
        //AppearanceStatus status


) {}
