package com.gintaras.luminor.payments.service;


import com.gintaras.luminor.payments.exception.PaymentValidationException;
import com.gintaras.luminor.payments.model.AbstractPaymentType;
import com.gintaras.luminor.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implements business logic for payments.
 *
 * @created 21/08/2020 - 4:08 PM
 * @author gintaras
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<AbstractPaymentType> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public <T> Optional<AbstractPaymentType> findById(Long id) {
        return Optional.of(paymentRepository.findById(id)).orElse(null);
    }

    @Override
    public void validatePaymentForCancellation(AbstractPaymentType payment) {
        LocalDateTime creationDateTime = payment.getCreationDate();
        LocalDateTime todayDateTime = LocalDateTime.now();

        boolean isOfToday = todayDateTime.toLocalDate().equals(creationDateTime.toLocalDate());

        if(!isOfToday) {
            throw new PaymentValidationException(payment.getId());
        }
    }

    @Override
    public AbstractPaymentType cancelPayment(AbstractPaymentType payment) {
        payment.setCancelled(Boolean.TRUE);
        paymentRepository.save(payment);
        return payment;
    }
}
