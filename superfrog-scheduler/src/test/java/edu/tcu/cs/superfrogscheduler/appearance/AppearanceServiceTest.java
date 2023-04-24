package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.appearance.Appearance;
import edu.tcu.cs.superfrogscheduler.appearance.AppearanceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AppearanceServiceTest {

    @Mock
    AppearanceRepository appearanceRepository;

    @InjectMocks
    AppearanceService appearanceService;

    List<Appearance> appearances;

    @BeforeEach
    void setUp() {
        Appearance a1 = new Appearance();
        a1.setE_id("1");
        a1.setEventTitle("Boschini Birthday");
        LocalDateTime mockStartTime = null;
        a1.setStartTime(null);
        LocalDateTime mockEndTime = null;
        a1.setEndTime(null);
        a1.setEventType("Private");
        a1.setOrganizationName("Boschini Million Dollar Group");
        a1.setEventAddress("2800 S University Dr, Fort Worth, TX 76129");
        a1.setOnCampus(Boolean.FALSE);
        a1.setSpecialInstructions(null);
        a1.setExpenseBen(null);
        a1.setOutsideOrganizations(null);
        a1.setEventDescription("The annual millionare party organized by Boschini");
        a1.setStatus(null);

        this.appearances = new ArrayList<>();
        this.appearances.add(a1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAppearanceByIdSuccess() {

        LocalDateTime mockStartTime = LocalDateTime.of(2023, 12, 23, 11, 0, 0);
        LocalDateTime mockEndTime = LocalDateTime.of(2023, 12, 23, 15, 0, 0);

        Appearance a1 = new Appearance();
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
        given(appearanceRepository.findById("1")).willReturn(Optional.of(a1));

        //When - call the method to be tested - act on target behavior

        Appearance returnedAppearance = appearanceService.getAppearanceById("1");


        //Then - compare the result from when and given -> if the insertion is True then test passed
        assertThat(returnedAppearance.getE_id()).isEqualTo(a1.getE_id());
        assertThat(returnedAppearance.getEventTitle()).isEqualTo(a1.getEventTitle());
        assertThat(returnedAppearance.getStartTime()).isEqualTo(a1.getStartTime());
        assertThat(returnedAppearance.getEndTime()).isEqualTo(a1.getEndTime());
        assertThat(returnedAppearance.getEventType()).isEqualTo(a1.getEventType());
        assertThat(returnedAppearance.getOrganizationName()).isEqualTo(a1.getOrganizationName());
        assertThat(returnedAppearance.getEventAddress()).isEqualTo(a1.getEventAddress());
        assertThat(returnedAppearance.getOnCampus()).isEqualTo(a1.getOnCampus());
        assertThat(returnedAppearance.getSpecialInstructions()).isEqualTo(a1.getSpecialInstructions());
        assertThat(returnedAppearance.getExpenseBen()).isEqualTo(a1.getExpenseBen());
        assertThat(returnedAppearance.getOutsideOrganizations()).isEqualTo(a1.getOutsideOrganizations());
        assertThat(returnedAppearance.getEventDescription()).isEqualTo(a1.getEventDescription());
        assertThat(returnedAppearance.getStatus()).isEqualTo(a1.getStatus());


        verify(appearanceRepository, times(1)).findById("1");

    }

    @Test
    void FindAllSuccess() {
        //Given
        given(appearanceRepository.findAll()).willReturn(this.appearances);

        //When
        List<Appearance> actualAppearence = appearanceService.findAll();

        //Then
        assertThat(actualAppearence.size()).isEqualTo(this.appearances.size());
        verify(appearanceRepository, times(1)).findAll();

    }
}