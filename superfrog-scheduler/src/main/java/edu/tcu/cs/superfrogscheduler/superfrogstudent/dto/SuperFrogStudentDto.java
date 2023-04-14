package edu.tcu.cs.superfrogscheduler.superfrogstudent.dto;
import jakarta.validation.constraints.NotEmpty;

public record SuperFrogStudentDto(

                          @NotEmpty(message="Firs name is required.") String firstName,
                          @NotEmpty(message="Last name is required.") String lastName,
                          @NotEmpty(message="Phone number is required") String phoneNumber,
                          @NotEmpty(message="Email is required") String email) {


}
