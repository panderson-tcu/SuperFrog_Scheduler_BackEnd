package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppearanceService {

    private final AppearanceRepository appearanceRepository;

    public AppearanceService(AppearanceRepository appearanceRepository) {
        this.appearanceRepository = appearanceRepository;
    }

    public Appearance getAppearanceById(String E_id){
        return this.appearanceRepository.findById(E_id).get();
    }

    //UC 2: Customer edits an existing appearance request based on ID
    public Appearance update(String E_id, Appearance updatedAppearanceRequest) {
        return this.appearanceRepository.findById(E_id)
                .map(oldAppearanceRequest -> {

                    //update customer information - no change status
                    oldAppearanceRequest.setC_firstName(updatedAppearanceRequest.getC_firstName());
                    oldAppearanceRequest.setC_lastName(updatedAppearanceRequest.getC_lastName());
                    oldAppearanceRequest.setC_phone(updatedAppearanceRequest.getC_phone());
                    oldAppearanceRequest.setC_email(updatedAppearanceRequest.getC_email());
                    oldAppearanceRequest.setSpecialInstructions(updatedAppearanceRequest.getSpecialInstructions());


                    //update event information - status change to pending - cancel current SFS sign-up
                    oldAppearanceRequest.setEventTitle(updatedAppearanceRequest.getEventTitle());
                    oldAppearanceRequest.setBeginning_time(updatedAppearanceRequest.getBeginning_time());
                    oldAppearanceRequest.setEnding_time(updatedAppearanceRequest.getEnding_time());
                    oldAppearanceRequest.setEventType(updatedAppearanceRequest.getEventType());
                    oldAppearanceRequest.setOrganizationName(updatedAppearanceRequest.getOrganizationName());
                    oldAppearanceRequest.setEventAddress(updatedAppearanceRequest.getEventAddress());
                    oldAppearanceRequest.setOnCampus(updatedAppearanceRequest.getOnCampus());
                    oldAppearanceRequest.setExpenseBen(updatedAppearanceRequest.getExpenseBen());
                    oldAppearanceRequest.setOutsideOrganizations(updatedAppearanceRequest.getOutsideOrganizations());
                    oldAppearanceRequest.setEventDescription(updatedAppearanceRequest.getEventDescription());


                    return this.appearanceRepository.save(oldAppearanceRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Apperance Request", E_id));
    }


}
