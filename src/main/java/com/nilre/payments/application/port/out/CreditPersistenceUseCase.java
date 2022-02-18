package com.nilre.payments.application.port.out;

import com.nilre.payments.application.domain.Credit;

public interface CreditPersistenceUseCase {
    Credit getCreditById(Integer creditId);
    Credit persistCredit(Credit credit);
}
