package com.gintaras.luminor.payments.exception;

/*
 * @created 22/08/2020 - 11:44 AM
 * @author gintaras
 */

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(Long id) {
        super("Payment with id " + id + " not found");
    }
}
