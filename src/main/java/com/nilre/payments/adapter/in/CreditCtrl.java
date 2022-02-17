package com.nilre.payments.adapter.in;

import com.nilre.payments.application.dto.CreditDto;
import com.nilre.payments.application.port.in.CreditUseCase;
import com.nilre.payments.domain.Credit;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditCtrl {

    private CreditUseCase creditUseCase;

    public CreditCtrl(CreditUseCase creditUseCase) {
        this.creditUseCase = creditUseCase;
    }

    @GetMapping("/{creditId}")
    public CreditDto getCredit(Integer creditId) {
        return creditUseCase.retrieveCreditById(creditId);
    }

    @PostMapping
    public CreditDto createCredit(@RequestBody Credit credit) {
        return creditUseCase.createCredit(credit);
    }
}
