package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceService;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;
import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SFSToSuperFrogStudentDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SuperFrogStudentDtoToSFSConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService studentService;


    private final SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter;

    private final SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter;

    private final AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter;

    private final AppearanceService appearanceService;




    public SuperFrogStudentController(SuperFrogStudentService studentService, SuperFrogStudentRepository studentRepository, SuperFrogStudentRepository studentRepository1, SuperFrogStudentDtoToSFSConverter superFrogStudentDtoToSFSConverter, SFSToSuperFrogStudentDtoConverter sfsToSuperFrogStudentDtoConverter, SuperFrogStudentsSpecifications superFrogStudentsSpecifications, AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter, AppearanceService appearanceService, AppearanceRepository appearanceRepository) {
        this.studentService = studentService;
        this.superFrogStudentDtoToSFSConverter = superFrogStudentDtoToSFSConverter;
        this.sfsToSuperFrogStudentDtoConverter = sfsToSuperFrogStudentDtoConverter;
        this.appearanceToAppearanceDtoConverter = appearanceToAppearanceDtoConverter;
        this.appearanceService = appearanceService;
    }


    //Supplemetary: Find all SuperFrogStudents

    @GetMapping("/find_all")
    public Result findAllSuperFrogStudents(){
        List<SuperFrogStudent> students = this.studentService.findAllSuperFrogStudent();
        List<SuperFrogStudentDto> studentDtos = students
                .stream()
                .map(this.sfsToSuperFrogStudentDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find all students success", studentDtos);
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

    //UC 16: Find a SuperFrogStudent Account by ID
    @GetMapping("/{SFS_id}")
    public Result findSFSById(@PathVariable Integer SFS_id) {
        SuperFrogStudent student = this.studentService.findById(SFS_id);
        SuperFrogStudentDto studentDto = this.sfsToSuperFrogStudentDtoConverter.convert(student);


        List<Appearance> completedAppearances = this.appearanceService.getCompletedAppearancesByStudentId(SFS_id);

        List<AppearanceDto> completedAppearanceDtos = completedAppearances
                .stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)
                .toList();

        List<Appearance> assignedAppearances = this.appearanceService.getAssignedAppearancesByStudentId(SFS_id);
        List<AppearanceDto> assignedAppearancesDtos = assignedAppearances
                .stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)
                .toList();

        //how to merge studentDto and appearanceDtos together and respond all at once??
        Map<String, Object> response = new HashMap<>();
        response.put("super-frog-student", studentDto);
        response.put("completed_appearances", completedAppearanceDtos);
        response.put("assigned_appearances", assignedAppearancesDtos);
        return new Result(true, StatusCode.SUCCESS, "Found account success!", response);
    }



    @PutMapping("/{SFS_id}")
    public Result updatedStudentProfile(@PathVariable Integer SFS_id, @Valid @RequestBody SuperFrogStudentDto studentDto) {
        SuperFrogStudent update = this.superFrogStudentDtoToSFSConverter.convert(studentDto);
        SuperFrogStudent updatedStudentProfile = this.studentService.update(SFS_id, update);
        SuperFrogStudentDto updatedStudentProfileDto = this.sfsToSuperFrogStudentDtoConverter.convert(updatedStudentProfile);

        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedStudentProfileDto);
    }

    @PutMapping("/{SFS_id}/appearance/{Id}")
    public Result assignAppearance(@PathVariable Integer SFS_id, @PathVariable Integer Id) {
        this.studentService.assignAppearance(SFS_id, Id);
        return new Result(true, StatusCode.SUCCESS, "Appearance Assignment Success");
    }

    @PutMapping("/{SFS_id}/appearance/{E_id}")
    public Result unassignedAppearance(@PathVariable Integer SFS_id, @PathVariable Integer E_id) {
        this.studentService.unassignedAppearance(SFS_id, E_id);
        return new Result(true, StatusCode.SUCCESS, "Cancel SignUp Success");
    }

    // UC 14
    @DeleteMapping("/{SFS_id}")
    public Result deleteUser(@PathVariable Integer SFS_id) {
        this.studentService.delete(SFS_id);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    // UC 13
    @PostMapping
    public Result addStudent(@Valid @RequestBody SuperFrogStudentDto studentDto){
        SuperFrogStudent newStudent = this.superFrogStudentDtoToSFSConverter.convert(studentDto);
        SuperFrogStudent savedStudent = this.studentService.save(newStudent);
        SuperFrogStudentDto savedStudentDto = this.sfsToSuperFrogStudentDtoConverter.convert(savedStudent);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedStudentDto);
    }




}
