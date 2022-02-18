package com.nilre.payments.adapter.out;

import com.nilre.payments.application.domain.Credit;
import com.nilre.payments.application.domain.Payment;
import com.nilre.payments.application.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CreditPersistenceJPATest {

    @Autowired
    CreditPersistenceJPA creditPersistenceJPA;

    @Test
    void getCreditByInvalidId() {
        Assertions.assertThrows(NotFoundException.class, ()->{
           creditPersistenceJPA.getCreditById(100);
        });
    }

    @Test
    void persistCredit() {
        List<Payment> payments = List.of(new Payment(1, 100.0, LocalDate.now()));
        Credit credit = new Credit(null, 100.0, 1, 10.0, payments);

        Credit newCredit = creditPersistenceJPA.persistCredit(credit);
        Credit newCreditById = creditPersistenceJPA.getCreditById(newCredit.getId());

        assertNotNull(newCredit);
        assertTrue(newCredit.equals(newCreditById));
    }
}