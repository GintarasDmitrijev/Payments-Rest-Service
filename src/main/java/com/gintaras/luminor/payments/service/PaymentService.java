package com.gintaras.luminor.payments.service;

import com.gintaras.luminor.payments.model.AbstractPaymentType;

import java.util.List;
import java.util.Optional;

/**
 * Business logic for payments.
 *
 * @created 21/08/2020 - 4:07 PM
 * @author gintaras
 */

public interface PaymentService {

    List<AbstractPaymentType> findAll();

    <T> Optional<AbstractPaymentType> findById(Long id);

    void validatePaymentForCancellation(AbstractPaymentType payment);

    AbstractPaymentType cancelPayment(AbstractPaymentType payment);
}

