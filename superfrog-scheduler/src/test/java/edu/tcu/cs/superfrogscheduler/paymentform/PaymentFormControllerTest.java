package edu.tcu.cs.superfrogscheduler.paymentform;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.paymentform.dto.RequestIds;
import edu.tcu.cs.superfrogscheduler.paymentform.util.Period;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for Payment APIs")
@Tag("integration")
public class PaymentFormControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void should_generate_payments_form_for_SuperFrog_students() throws Exception {
        // Given
        List<String> selectedAppearanceRequestIds = List.of("1"); // Assume the Spirit Director has selected 7 completed requests for April.

        Period paymentPeriod = new Period(LocalDate.of(2023, 12, 21), LocalDate.of(2023, 12, 25));

        RequestIds requestIds = new RequestIds(selectedAppearanceRequestIds, paymentPeriod);

        String json = this.objectMapper.writeValueAsString(requestIds);

        // When and then
        this.mockMvc.perform(post("/payment-forms").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Payment forms are generated successfully."))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

}
