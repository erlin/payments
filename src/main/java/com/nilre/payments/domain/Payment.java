package com.nilre.payments.domain;

import java.util.Date;

public class Payment {

    private Integer payment_number;

    private Double amount;

    private Date payment_date;

    public Payment(Integer payment_number, Double amount, Date payment_date) {
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

    public Date getPayment_date() {
        return payment_date;
    }
}
