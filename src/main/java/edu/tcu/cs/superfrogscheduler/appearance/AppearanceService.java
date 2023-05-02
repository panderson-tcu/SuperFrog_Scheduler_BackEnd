package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class AppearanceService {

    private final AppearanceRepository appearanceRepository;

    public AppearanceService(AppearanceRepository appearanceRepository) {
        this.appearanceRepository = appearanceRepository;
    }

    public Appearance getAppearanceByAppearanceId(Integer E_id){
        return this.appearanceRepository.findById(E_id).get();
    }


    public List<Appearance> getAppearancesByStudentId(Integer SFS_id){
        return this.appearanceRepository.findAppearancesByAssignedStudentSFSid(SFS_id);
    }

    public List<Appearance> getCompletedAppearancesByStudentId(Integer SFS_id){
        return this.appearanceRepository.findAppearancesByAssignedStudentSFSidAndStatusIs(SFS_id, AppearanceStatus.COMPLETED);
    }

    public List<Appearance> getAssignedAppearancesByStudentId(Integer SFS_id){
        return this.appearanceRepository.findAppearancesByAssignedStudentSFSidAndStatusIs(SFS_id, AppearanceStatus.ASSIGNED);
    }



    //UC 2: Customer edits an existing appearance request based on ID
    public Appearance update(Integer E_id, Appearance updatedAppearanceRequest) {
        return this.appearanceRepository.findById(E_id)
                .map(oldAppearanceRequest -> {

                    //update customer information - no change status
                    oldAppearanceRequest.setC_firstName(updatedAppearanceRequest.getC_firstName());
                    oldAppearanceRequest.setC_lastName(updatedAppearanceRequest.getC_lastName());
                    oldAppearanceRequest.setC_phone(updatedAppearanceRequest.getC_phone());
                    oldAppearanceRequest.setC_email(updatedAppearanceRequest.getC_email());
                    oldAppearanceRequest.setSpecialInstructions(updatedAppearanceRequest.getSpecialInstructions());


                    //update event information - status change to pending - cancel current SFS sign-up

                    //Pending Flag
                    Boolean statusChange = Boolean.FALSE;

                    String updatedEventTitle = updatedAppearanceRequest.getEventTitle();
                    if ((!updatedEventTitle.equals(oldAppearanceRequest.getEventTitle()))
                            || (updatedEventTitle != null && oldAppearanceRequest.getEventTitle() == null)
                            || (updatedEventTitle == null && oldAppearanceRequest.getEventTitle() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }

                    LocalDateTime updatedBeginning_time = updatedAppearanceRequest.getBeginning_time();
                    LocalDateTime updatedEnding_time = updatedAppearanceRequest.getEnding_time();

                    /*
                    String updatedEventType = updatedAppearanceRequest.getEventType();
                    if ((!updatedEventType.equals(oldAppearanceRequest.getEventType()))
                            || (updatedEventType != null && oldAppearanceRequest.getEventType() == null)
                            || (updatedEventType == null && oldAppearanceRequest.getEventType() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }*/

                    String updatedOrganizationName = updatedAppearanceRequest.getOrganizationName();
                    if ((!updatedOrganizationName.equals(oldAppearanceRequest.getOrganizationName()))
                            || ( updatedOrganizationName!= null && oldAppearanceRequest.getOrganizationName() == null)
                            || (updatedOrganizationName == null && oldAppearanceRequest.getOrganizationName() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }

                    String updatedEventAddress = updatedAppearanceRequest.getEventAddress();
                    if ((!updatedEventAddress.equals(oldAppearanceRequest.getEventAddress()))
                            || ( updatedEventAddress!= null && oldAppearanceRequest.getEventAddress() == null)
                            || (updatedEventAddress == null && oldAppearanceRequest.getEventAddress() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }

                    Boolean updatedOnCampus = updatedAppearanceRequest.getOnCampus();
                    if (updatedOnCampus != oldAppearanceRequest.getOnCampus()){
                        statusChange = Boolean.TRUE;
                    }

                    String updatedExpenseBen = updatedAppearanceRequest.getExpenseBen();
                    if ((!updatedExpenseBen.equals(oldAppearanceRequest.getExpenseBen()))
                            || ( updatedExpenseBen!= null && oldAppearanceRequest.getExpenseBen() == null)
                            || (updatedExpenseBen == null && oldAppearanceRequest.getExpenseBen() != null)

                    )
                    {
                        statusChange = Boolean.TRUE;
                    }

                    String updatedOutsideOrganizations = updatedAppearanceRequest.getOutsideOrganizations();
                    if ((!updatedOutsideOrganizations.equals(oldAppearanceRequest.getOutsideOrganizations()))
                            || ( updatedOutsideOrganizations!= null && oldAppearanceRequest.getOutsideOrganizations() == null)
                            || (updatedOutsideOrganizations == null && oldAppearanceRequest.getOutsideOrganizations() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }

                    String updatedEventDescription = updatedAppearanceRequest.getEventDescription();
                    if ((!updatedEventDescription.equals(oldAppearanceRequest.getEventDescription()))
                            || ( updatedEventDescription!= null && oldAppearanceRequest.getEventDescription() == null)
                            || (updatedEventDescription == null && oldAppearanceRequest.getEventDescription() != null))
                    {
                        statusChange = Boolean.TRUE;
                    }
                    //Change status, cancel superfrog signup
                    if (statusChange){
                        oldAppearanceRequest.setStatus(AppearanceStatus.PENDING);
                    }
                    //Update old appearance request
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
