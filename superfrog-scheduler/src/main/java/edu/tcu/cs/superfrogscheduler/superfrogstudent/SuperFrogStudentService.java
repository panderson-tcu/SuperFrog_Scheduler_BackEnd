package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository studentRepository;

    private final AppearanceRepository appearanceRepository;

    private final SuperFrogStudentsSpecifications superFrogStudentsSpecifications;

    public SuperFrogStudentService(SuperFrogStudentRepository studentRepository, AppearanceRepository appearanceRepository, SuperFrogStudentsSpecifications superFrogStudentsSpecifications) {
        this.studentRepository = studentRepository;
        this.appearanceRepository = appearanceRepository;
        this.superFrogStudentsSpecifications = superFrogStudentsSpecifications;
    }

    public SuperFrogStudent findById(String SFS_id){
        return this.studentRepository.findById(SFS_id).get();
    }


    //UC 15: Find a SuperFrog Student by criteria
    //Return a list of matching SuperFrog Students
    public List<SuperFrogStudent> findSuperFrogStudent(String firstName, String lastName, String phoneNumber, String email){
        Specification<SuperFrogStudent> searchSpecification = superFrogStudentsSpecifications
                .superFrogStudentFilters(firstName, lastName, phoneNumber, email);

        return this.studentRepository.findAll(searchSpecification);
    }


    //UC 20: Edit SuperFrog Student profile information
    //Returns new student profile information
    public SuperFrogStudent update(String SFS_id, SuperFrogStudent updatedStudentProfile) {
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
    public void assignAppearance(String SFS_id, String E_id){
        // Find this appearance by Id from DB.
        Appearance appearanceToBeAssigned = this.appearanceRepository.findById(E_id)
                .orElseThrow(() -> new ObjectNotFoundException("appearance", E_id));

        // Find this SuperFrogStudent by Id fromm DB.
        SuperFrogStudent superFrogStudent = this.studentRepository.findById(SFS_id)
                .orElseThrow(() -> new ObjectNotFoundException("SuperFrog Student", SFS_id));

        // Appearance assignment
        // We need to see if the appearance is already owned by some SuperFrogStudent.
        if (appearanceToBeAssigned.getWorker() == null) {
            superFrogStudent.addAppearance(appearanceToBeAssigned);
        }
        // for future would do a notifaction or throw an exception, by design we can only add a superfrog to an appearance if there is someone not signed up
    }

//    public void unassignAppearance(String SFS_id, String E_id){
//        Appearance appearanceToBeUnassigned = this.appearanceRepository.findById(E_id)
//                .orElseThrow(() -> new ObjectNotFoundException("appearance", E_id));
//
//        // Find this SuperFrogStudent by Id fromm DB.
//        SuperFrogStudent superFrogStudent = this.studentRepository.findById(SFS_id)
//                .orElseThrow(() -> new ObjectNotFoundException("SuperFrog Student", SFS_id));
//
//        // We need to see if the appearance is already owned by some SuperFrogStudent.
//        if (appearanceToBeUnassigned.getWorker() != null) {
//            appearanceToBeUnassigned.getWorker().removeAppearance(appearanceToBeUnassigned);
//        }
//    }

}
