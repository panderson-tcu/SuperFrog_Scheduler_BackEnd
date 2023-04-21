
package edu.tcu.cs.superfrogscheduler.dbinitializer;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.appearance.EventType;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// make this as a Spring bean
@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SuperFrogStudentRepository studentRepositoryRepository;

    private final AppearanceRepository appearanceRepository;



    public DBDataInitializer(SuperFrogStudentRepository studentRepositoryRepository, AppearanceRepository appearanceRepository) {
        this.studentRepositoryRepository = studentRepositoryRepository;
        this.appearanceRepository = appearanceRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        //SFS
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

        studentRepositoryRepository.save(s1);
        studentRepositoryRepository.save(s2);
        studentRepositoryRepository.save(s3);
        studentRepositoryRepository.save(s4);
        studentRepositoryRepository.save(s5);


        //Appearance requests


        Appearance a1 = new Appearance();
        a1.setId("1");
        a1.setC_firstName("Hiep");
        a1.setC_lastName("Nguyen");
        a1.setC_email("hiep.n.nguyen@tcu.edu");
        a1.setC_phone("(682) 365-5307");
        a1.setEventTitle("Boschini Birthday");

        LocalDateTime mockStartTime = LocalDateTime.of(2023, 12, 23, 11, 0, 0);
        a1.setBeginning_time(mockStartTime);

        LocalDateTime mockEndTime = LocalDateTime.of(2023, 12, 23, 15, 0, 0);
        a1.setEnding_time(mockEndTime);

        a1.setEventType(EventType.TCU);
        a1.setOrganizationName("Boschini Million Dollar Group");
        a1.setEventAddress("2800 S University Dr, Fort Worth, TX 76129");
        a1.setOnCampus(Boolean.FALSE);
        a1.setSpecialInstructions(null);
        a1.setExpenseBen(null);
        a1.setOutsideOrganizations(null);
        a1.setEventDescription("The annual millionaire party organized by Boschini");
        a1.setStatus(null);
        a1.setMileage(50.0);
        a1.setStudent(s1);

        appearanceRepository.save(a1);





    }

}
