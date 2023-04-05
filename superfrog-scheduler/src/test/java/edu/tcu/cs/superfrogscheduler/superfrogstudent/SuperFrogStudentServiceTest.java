package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.spiritdirector.SpiritDirector;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class SuperFrogStudentServiceTest {

    @Mock
    SuperFrogStudentRepository studentRepository;

    @InjectMocks
    SuperFrogStudentService studentService;

    List<SuperFrogStudent> students;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testFindByIdSuccess() {

        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id("1250808601744904191");
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682)-365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);

        SpiritDirector d = new SpiritDirector();
        d.setSD_id("01");
        d.setFirstName("Bingyang");
        d.setLastName("Wei");
        s1.setDirector(d);


        //define the behavior of the mock object
        given(studentRepository.findById("1250808601744904191")).willReturn(Optional.of(s1));

        //When - call the method to be tested - act on target behavior

        SuperFrogStudent returnedStudent = studentService.findById("1250808601744904191");


        //Then - compare the result from when and given -> if the insertion is True then test passed
        assertThat(returnedStudent.getSFS_id()).isEqualTo(s1.getSFS_id());
        assertThat(returnedStudent.getFirstName()).isEqualTo(s1.getFirstName());
        assertThat(returnedStudent.getLastName()).isEqualTo(s1.getLastName());
        assertThat(returnedStudent.getAddress()).isEqualTo(s1.getAddress());
        assertThat(returnedStudent.getPhone()).isEqualTo(s1.getPhone());
        assertThat(returnedStudent.getEmail()).isEqualTo(s1.getEmail());
        assertThat(returnedStudent.getInternational()).isEqualTo(s1.getInternational());

        verify(studentRepository, times(1)).findById("1250808601744904191");
    }

}