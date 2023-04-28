package edu.tcu.cs.superfrogscheduler.appearance.converter;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import org.springframework.stereotype.Component;

@Component
public class AppearanceToAppearanceDtoConverter {
    public AppearanceDto convert(Appearance source){
        AppearanceDto appearanceRequestDto = new AppearanceDto(

                //Customer information
                source.getId(),
                source.getC_firstName(),
                source.getC_lastName(),
                source.getC_phone(),
                source.getC_email(),

                //Event information
                source.getEventTitle(),
                source.getBeginning_time(),
                source.getEnding_time(),
                source.getEventType(),
                source.getOrganizationName(),
                source.getEventAddress(),
                source.getOnCampus(),
                source.getSpecialInstructions(),
                source.getExpenseBen(),
                source.getOutsideOrganizations(),
                source.getEventDescription(),
                source.getStatus()

        );

        return appearanceRequestDto;
    }
}
