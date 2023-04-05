package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SuperFrogStudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperFrogStudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    List<SuperFrogStudent> students;

    @BeforeEach
    void setUp() {

        this.students = new ArrayList<>();

        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id("1250808601744904191");
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682)-365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);
        this.students.add(s1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findSFSById() {
    }
    @Test
    void testFindSFSByIdSuccess() throws Exception {


        // Given
        given(this.studentService.findById("1250808601744904191")).willReturn(this.students.get(0));

        // When and then
        this.mockMvc.perform(get("/api/v1/students/1250808601744904191").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.sfs_id").value("1250808601744904191"))
                .andExpect(jsonPath("$.data.firstName").value("Hiep"));



    }
}