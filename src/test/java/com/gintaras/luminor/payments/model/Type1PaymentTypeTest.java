package com.gintaras.luminor.payments.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit test for {@link Type1PaymentType}
 *
 * @created 20/08/2020 - 4:06 PM
 * @author gintaras
 */

class Type1PaymentTypeTest {

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));
    private static final Money FEE_AMOUNT_0_EUR = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
    private static final Money FEE_AMOUNT_0_0_5_EUR = new Money(BigDecimal.valueOf(0.05)
            .setScale(2, RoundingMode.HALF_UP), Currency.getInstance("EUR"));

    private static final String IBAN = "IBAN_Code";
    private static final String DETAILS_TEXT = "Text";


    @Test
    void testToString() {
        Type1PaymentType paymentType = new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);

        String paymentTypeString = paymentType.toString();
        assertThat(paymentTypeString).contains("amount=1.00 USD");
        assertThat(paymentTypeString).contains("debtorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("creditorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("details=Text");
    }

    @Test
    void determineCancellationFeeShouldReturnZeroFee() {
        Type1PaymentType paymentType = new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now());

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_EUR);
    }

    @Test
    void determineCancellationFeeShouldReturnZeroDotZeroFiveFee() {
        Type1PaymentType paymentType = new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now().minusHours(1));

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_0_5_EUR);

    }
}