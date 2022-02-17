package com.nilre.payments.application.interest;

import com.nilre.payments.domain.Credit;
import com.nilre.payments.domain.Payment;

import java.util.List;

public interface CreditInterest {
    List<Payment> calculatePayments(Credit credit);
}
