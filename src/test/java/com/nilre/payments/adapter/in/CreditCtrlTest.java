package com.nilre.payments.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nilre.payments.application.domain.Credit;
import com.nilre.payments.application.domain.Payment;
import com.nilre.payments.application.exceptions.NotFoundException;
import com.nilre.payments.application.port.out.CreditPersistenceUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreditCtrlTest {

    protected final WebApplicationContext webApplicationContext;

    final ObjectMapper mapper;

    MockMvc mvc;

    @MockBean
    CreditPersistenceUseCase creditPersistenceUseCase;

    @Captor
    ArgumentCaptor<Credit> creditCaptor;

    public CreditCtrlTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCreditNotFound() throws Exception {
        Mockito.when(creditPersistenceUseCase.getCreditById(1))
                .thenThrow(new NotFoundException("There is no credit with that Id"));

        mvc.perform(MockMvcRequestBuilders.get("/credit/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testCreditPaymentsCreation() throws Exception {
        Credit credit = new Credit(null, 100.0, 10, 10.0, null);
        Mockito.when(creditPersistenceUseCase.persistCredit(creditCaptor.capture())).thenReturn(credit);

        mvc.perform(MockMvcRequestBuilders.post("/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(credit)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Credit newCredit = creditCaptor.getValue();

        Assertions.assertEquals(10, newCredit.getPayments().size());
    }

    @Test
    void testCreditCreationAndRetrieve() throws Exception {
        Credit credit = new Credit(1, 100.0, 10, 10.0, List.of(new Payment(1, 100.0, LocalDate.now())));
        Mockito.when(creditPersistenceUseCase.getCreditById(1)).thenReturn(credit);

        String newCreditAsString = mvc.perform(MockMvcRequestBuilders.get("/credit/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Credit creditFromGet = mapper.readValue(newCreditAsString, Credit.class);

        Assertions.assertTrue(credit.equals(creditFromGet));
    }

}