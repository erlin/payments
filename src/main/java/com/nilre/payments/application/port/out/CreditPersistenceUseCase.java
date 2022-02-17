package com.nilre.payments.application.port.out;

import com.nilre.payments.application.dto.CreditDto;
import com.nilre.payments.domain.Credit;
import com.nilre.payments.domain.Payment;

import java.util.List;

public interface CreditPersistenceUseCase {
    CreditDto getCreditById(Integer creditId);
    CreditDto persistCredit(Credit credit, List<Payment> payments);
}
