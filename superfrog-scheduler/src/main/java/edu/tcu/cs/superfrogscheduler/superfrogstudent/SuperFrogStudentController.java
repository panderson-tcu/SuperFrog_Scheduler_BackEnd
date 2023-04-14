package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SFSToSuperFrogStudentDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SuperFrogStudentDtoToSFSConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService studentService;

    private final SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter;

    private final SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter;




    public SuperFrogStudentController(SuperFrogStudentService studentService, SuperFrogStudentRepository studentRepository, SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter, SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter) {
        this.studentService = studentService;
        this.superFrogStudentDtoToSFSConverter = superFrogStudentDtoToSFSConverter;
        this.sfsToSuperFrogStudentDtoConverter = sfsToSuperFrogStudentDtoConverter;
    }

    @GetMapping("/{SFS_id}")
    public Result findSFSById(@PathVariable String SFS_id) {
        SuperFrogStudent student = this.studentService.findById(SFS_id);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", student);
    }


    //UC 15: Find a SuperFrog Student by criteria
    //Return a list of matching SuperFrog Students
    @PostMapping("/search_students")
    public Result findSFSByMainCriteria(@Valid @RequestBody SuperFrogStudentDto superFrogStudentDto){

        SuperFrogStudent student = this.superFrogStudentDtoToSFSConverter.convert(superFrogStudentDto);
        List<SuperFrogStudent> students = this.studentService.findSuperFrogStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getPhone(),
                student.getEmail());

        List<SuperFrogStudentDto> studentDtos = students
                .stream()
                .map(this.sfsToSuperFrogStudentDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find students success", studentDtos);
    }








}
