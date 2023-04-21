package edu.tcu.cs.superfrogscheduler.appearance.converter;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import org.springframework.stereotype.Component;

@Component
public class AppearanceDtoToAppearanceConverter {
    public Appearance convert(AppearanceDto source){
        Appearance appearanceRequest = new Appearance();

        //Customer information
        appearanceRequest.setC_firstName(source.C_firstName());
        appearanceRequest.setC_lastName(source.C_lastName());
        appearanceRequest.setC_phone(source.C_phone());
        appearanceRequest.setC_email(source.C_email());

        //Event information
        appearanceRequest.setEventTitle(source.eventTitle());
        appearanceRequest.setBeginning_time(source.beginning_time());
        appearanceRequest.setEnding_time(source.ending_time());
        appearanceRequest.setEventType(source.eventType());
        appearanceRequest.setOrganizationName(source.organizationName());
        appearanceRequest.setEventAddress(source.eventAddress());
        appearanceRequest.setOnCampus(source.onCampus());
        appearanceRequest.setSpecialInstructions(source.specialInstructions());
        appearanceRequest.setExpenseBen(source.expenseBen());
        appearanceRequest.setOutsideOrganizations(source.outsideOrganizations());
        appearanceRequest.setEventDescription(source.eventDescription());

        //miss - status - E_id

        return appearanceRequest;
    }
}
