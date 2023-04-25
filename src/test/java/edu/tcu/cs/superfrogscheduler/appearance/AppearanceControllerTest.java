package edu.tcu.cs.superfrogscheduler.appearance;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AppearanceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppearanceService appearanceService;

    @Autowired
    ObjectMapper objectMapper;

    List<Appearance> appearances;


    @BeforeEach
    void setUp() {
        this.appearances = new ArrayList<>();

        LocalDateTime mockStartTime = LocalDateTime.of(2023, 12, 23, 11, 0, 0);
        LocalDateTime mockEndTime = LocalDateTime.of(2023, 12, 23, 15, 0, 0);

        Appearance a1 = new Appearance();
        a1.setId(1);
        a1.setEventTitle("Boschini Birthday");
        //a1.setStartTime(mockStartTime);
        //a1.setEndTime(mockEndTime);
        a1.setEventType(EventType.PRIVATE);
        a1.setOrganizationName("Boschini Million Dollar Group");
        a1.setEventAddress("2800 S University Dr, Fort Worth, TX 76129");
        a1.setOnCampus(Boolean.FALSE);
        a1.setSpecialInstructions(null);
        a1.setExpenseBen(null);
        a1.setOutsideOrganizations(null);
        a1.setEventDescription("The annual millionare party organized by Boschini");
        a1.setStatus(null);

        this.appearances.add(a1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAppearanceByAppearanceIdSuccess() throws Exception{
        // Given
        given(this.appearanceService.getAppearanceByAppearanceId(1)).willReturn(this.appearances.get(0));

        // When and then
        this.mockMvc.perform(get("/api/v1/appearances/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find appearance Success"))
                .andExpect(jsonPath("$.data.eventTitle").value("Boschini Birthday"));

    }
}