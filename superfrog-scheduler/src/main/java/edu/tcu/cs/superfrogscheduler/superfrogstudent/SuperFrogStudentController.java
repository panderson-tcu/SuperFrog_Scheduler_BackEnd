package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService studentService;
    public SuperFrogStudentController(SuperFrogStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{SFS_id}")
    public Result findSFSById(@PathVariable String SFS_id) {
        SuperFrogStudent student = this.studentService.findById(SFS_id);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", student);
    }



}
