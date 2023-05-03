package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository studentRepository;

    private final SuperFrogStudentsSpecifications superFrogStudentsSpecifications;
    private AppearanceRepository appearanceRepository;


    public SuperFrogStudentService(SuperFrogStudentRepository studentRepository, SuperFrogStudentsSpecifications superFrogStudentsSpecifications) {
        this.studentRepository = studentRepository;
        this.superFrogStudentsSpecifications = superFrogStudentsSpecifications;
    }


    //Supplementary: Find all superfrogstudents
    public List<SuperFrogStudent> findAllSuperFrogStudent(){
        return this.studentRepository.findAll();
    }


    //UC 15: Find a SuperFrog Student by criteria
    //Return a list of matching SuperFrog Students
    public List<SuperFrogStudent> findSuperFrogStudent(String firstName, String lastName, String phoneNumber, String email){
        Specification<SuperFrogStudent> searchSpecification = superFrogStudentsSpecifications
                .superFrogStudentFilters(firstName, lastName, phoneNumber, email);

        return this.studentRepository.findAll(searchSpecification);
    }

    //UC 16: View a superfrog student account
    public SuperFrogStudent findById(Integer SFS_id){
        return this.studentRepository.findById(SFS_id).get();
    }




    //Anna


    //UC 20: Edit SuperFrog Student profile information
    //Returns new student profile information
    public SuperFrogStudent update(Integer SFS_id, SuperFrogStudent updatedStudentProfile) {
        return this.studentRepository.findById(SFS_id)
                .map(oldStudentProfile -> {

                    //update SuperFrog Student Profile Information
                    oldStudentProfile.setFirstName(updatedStudentProfile.getFirstName());
                    oldStudentProfile.setLastName(updatedStudentProfile.getLastName());
                    oldStudentProfile.setPhone(updatedStudentProfile.getPhone());
                    oldStudentProfile.setEmail(updatedStudentProfile.getEmail());
                    oldStudentProfile.setAddress(updatedStudentProfile.getAddress());
                    oldStudentProfile.setInternational(updatedStudentProfile.getInternational());
                    oldStudentProfile.setPaymentPreference(updatedStudentProfile.getPaymentPreference());

                    return this.studentRepository.save(oldStudentProfile);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Student Profile", SFS_id));
    }
    //UC 22: Assign appearance to a superFrogStudent
    //Returns student appearance information
    public void assignAppearance(Integer SFS_id, Integer E_id){
        // Find this appearance by Id from DB.
        //Appearance appearanceToBeAssigned = this.appearanceRepository.findById(Id).get();
        //        .orElseThrow(() -> new ObjectNotFoundException("appearance", Id));

        //Appearance appearanceToBeAssigned = this.appearanceRepository.find

        Appearance appearanceToBeAssigned = this.appearanceRepository.findById(E_id).get();

        // Find this SuperFrogStudent by Id fromm DB.
        SuperFrogStudent superFrogStudent = this.studentRepository.findById(SFS_id)
                .orElseThrow(() -> new ObjectNotFoundException("SuperFrog Student", SFS_id));

        // Appearance assignment
        // We need to see if the appearance is already owned by some SuperFrogStudent.
        if (appearanceToBeAssigned.getStudent() == null) {
            superFrogStudent.addStudentAppearance(appearanceToBeAssigned);        }
        // for future would do a notifaction or throw an exception, by design we can only add a superfrog to an appearance if there is someone not signed up
    }

    public void unassignedAppearance(Integer SFS_id, Integer E_id){
        Appearance appearanceToBeUnassigned = this.appearanceRepository.findById(E_id)
                .orElseThrow(() -> new ObjectNotFoundException("appearance", E_id));

        // Find this SuperFrogStudent by Id fromm DB.
        SuperFrogStudent superFrogStudent = this.studentRepository.findById(SFS_id)
                .orElseThrow(() -> new ObjectNotFoundException("SuperFrog Student", SFS_id));

        // We need to see if the appearance is already owned by some SuperFrogStudent.
        if (appearanceToBeUnassigned.getStudent() != null) {
            appearanceToBeUnassigned.getStudent().removeAppearance(appearanceToBeUnassigned);
        }
    }

    // UC 14
    public void delete(Integer SFS_id) {
        this.studentRepository.findById(SFS_id)
                .orElseThrow(() -> new ObjectNotFoundException("student", SFS_id));
        this.studentRepository.deleteById(SFS_id);
    }

    // UC 13
    public SuperFrogStudent save(SuperFrogStudent newArtifact){
        newArtifact.setSFS_id(newArtifact.getSFS_id());
        newArtifact.setFirstName(newArtifact.getFirstName());
        newArtifact.setLastName(newArtifact.getLastName());
        newArtifact.setPhone(newArtifact.getPhone());
        newArtifact.setEmail(newArtifact.getEmail());
        newArtifact.setAddress(newArtifact.getAddress());
        newArtifact.setInternational(newArtifact.getInternational());
        newArtifact.setPaymentPreference(newArtifact.getPaymentPreference());
        return this.studentRepository.save(newArtifact);
    }

    // UC 11
    public SuperFrogStudent cancelSignin(Integer SFS_id, SuperFrogStudent updatedStudentRequest) {
        return this.studentRepository.findById(SFS_id)
                .map(oldAppearanceRequest -> {
                    //Update status - protected routing handled on the front-end
                    oldAppearanceRequest.setAppearance(updatedStudentRequest.getAppearance());
                    return this.studentRepository.save(oldAppearanceRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("SFS sign-up", SFS_id));
    }

}
