package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id("1");
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682) 365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setSFS_id("2");
        s2.setFirstName("Ryan");
        s2.setLastName("Usell");
        s2.setPhone("(123) 456-789");
        s2.setEmail("dummy_email@gmail.com");
        s2.setInternational(Boolean.FALSE);

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setSFS_id("3");
        s3.setFirstName("Paige");
        s3.setLastName("Anderson");
        s3.setPhone("(123) 456-789");
        s3.setEmail("dummy_email@gmail.com");
        s3.setInternational(Boolean.FALSE);

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setSFS_id("4");
        s4.setFirstName("Annalise");
        s4.setLastName("Gadbois");
        s4.setPhone("(123) 456-789");
        s4.setEmail("dummy_email@gmail.com");
        s4.setInternational(Boolean.FALSE);


        SuperFrogStudent s5 = new SuperFrogStudent();
        s5.setSFS_id("5");
        s5.setFirstName("Danny");
        s5.setLastName("Mairena Jarquin");
        s5.setPhone("(123) 456-789");
        s5.setEmail("dummy_email@gmail.com");
        s5.setInternational(Boolean.TRUE);

        SuperFrogStudent s6 = new SuperFrogStudent();
        s6.setSFS_id("6");
        s6.setFirstName("Hiep");
        s6.setLastName("Nguyen");
        s6.setPhone("(682) 365-5307");
        s6.setEmail("hiep.n.nguyen@tcu.edu");
        s6.setInternational(Boolean.FALSE);

        this.students = new ArrayList<>();
        this.students.add(s1);
        //this.students.add(s6);

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
        given(this.studentService.findById("1")).willReturn(this.students.get(0));

        // When and then
        this.mockMvc.perform(get("/api/v1/students/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.sfs_id").value("1"))
                .andExpect(jsonPath("$.data.firstName").value("Hiep"));
    }

    @Test
    void testFindSFSByMainCriteriaSuccess() throws Exception{

        //Given
        SuperFrogStudentDto studentDto = new SuperFrogStudentDto(
                "Hiep",
                "Nguyen",
                "(682) 365-5307",
                "hiep.n.nguyen@tcu.edu"
                );

        String json = this.objectMapper.writeValueAsString(studentDto);

        given(this.studentService.findSuperFrogStudent(
                "Hiep",
                "Nguyen",
                "(682) 365-5307",
                "hiep.n.nguyen@tcu.edu")).willReturn(students);

        this.mockMvc.perform(post("/api/v1/students/search_students").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find students success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.students.size())))
                .andExpect(jsonPath("$.data[0].firstName").value("Hiep"))

                .andExpect(jsonPath("$.data[0].lastName").value("Hiep"))



        ;
    }
}