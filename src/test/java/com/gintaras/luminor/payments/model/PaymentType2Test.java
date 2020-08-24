package com.gintaras.luminor.payments.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit test for {@link PaymentType2}
 *
 * @created 20/08/2020 - 6:53 PM
 * @author gintaras
 */
class PaymentType2Test {

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));
    private static final Money FEE_AMOUNT_0_EUR = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
    private static final Money FEE_AMOUNT_0_1_EUR = new Money(BigDecimal.valueOf(0.1)
            .setScale(2, RoundingMode.HALF_UP), Currency.getInstance("EUR"));
    public static final String IBAN = "IBAN_Code";
    public static final String DETAILS_TEXT = "Text";

    @Test
    void testToString() {
        PaymentType2 paymentType = new PaymentType2(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);

        String paymentTypeString = paymentType.toString();
        assertThat(paymentTypeString).contains("amount=1.00 USD");
        assertThat(paymentTypeString).contains("debtorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("creditorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("details=Text");
    }

    @Test
    void determineCancellationFeeShouldReturnZeroFee() {
        PaymentType2 paymentType = new PaymentType2(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now());

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_EUR);
    }

    @Test
    void determineCancellationFeeShouldReturnZeroDotZeroFiveFee() {
        PaymentType2 paymentType = new PaymentType2(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now().minusHours(1));

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_1_EUR);

    }

}