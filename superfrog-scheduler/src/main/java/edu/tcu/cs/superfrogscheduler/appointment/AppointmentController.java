package edu.tcu.cs.superfrogscheduler.appointment;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{E_id}")
    public Result getAppointmentById(@PathVariable String E_id) {
        Appointment appointment = this.appointmentService.getAppointmentById(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appointment Success", appointment);
    }
}
