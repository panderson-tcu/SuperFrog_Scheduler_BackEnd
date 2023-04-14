package edu.tcu.cs.superfrogscheduler.superfrogstudent.converter;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import org.springframework.stereotype.Component;

@Component
public class SFSToSuperFrogStudentDtoConverter {

    public SuperFrogStudentDto convert(SuperFrogStudent source) {
        SuperFrogStudentDto studentDto = new SuperFrogStudentDto(
                source.getFirstName(),
                source.getLastName(),
                source.getPhone(),
                source.getEmail()
                );
        return studentDto;
    }
}
