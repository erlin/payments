package com.nilre.payments.adapter.out.jpa;

import com.nilre.payments.application.domain.Payment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "payment")
public class PaymentJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer payment_number;

    private Double amount;

    private LocalDate payment_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private CreditJPA credit;

    public PaymentJPA() {}

    public PaymentJPA(Integer id, Integer payment_number, Double amount, LocalDate payment_date) {
        this.id = id;
        this.payment_number = payment_number;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public static PaymentJPA fromPayment(Payment payment) {
        return new PaymentJPA(null, payment.getPayment_number(), payment.getAmount(), payment.getPayment_date());
    }

    public Payment toPayment() {
        return new Payment(getPayment_number(), getAmount(), getPayment_date());
    }

    public Integer getId() {
        return id;
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

    public CreditJPA getCredit() {
        return credit;
    }

    public void setCredit(CreditJPA credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentJPA)) return false;

        PaymentJPA that = (PaymentJPA) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPayment_number().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getPayment_date().hashCode();
        return result;
    }
}
