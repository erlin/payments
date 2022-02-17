package com.nilre.payments.application.service;

import com.nilre.payments.application.dto.CreditDto;
import com.nilre.payments.application.interest.CreditInterest;
import com.nilre.payments.application.port.in.CreditUseCase;
import com.nilre.payments.application.port.out.CreditPersistenceUseCase;
import com.nilre.payments.domain.Credit;
import com.nilre.payments.domain.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService implements CreditUseCase{

    private CreditPersistenceUseCase creditPersistenceUseCase;

    private CreditInterest creditInterest;

    public CreditService(CreditPersistenceUseCase creditPersistenceUseCase, CreditInterest creditInterest) {
        this.creditPersistenceUseCase = creditPersistenceUseCase;
        this.creditInterest = creditInterest;
    }

    @Override
    public CreditDto retrieveCreditById(Integer creditId) {
        return creditPersistenceUseCase.getCreditById(creditId);
    }

    @Override
    public CreditDto createCredit(Credit credit) {
        List<Payment> payments = creditInterest.calculatePayments(credit);
        creditPersistenceUseCase.persistCredit(credit, payments);
        return new CreditDto(credit, payments);
    }
}
