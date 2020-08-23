package com.gintaras.luminor.payments.exception;

/**
 * Validation exception for payments that are not valid for cancellation.
 *
 * @created 23/08/2020 - 1:56 PM
 * @author gintaras
 */

public class PaymentValidationException extends RuntimeException {

    public PaymentValidationException(Long id) {
        super("The payment with id " + id + " could not be cancelled");
    }
}
