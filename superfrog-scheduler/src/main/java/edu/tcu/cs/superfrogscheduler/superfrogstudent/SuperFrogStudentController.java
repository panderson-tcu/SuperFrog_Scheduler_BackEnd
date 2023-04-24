package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SFSToSuperFrogStudentDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SuperFrogStudentDtoToSFSConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService studentService;

    private final SuperFrogStudentRepository studentRepository;

    private final SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter;

    private final SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter;

    private final SuperFrogStudentsSpecifications superFrogStudentsSpecifications;




    public SuperFrogStudentController(SuperFrogStudentService studentService, SuperFrogStudentRepository studentRepository, SuperFrogStudentRepository studentRepository1, SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter, SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter, SuperFrogStudentsSpecifications superFrogStudentsSpecifications) {
        this.studentService = studentService;
        this.studentRepository = studentRepository1;
        this.superFrogStudentDtoToSFSConverter = superFrogStudentDtoToSFSConverter;
        this.sfsToSuperFrogStudentDtoConverter = sfsToSuperFrogStudentDtoConverter;
        this.superFrogStudentsSpecifications = superFrogStudentsSpecifications;
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

        List<SuperFrogStudent> students = this.studentService
                .findSuperFrogStudent(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail());

        List<SuperFrogStudentDto> studentDtos = students
                .stream()
                .map(this.sfsToSuperFrogStudentDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find students success", studentDtos);
    }


    @PutMapping("/{SFS_id}")
    public Result updatedStudentProfile(@PathVariable String SFS_id, @Valid @RequestBody SuperFrogStudentDto studentDto) {
        SuperFrogStudent update = this.superFrogStudentDtoToSFSConverter.convert(studentDto);
        SuperFrogStudent updatedStudentProfile = this.studentService.update(SFS_id, update);
        SuperFrogStudentDto updatedStudentProfileDto = this.sfsToSuperFrogStudentDtoConverter.convert(updatedStudentProfile);

        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedStudentProfileDto);
    }

    @PutMapping("/{SFS_id}/appearance/{E_id}")
    public Result assignAppearance(@PathVariable String SFS_id, @PathVariable String E_id) {
        this.studentService.assignAppearance(SFS_id, E_id);
        return new Result(true, StatusCode.SUCCESS, "Appearance Assignment Success");
    }

    @PutMapping("/{SFS_id}/appearance/{E_id}")
    public Result unassignAppearance(@PathVariable String SFS_id, @PathVariable String E_id) {
        this.studentService.unassignAppearance(SFS_id, E_id);
        return new Result(true, StatusCode.SUCCESS, "Cancel SignUp Success");
    }


}
