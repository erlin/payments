package com.nilre.payments.adapter.in;

import com.nilre.payments.application.port.in.CreditUseCase;
import com.nilre.payments.application.domain.Credit;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditCtrl {

    private CreditUseCase creditUseCase;

    public CreditCtrl(CreditUseCase creditUseCase) {
        this.creditUseCase = creditUseCase;
    }

    @GetMapping("/{creditId}")
    public Credit getCredit(@PathVariable("creditId") Integer creditId) {
        return creditUseCase.retrieveCreditById(creditId);
    }

    @PostMapping
    public Credit createCredit(@RequestBody Credit credit) {
        return creditUseCase.createCredit(credit);
    }
}
