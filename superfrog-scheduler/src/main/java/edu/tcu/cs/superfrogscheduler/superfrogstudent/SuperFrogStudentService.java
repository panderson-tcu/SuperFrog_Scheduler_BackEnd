package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository studentRepository;

    public SuperFrogStudentService(SuperFrogStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public SuperFrogStudent findById(String SFS_id){
        return this.studentRepository.findById(SFS_id).get();
    }


    //UC 15: Find a SuperFrog Student by criteria
    //Return a list of matching SuperFrog Students
    public List<SuperFrogStudent> findSuperFrogStudent(String firstName, String lastName, String phoneNumber, String email){
        return this.studentRepository.findSuperFrogStudentsByFirstNameAndLastNameAndPhoneAndEmail(firstName, lastName, phoneNumber, email);
    }



}
