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
import java.util.stream.IntStream;

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
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id(1);
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682)-365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setSFS_id(2);
        s2.setFirstName("Ryan");
        s2.setLastName("Usell");
        s2.setPhone("(123) 456-789");
        s2.setEmail("dummy_email@gmail.com");
        s2.setInternational(Boolean.FALSE);

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setSFS_id(3);
        s3.setFirstName("Paige");
        s3.setLastName("Anderson");
        s3.setPhone("(123) 456-789");
        s3.setEmail("dummy_email@gmail.com");
        s3.setInternational(Boolean.FALSE);

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setSFS_id(4);
        s4.setFirstName("Annalise");
        s4.setLastName("Gadbois");
        s4.setPhone("(123) 456-789");
        s4.setEmail("dummy_email@gmail.com");
        s4.setInternational(Boolean.FALSE);


        SuperFrogStudent s5 = new SuperFrogStudent();
        s5.setSFS_id(5);
        s5.setFirstName("Danny");
        s5.setLastName("Mairena Jarquin");
        s5.setPhone("(123) 456-789");
        s5.setEmail("dummy_email@gmail.com");
        s5.setInternational(Boolean.TRUE);

        SuperFrogStudent s6 = new SuperFrogStudent();
        s6.setSFS_id(6);
        s6.setFirstName("Hiep");
        s6.setLastName("Nguyen");
        s6.setPhone("(682) 365-5307");
        s6.setEmail("hiep.n.nguyen@tcu.edu");
        s6.setInternational(Boolean.FALSE);

        this.students = new ArrayList<>();
        this.students.add(s1);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testFindByIdSuccess() {

        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id(1);
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682)-365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);

        SpiritDirector d = new SpiritDirector();
        d.setSD_id(1);
        d.setFirstName("Bingyang");
        d.setLastName("Wei");
        s1.setDirector(d);


        //define the behavior of the mock object
        given(studentRepository.findById(1)).willReturn(Optional.of(s1));

        //When - call the method to be tested - act on target behavior

        SuperFrogStudent returnedStudent = studentService.findById(1);


        //Then - compare the result from when and given -> if the insertion is True then test passed
        assertThat(returnedStudent.getSFS_id()).isEqualTo(s1.getSFS_id());
        assertThat(returnedStudent.getFirstName()).isEqualTo(s1.getFirstName());
        assertThat(returnedStudent.getLastName()).isEqualTo(s1.getLastName());
        assertThat(returnedStudent.getAddress()).isEqualTo(s1.getAddress());
        assertThat(returnedStudent.getPhone()).isEqualTo(s1.getPhone());
        assertThat(returnedStudent.getEmail()).isEqualTo(s1.getEmail());
        assertThat(returnedStudent.getInternational()).isEqualTo(s1.getInternational());

        verify(studentRepository, times(1)).findById(1);
    }

    @Test
    void testFindSuperFrogStudentSuccess() {

    }
}