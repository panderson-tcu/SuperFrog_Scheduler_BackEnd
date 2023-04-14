
package edu.tcu.cs.superfrogscheduler.dbinitializer;

import edu.tcu.cs.superfrogscheduler.appointment.AppointmentRepository;
import edu.tcu.cs.superfrogscheduler.spiritdirector.SpiritDirector;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// make this as a Spring bean
@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SuperFrogStudentRepository studentRepositoryRepository;


    public DBDataInitializer(SuperFrogStudentRepository studentRepositoryRepository) {
        this.studentRepositoryRepository = studentRepositoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id("1");
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682)-365-5307");
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



    }

}
