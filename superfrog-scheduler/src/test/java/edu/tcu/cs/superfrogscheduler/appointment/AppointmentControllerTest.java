package edu.tcu.cs.superfrogscheduler.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentService;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppointmentService appointmentService;

    @Autowired
    ObjectMapper objectMapper;

    List<Appointment> appointments;


    @BeforeEach
    void setUp() {
        this.appointments = new ArrayList<>();

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

        this.appointments.add(a1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAppointmentByIdSuccess() throws Exception{
        // Given
        given(this.appointmentService.getAppointmentById("1")).willReturn(this.appointments.get(0));

        // When and then
        this.mockMvc.perform(get("/api/v1/appointments/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find appointment Success"))
                .andExpect(jsonPath("$.data.eventTitle").value("Boschini Birthday"));

    }
}