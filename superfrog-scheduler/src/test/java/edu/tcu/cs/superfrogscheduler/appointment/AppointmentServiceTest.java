package edu.tcu.cs.superfrogscheduler.appointment;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAppointmentByIdSuccess() {

        LocalDateTime mockStartTime = LocalDateTime.of(2023, 12, 23, 11, 0, 0);
        LocalDateTime mockEndTime = LocalDateTime.of(2023, 12, 23, 15, 0, 0);

        Appointment a1 = new Appointment();
        a1.setE_id("1");
        a1.setEventTitle("Boschini Birthday");
        a1.setStartTime(mockStartTime);
        a1.setEndTime(mockEndTime);
        a1.setEventType("Private");
        a1.setOrganizationName("Boschini Million Dollar Group");
        a1.setEventAddress("2800 S University Dr, Fort Worth, TX 76129");
        a1.setOnCampus(Boolean.FALSE);
        a1.setSpecialInstructions(null);
        a1.setExpenseBen(null);
        a1.setOutsideOrganizations(null);
        a1.setEventDescription("The annual millionare party organized by Boschini");
        a1.setStatus(null);


        //define the behavior of the mock object
        given(appointmentRepository.findById("1")).willReturn(Optional.of(a1));

        //When - call the method to be tested - act on target behavior

        Appointment returnedAppointment = appointmentService.getAppointmentById("1");


        //Then - compare the result from when and given -> if the insertion is True then test passed
        assertThat(returnedAppointment.getE_id()).isEqualTo(a1.getE_id());
        assertThat(returnedAppointment.getEventTitle()).isEqualTo(a1.getEventTitle());
        assertThat(returnedAppointment.getStartTime()).isEqualTo(a1.getStartTime());
        assertThat(returnedAppointment.getEndTime()).isEqualTo(a1.getEndTime());
        assertThat(returnedAppointment.getEventType()).isEqualTo(a1.getEventType());
        assertThat(returnedAppointment.getOrganizationName()).isEqualTo(a1.getOrganizationName());
        assertThat(returnedAppointment.getEventAddress()).isEqualTo(a1.getEventAddress());
        assertThat(returnedAppointment.getOnCampus()).isEqualTo(a1.getOnCampus());
        assertThat(returnedAppointment.getSpecialInstructions()).isEqualTo(a1.getSpecialInstructions());
        assertThat(returnedAppointment.getExpenseBen()).isEqualTo(a1.getExpenseBen());
        assertThat(returnedAppointment.getOutsideOrganizations()).isEqualTo(a1.getOutsideOrganizations());
        assertThat(returnedAppointment.getEventDescription()).isEqualTo(a1.getEventDescription());
        assertThat(returnedAppointment.getStatus()).isEqualTo(a1.getStatus());


        verify(appointmentRepository, times(1)).findById("1");

    }
}