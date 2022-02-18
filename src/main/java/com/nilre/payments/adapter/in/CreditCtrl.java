package com.nilre.payments.adapter.in;

import com.nilre.payments.application.domain.Payment;
import com.nilre.payments.application.port.in.CreditUseCase;
import com.nilre.payments.application.domain.Credit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditCtrl {

    private CreditUseCase creditUseCase;

    public CreditCtrl(CreditUseCase creditUseCase) {
        this.creditUseCase = creditUseCase;
    }

    @GetMapping("/{creditId}")
    public List<Payment> getCredit(@PathVariable("creditId") Integer creditId) {
        return creditUseCase.retrieveCreditById(creditId).getPayments();
    }

    @PostMapping
    public List<Payment> createCredit(@RequestBody Credit credit) {
        return creditUseCase.createCredit(credit).getPayments();
    }
}
