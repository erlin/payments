package com.nilre.payments.application.port.in;

import com.nilre.payments.application.dto.CreditDto;
import com.nilre.payments.domain.Credit;

public interface CreditUseCase {
    CreditDto retrieveCreditById(Integer creditId);
    CreditDto createCredit(Credit credit);
}
