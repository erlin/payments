package com.nilre.payments.application.domain;

import java.util.ArrayList;
import java.util.List;

public class Credit {

    private Integer id;

    private Double amount;

    private Integer terms;

    private Double rate;

    private List<Payment> payments = new ArrayList<>();

    public Credit() {}

    public Credit(Integer id, Double amount, Integer terms, Double rate, List<Payment> payments) {
        this.id = id;
        this.amount = amount;
        this.terms = terms;
        this.rate = rate;
        this.payments = (payments != null) ? payments : new ArrayList<>();
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

    public void addPayments(List<Payment> payments) {
        this.payments.addAll(payments);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;

        Credit credit = (Credit) o;

        if (getId() != null ? !getId().equals(credit.getId()) : credit.getId() != null) return false;
        if (!getAmount().equals(credit.getAmount())) return false;
        if (!getTerms().equals(credit.getTerms())) return false;
        if (!getRate().equals(credit.getRate())) return false;
        return getPayments().equals(credit.getPayments());
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
