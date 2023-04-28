
package edu.tcu.cs.superfrogscheduler.dbinitializer;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceRepository;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceStatus;
import edu.tcu.cs.superfrogscheduler.appearance.EventType;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;

import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// make this as a Spring bean
@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SuperFrogStudentRepository studentRepository;

    private final AppearanceRepository appearanceRepository;

    private final UserService userService;


    public DBDataInitializer(SuperFrogStudentRepository studentRepository, AppearanceRepository appearanceRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.appearanceRepository = appearanceRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        //Appearance requests


        Appearance a1 = new Appearance();
        a1.setId(1);
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
        a1.setStatus(AppearanceStatus.COMPLETED);
        a1.setMileage(50.0);

        Appearance a2 = new Appearance();
        a2.setId(2);
        a2.setC_firstName("John");
        a2.setC_lastName("Doe");
        a2.setC_email("johndoe@gmail.com");
        a2.setC_phone("(123) 456-7890");
        a2.setEventTitle("Charity Auction");
        LocalDateTime mockStartTime2 = LocalDateTime.of(2023, 11, 15, 18, 0, 0);
        a2.setBeginning_time(mockStartTime2);
        LocalDateTime mockEndTime2 = LocalDateTime.of(2023, 11, 15, 21, 0, 0);
        a2.setEnding_time(mockEndTime2);
        a2.setEventType(EventType.NONPROFIT);
        a2.setOrganizationName("Charity for Children");
        a2.setEventAddress("123 Main St, Anytown USA");
        a2.setOnCampus(Boolean.FALSE);
        a2.setSpecialInstructions("Please bring a tuxedo");
        a2.setExpenseBen("All expenses paid");
        a2.setOutsideOrganizations("None");
        a2.setEventDescription("Annual charity auction to raise money for children in need");
        a2.setStatus(AppearanceStatus.ASSIGNED);
        a2.setMileage(100.0);

        Appearance a3 = new Appearance();
        a3.setId(3);
        a3.setC_firstName("Jane");
        a3.setC_lastName("Smith");
        a3.setC_email("jane.smith@company.com");
        a3.setC_phone("(555) 555-5555");
        a3.setEventTitle("Company Holiday Party");
        LocalDateTime mockStartTime3 = LocalDateTime.of(2023, 12, 15, 19, 0, 0);
        a3.setBeginning_time(mockStartTime3);
        LocalDateTime mockEndTime3 = LocalDateTime.of(2023, 12, 16, 1, 0, 0);
        a3.setEnding_time(mockEndTime3);
        a3.setEventType(EventType.PRIVATE);
        a3.setOrganizationName("ABC Company");
        a3.setEventAddress("1234 Main St, Anytown USA");
        a3.setOnCampus(Boolean.FALSE);
        a3.setSpecialInstructions("Casual dress code");
        a3.setExpenseBen("Meal and drinks provided");
        a3.setOutsideOrganizations("None");
        a3.setEventDescription("Annual company holiday party for employees and their guests");
        a3.setStatus(AppearanceStatus.PENDING);
        a3.setMileage(20.0);


        Appearance a4 = new Appearance();
        a4.setId(4);
        a4.setC_firstName("David");
        a4.setC_lastName("Lee");
        a4.setC_email("david.lee@university.edu");
        a4.setC_phone("(555) 123-4567");
        a4.setEventTitle("Guest Speaker");
        LocalDateTime mockStartTime4 = LocalDateTime.of(2023, 10, 20, 15, 0, 0);
        a4.setBeginning_time(mockStartTime4);
        LocalDateTime mockEndTime4 = LocalDateTime.of(2023, 10, 20, 17, 0, 0);
        a4.setEnding_time(mockEndTime4);
        a4.setEventType(EventType.TCU);
        a4.setOrganizationName("TCU School of Business");
        a4.setEventAddress("2900 Lubbock Ave, Fort Worth, TX 76109");
        a4.setOnCampus(Boolean.TRUE);
        a4.setSpecialInstructions("Please arrive few minutes early to set up your presentation");
        a4.setExpenseBen("Honorarium provided");
        a4.setOutsideOrganizations("None");
        a4.setEventDescription("Guest speaker for TCU School of Business");
        a4.setStatus(AppearanceStatus.COMPLETED);
        a4.setMileage(5.0);

        Appearance a5 = new Appearance();
        a5.setId(5);
        a5.setC_firstName("Maria");
        a5.setC_lastName("Garcia");
        a5.setC_email("maria.garcia@communitycenter.org");
        a5.setC_phone("(555) 789-1234");
        a5.setEventTitle("Community Talent Show");
        LocalDateTime mockStartTime5 = LocalDateTime.of(2023, 9, 1, 18, 0, 0);
        a5.setBeginning_time(mockStartTime5);
        LocalDateTime mockEndTime5 = LocalDateTime.of(2023, 9, 1, 21, 0, 0);
        a5.setEnding_time(mockEndTime5);
        a5.setEventType(EventType.NONPROFIT);
        a5.setOrganizationName("Community Center");
        a5.setEventAddress("456 Oak St, Anytown USA");
        a5.setOnCampus(Boolean.FALSE);
        a5.setSpecialInstructions("Please bring your own chair");
        a5.setExpenseBen("None");
        a5.setOutsideOrganizations("None");
        a5.setEventDescription("Annual community talent show featuring local performers");
        a5.setStatus(AppearanceStatus.PENDING);
        a5.setMileage(30.0);

        Appearance a6 = new Appearance();
        a6.setId(6);
        a6.setC_firstName("David");
        a6.setC_lastName("Johnson");
        a6.setC_email("david.johnson@example.com");
        a6.setC_phone("(555) 555-1212");
        a6.setEventTitle("Corporate Training Session");
        LocalDateTime mockStartTime6 = LocalDateTime.of(2023, 5, 15, 9, 0, 0);
        a6.setBeginning_time(mockStartTime6);
        LocalDateTime mockEndTime6 = LocalDateTime.of(2023, 5, 15, 16, 0, 0);
        a6.setEnding_time(mockEndTime6);
        a6.setEventType(EventType.TCU);
        a6.setOrganizationName("ABC Corp");
        a6.setEventAddress("123 Main St, Anytown USA");
        a6.setOnCampus(Boolean.FALSE);
        a6.setSpecialInstructions("Please bring a laptop and a notepad");
        a6.setExpenseBen("Travel and lodging expenses covered");
        a6.setOutsideOrganizations("None");
        a6.setEventDescription("All-day training session on effective communication");
        a6.setStatus(AppearanceStatus.ASSIGNED);
        a6.setMileage(100.0);

        Appearance a7 = new Appearance();
        a7.setId(7);
        a7.setC_firstName("Jessica");
        a7.setC_lastName("Smith");
        a7.setC_email("jessica.smith@example.com");
        a7.setC_phone("(555) 555-1212");
        a7.setEventTitle("Charity Fundraiser");
        LocalDateTime mockStartTime7 = LocalDateTime.of(2023, 6, 10, 18, 0, 0);
        a7.setBeginning_time(mockStartTime7);
        LocalDateTime mockEndTime7 = LocalDateTime.of(2023, 6, 10, 22, 0, 0);
        a7.setEnding_time(mockEndTime7);
        a7.setEventType(EventType.NONPROFIT);
        a7.setOrganizationName("Hope Center");
        a7.setEventAddress("456 Elm St, Anytown USA");
        a7.setOnCampus(Boolean.FALSE);
        a7.setSpecialInstructions("Black tie attire");
        a7.setExpenseBen("Honorarium provided");
        a7.setOutsideOrganizations("None");
        a7.setEventDescription("Annual charity fundraiser to support local community programs");
        a7.setStatus(AppearanceStatus.PENDING);
        a7.setMileage(20.0);

        Appearance a8 = new Appearance();
        a8.setId(8);
        a8.setC_firstName("Karen");
        a8.setC_lastName("Williams");
        a8.setC_email("karen.williams@example.com");
        a8.setC_phone("(555) 555-1212");
        a8.setEventTitle("College Graduation Ceremony");
        LocalDateTime mockStartTime8 = LocalDateTime.of(2023, 5, 20, 10, 0, 0);
        a8.setBeginning_time(mockStartTime8);
        LocalDateTime mockEndTime8 = LocalDateTime.of(2023, 5, 20, 12, 0, 0);
        a8.setEnding_time(mockEndTime8);
        a8.setEventType(EventType.TCU);
        a8.setOrganizationName("XYZ College");
        a8.setEventAddress("789 Oak St, Anytown USA");
        a8.setOnCampus(Boolean.TRUE);
        a8.setSpecialInstructions("Please arrive by 9:30 AM for photos");
        a8.setExpenseBen(null);
        a8.setOutsideOrganizations(null);
        a8.setEventDescription("Annual college graduation ceremony for the School of Business");
        a8.setStatus(AppearanceStatus.COMPLETED);
        a8.setMileage(0.0);

        Appearance a9 = new Appearance();
        a9.setId(9);
        a9.setC_firstName("Alex");
        a9.setC_lastName("Lee");
        a9.setC_email("alex.lee@example.com");
        a9.setC_phone("(555) 555-1212");
        a9.setEventTitle("Annual Science Fair");
        LocalDateTime mockStartTime9 = LocalDateTime.of(2023, 4, 15, 8, 0, 0);
        a9.setBeginning_time(mockStartTime9);
        LocalDateTime mockEndTime9 = LocalDateTime.of(2023, 4, 15, 15, 0, 0);
        a9.setEnding_time(mockEndTime9);
        a9.setEventType(EventType.NONPROFIT);
        a9.setOrganizationName("Central High School");
        a9.setEventAddress("321 Maple St, Anytown USA");
        a9.setOnCampus(Boolean.TRUE);
        a9.setSpecialInstructions("Please wear a lab coat and safety goggles");
        a9.setExpenseBen("Travel expenses covered");
        a9.setOutsideOrganizations("None");
        a9.setEventDescription("Annual science fair showcasing innovative projects by students");
        a9.setStatus(AppearanceStatus.ASSIGNED);
        a9.setMileage(10.0);

        Appearance a10 = new Appearance();
        a10.setId(10);
        a10.setC_firstName("Michael");
        a10.setC_lastName("Davis");
        a10.setC_email("michael.davis@example.com");
        a10.setC_phone("(555) 555-1212");
        a10.setEventTitle("Book Signing Event");
        LocalDateTime mockStartTime10 = LocalDateTime.of(2023, 7, 1, 12, 0, 0);
        a10.setBeginning_time(mockStartTime10);
        LocalDateTime mockEndTime10 = LocalDateTime.of(2023, 7, 1, 14, 0, 0);
        a10.setEnding_time(mockEndTime10);
        a10.setEventType(EventType.TCU);
        a10.setOrganizationName("Local Bookstore");
        a10.setEventAddress("789 Oak St, Anytown USA");
        a10.setOnCampus(Boolean.FALSE);
        a10.setSpecialInstructions("Please bring a copy of the book for signing");
        a10.setExpenseBen("Honorarium provided");
        a10.setOutsideOrganizations("None");
        a10.setEventDescription("Book signing event for the new novel by Michael Davis");
        a10.setStatus(AppearanceStatus.PENDING);
        a10.setMileage(5.0);

        //SFS


        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setSFS_id(1);
        s1.setFirstName("Hiep");
        s1.setLastName("Nguyen");
        s1.setAddress("3145 Cockrell Avenue, DFW, TX 76109");
        s1.setPhone("(682) 365-5307");
        s1.setEmail("hiep.n.nguyen@tcu.edu");
        s1.setInternational(Boolean.TRUE);
        s1.addAppearance(a1);
        s1.addAppearance(a4);
        s1.addAppearance(a6);
        s1.addAppearance(a9);

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setSFS_id(2);
        s2.setFirstName("Ryan");
        s2.setLastName("Usell");
        s2.setPhone("(123) 456-789");
        s2.setEmail("dummy_email@gmail.com");
        s2.setInternational(Boolean.FALSE);
        s2.addAppearance(a2);

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
        s6.setFirstName("Cameron");
        s6.setLastName("Lee");
        s6.setPhone("(345) 678-9012");
        s6.setEmail("dummy_email3@gmail.com");
        s6.setInternational(Boolean.FALSE);

        SuperFrogStudent s7 = new SuperFrogStudent();
        s7.setSFS_id(7);
        s7.setFirstName("Daniela");
        s7.setLastName("Garcia");
        s7.setPhone("(456) 789-0123");
        s7.setEmail("dummy_email4@gmail.com");
        s7.setInternational(Boolean.TRUE);

        SuperFrogStudent s8 = new SuperFrogStudent();
        s8.setSFS_id(8);
        s8.setFirstName("Ethan");
        s8.setLastName("Lopez");
        s8.setPhone("(567) 890-1234");
        s8.setEmail("dummy_email5@gmail.com");
        s8.setInternational(Boolean.FALSE);

        SuperFrogStudent s9 = new SuperFrogStudent();
        s9.setSFS_id(9);
        s9.setFirstName("Fiona");
        s9.setLastName("Nguyen");
        s9.setPhone("(678) 901-2345");
        s9.setEmail("dummy_email6@gmail.com");
        s9.setInternational(Boolean.TRUE);

        SuperFrogStudent s10 = new SuperFrogStudent();
        s10.setSFS_id(10);
        s10.setFirstName("Gabriel");
        s10.setLastName("Kim");
        s10.setPhone("(789) 012-3456");
        s10.setEmail("dummy_email7@gmail.com");
        s10.setInternational(Boolean.FALSE);

        SuperFrogStudent s11 = new SuperFrogStudent();
        s11.setSFS_id(11);
        s11.setFirstName("Hailey");
        s11.setLastName("Choi");
        s11.setPhone("(890) 123-4567");
        s11.setEmail("dummy_email8@gmail.com");
        s11.setInternational(Boolean.TRUE);

        SuperFrogStudent s12 = new SuperFrogStudent();
        s12.setSFS_id(12);
        s12.setFirstName("Isaac");
        s12.setLastName("Park");
        s12.setPhone("(901) 234-5678");
        s12.setEmail("dummy_email9@gmail.com");
        s12.setInternational(Boolean.FALSE);

        SuperFrogStudent s13 = new SuperFrogStudent();
        s13.setSFS_id(13);
        s13.setFirstName("Jasmine");
        s13.setLastName("Wang");
        s13.setPhone("(012) 345-6789");
        s13.setEmail("dummy_email10@gmail.com");
        s13.setInternational(Boolean.TRUE);

        SuperFrogStudent s14 = new SuperFrogStudent();
        s14.setSFS_id(14);
        s14.setFirstName("Kevin");
        s14.setLastName("Zhang");
        s14.setPhone("(123) 456-7890");
        s14.setEmail("dummy_email11@gmail.com");
        s14.setInternational(Boolean.FALSE);


        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        studentRepository.save(s4);
        studentRepository.save(s5);
        studentRepository.save(s6);
        studentRepository.save(s7);
        studentRepository.save(s8);
        studentRepository.save(s9);
        studentRepository.save(s10);
        studentRepository.save(s11);
        studentRepository.save(s12);
        studentRepository.save(s13);
        studentRepository.save(s14);

        appearanceRepository.save(a1);
        appearanceRepository.save(a2);
        appearanceRepository.save(a3);
        appearanceRepository.save(a4);
        appearanceRepository.save(a5);
        appearanceRepository.save(a6);
        appearanceRepository.save(a7);
        appearanceRepository.save(a8);
        appearanceRepository.save(a9);
        appearanceRepository.save(a10);





        //admin = spirit director
        //user = customers
        //student = superfrogstudents

        SchedulerUser u1 = new SchedulerUser();
        u1.setId(1);
        u1.setUsername("john");
        u1.setPassword("123456");
        u1.setEnabled(true);
        u1.setRoles("admin user");

        SchedulerUser u2 = new SchedulerUser();
        u2.setId(2);
        u2.setUsername("eric");
        u2.setPassword("654321");
        u2.setEnabled(true);
        u2.setRoles("user");


        SchedulerUser u3 = new SchedulerUser();
        u3.setId(3);
        u3.setUsername("tom");
        u3.setPassword("qwerty");
        u3.setEnabled(false);
        u3.setRoles("user");

        this.userService.save(u1);
        this.userService.save(u2);
        this.userService.save(u3);







    }

}
