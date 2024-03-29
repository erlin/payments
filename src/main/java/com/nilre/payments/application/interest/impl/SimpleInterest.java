package com.nilre.payments.application.interest.impl;

import com.nilre.payments.application.interest.CreditInterest;
import com.nilre.payments.application.domain.Credit;
import com.nilre.payments.application.domain.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleInterest implements CreditInterest {

    @Override
    public List<Payment> calculatePayments(Credit credit) {
        Double amount = credit.getAmount()*( 1 + (credit.getRate() * credit.getTerms()) / 100) / credit.getTerms();
        LocalDate today = LocalDate.now();
        List<Payment> payments = new LinkedList<>();

        for (int i = 1; i <= credit.getTerms(); i++) {
            LocalDate date = LocalDate.from(today.plus(i, ChronoUnit.WEEKS));
            payments.add(new Payment(i, amount, date));
        }

        return payments;
    }
}
