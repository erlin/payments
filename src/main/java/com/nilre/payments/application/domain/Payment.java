package com.nilre.payments.application.domain;

import java.time.LocalDate;

public class Payment {

    private Integer payment_number;

    private Double amount;

    private LocalDate payment_date;

    public Payment(Integer payment_number, Double amount, LocalDate payment_date) {
        this.payment_number = payment_number;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public Integer getPayment_number() {
        return payment_number;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (!getPayment_number().equals(payment.getPayment_number())) return false;
        if (!getAmount().equals(payment.getAmount())) return false;
        return getPayment_date().equals(payment.getPayment_date());
    }

    @Override
    public int hashCode() {
        int result = getPayment_number().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getPayment_date().hashCode();
        return result;
    }
}
