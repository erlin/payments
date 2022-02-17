package com.nilre.payments.adapter.out;

import com.nilre.payments.application.dto.CreditDto;
import com.nilre.payments.application.port.out.CreditPersistenceUseCase;
import com.nilre.payments.domain.Credit;
import com.nilre.payments.domain.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditPersistenceJdbc implements CreditPersistenceUseCase {

    @Override
    public CreditDto getCreditById(Integer creditId) {
        return null;
    }

    @Override
    public CreditDto persistCredit(Credit credit, List<Payment> payments) {
        return new CreditDto(credit, payments);
    }
}
