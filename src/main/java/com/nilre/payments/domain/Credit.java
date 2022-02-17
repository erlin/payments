package com.nilre.payments.domain;

import org.springframework.data.annotation.Id;

public class Credit {

    @Id
    private Integer id;

    private Double amount;

    private Integer terms;

    private Double rate;

    public Credit(Integer id, Double amount, Integer terms, Double rate) {
        this.id = id;
        this.amount = amount;
        this.terms = terms;
        this.rate = rate;
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
}
