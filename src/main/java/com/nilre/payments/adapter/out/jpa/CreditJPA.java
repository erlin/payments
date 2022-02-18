package com.nilre.payments.adapter.out.jpa;

import com.nilre.payments.application.domain.Credit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "credit")
public class CreditJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    private Integer terms;

    private Double rate;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "credit", orphanRemoval = true)
    private List<PaymentJPA> payments = new ArrayList<>();

    public void addPayment(PaymentJPA paymentJPA) {
        this.payments.add(paymentJPA);
        paymentJPA.setCredit(this);
    }

    public CreditJPA() {}

    public CreditJPA(Integer id, Double amount, Integer terms, Double rate, List<PaymentJPA> payments) {
        this.id = id;
        this.amount = amount;
        this.terms = terms;
        this.rate = rate;
        payments.stream().forEach(this::addPayment);
    }

    public static CreditJPA fromCredit(Credit credit) {
        List<PaymentJPA> paymentsJPA = new LinkedList<>();
        paymentsJPA.addAll(credit.getPayments().stream().map(PaymentJPA::fromPayment).collect(Collectors.toList()));

        return new CreditJPA(credit.getId(), credit.getAmount(), credit.getTerms(), credit.getRate(), paymentsJPA);
    }

    public Credit toCredit() {
        return new Credit(getId(), getAmount(), getTerms(), getRate(),
                getPayments().stream().map(PaymentJPA::toPayment).collect(Collectors.toList()));
    }

    public Integer getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getTerms() {
        return terms;
    }

    public Double getRate() {
        return rate;
    }

    public List<PaymentJPA> getPayments() {
        return payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditJPA)) return false;

        CreditJPA creditJPA = (CreditJPA) o;

        if (getId() != null ? !getId().equals(creditJPA.getId()) : creditJPA.getId() != null) return false;
        if (!getAmount().equals(creditJPA.getAmount())) return false;
        if (!getTerms().equals(creditJPA.getTerms())) return false;
        if (!getRate().equals(creditJPA.getRate())) return false;
        return getPayments().equals(creditJPA.getPayments());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getTerms().hashCode();
        result = 31 * result + getRate().hashCode();
        result = 31 * result + getPayments().hashCode();
        return result;
    }
}
