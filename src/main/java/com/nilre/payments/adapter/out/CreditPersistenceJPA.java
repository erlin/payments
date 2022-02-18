package com.nilre.payments.adapter.out;

import com.nilre.payments.adapter.out.jpa.CreditJPA;
import com.nilre.payments.adapter.out.jpa.CreditJPARepository;
import com.nilre.payments.application.domain.Credit;
import com.nilre.payments.application.exceptions.NotFoundException;
import com.nilre.payments.application.port.out.CreditPersistenceUseCase;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class CreditPersistenceJPA implements CreditPersistenceUseCase {

    private CreditJPARepository creditJPARepository;

    public CreditPersistenceJPA(CreditJPARepository creditJPARepository) {
        this.creditJPARepository = creditJPARepository;
    }

    @Override
    @Transactional
    public Credit getCreditById(Integer creditId) {
        Optional<CreditJPA> optCreditJdbc = creditJPARepository.findById(creditId);
        if (optCreditJdbc.isPresent()) {
            CreditJPA creditJPA = optCreditJdbc.get();
            return creditJPA.toCredit();
        }
        throw new NotFoundException("There is no credit with that Id");
    }

    @Override
    public Credit persistCredit(Credit credit) {
        CreditJPA creditJPA = creditJPARepository.save(CreditJPA.fromCredit(credit));
        return creditJPA.toCredit();
    }
}
