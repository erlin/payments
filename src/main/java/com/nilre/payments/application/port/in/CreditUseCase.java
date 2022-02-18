package com.nilre.payments.application.port.in;

import com.nilre.payments.application.domain.Credit;

public interface CreditUseCase {
    Credit retrieveCreditById(Integer creditId);
    Credit createCredit(Credit credit);
}
