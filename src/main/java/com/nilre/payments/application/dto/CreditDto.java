package com.nilre.payments.application.dto;

import com.nilre.payments.domain.Credit;
import com.nilre.payments.domain.Payment;

import java.util.List;

public class CreditDto {

    public CreditDto(Credit credit, List<Payment> payments) {
        this.credit = credit;
        this.payments = payments;
    }

    private Credit credit;

    private List<Payment> payments;

    public Credit getCredit() {
        return credit;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
