package edu.tcu.cs.superfrogscheduler.superfrogstudent.converter;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import org.springframework.stereotype.Component;

@Component
public class SuperFrogStudentDtoToSFSConverter {
    public SuperFrogStudent convert(SuperFrogStudentDto source){
        SuperFrogStudent student = new SuperFrogStudent();
        student.setFirstName(source.firstName());
        student.setLastName(source.lastName());
        student.setPhone(source.phoneNumber());
        student.setEmail(source.email());
        student.setAddress(source.address());

        return student;
    }
}