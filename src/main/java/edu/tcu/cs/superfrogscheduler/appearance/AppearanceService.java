package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AppearanceService {

    private final AppearanceRepository appearanceRepository;

    public final AppearanceSpecifications appearanceSpecifications;

    public AppearanceService(AppearanceRepository appearanceRepository, AppearanceSpecifications appearanceSpecifications) {
        this.appearanceRepository = appearanceRepository;
        this.appearanceSpecifications = appearanceSpecifications;
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



    //UC 1: Customer requests a SuperFrog appearance
    public Appearance save(Appearance newAppearance) {
        return this.appearanceRepository.save(newAppearance);
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
                    //Update status - protected routing handled on the front-end
                    oldAppearanceRequest.setStatus(updatedAppearanceRequest.getStatus());
                    return this.appearanceRepository.save(oldAppearanceRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Apperance Request", E_id));
    }

    //UC 12: Cancel request
    public Appearance updateStatus(Integer E_id, Appearance updatedAppearanceRequest) {
        return this.appearanceRepository.findById(E_id)
                .map(oldAppearanceRequest -> {
                    //Update status - protected routing handled on the front-end
                    oldAppearanceRequest.setStatus(updatedAppearanceRequest.getStatus());
                    return this.appearanceRepository.save(oldAppearanceRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Apperance Request", E_id));
    }

    //UC 6: Spirit Director/ Superfrog Students finds appearance requests
    public List<Appearance> findAppearanceRequest(String eventTitle, String C_firstName, String C_lastName, AppearanceStatus status){
        Specification<Appearance> searchSpecification = appearanceSpecifications.appearanceFilters(eventTitle, C_firstName, C_lastName, status);
        return this.appearanceRepository.findAll(searchSpecification);
    }







}
