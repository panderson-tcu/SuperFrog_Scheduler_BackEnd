package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository studentRepository;

    private final SuperFrogStudentsSpecifications superFrogStudentsSpecifications;

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
}
