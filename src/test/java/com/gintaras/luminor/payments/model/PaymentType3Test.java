package com.gintaras.luminor.payments.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit test for {@link PaymentType3}
 *
 * @created 20/08/2020 - 7:16 PM
 * @author gintaras
 */
class PaymentType3Test {

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));
    private static final Money FEE_AMOUNT_0_EUR = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
    private static final Money FEE_AMOUNT_0_15_EUR = new Money(BigDecimal.valueOf(0.15)
            .setScale(2, RoundingMode.HALF_UP), Currency.getInstance("EUR"));
    public static final String IBAN = "IBAN_Code";
    public static final String BIC = "BIC_Code";

    @Test
    void testToString() {
        PaymentType3 paymentType = new PaymentType3(AMOUNT_1_USD, IBAN, IBAN, BIC);

        String paymentTypeString = paymentType.toString();
        assertThat(paymentTypeString).contains("amount=1.00 USD");
        assertThat(paymentTypeString).contains("debtorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("creditorIBAN=IBAN_Code");
        assertThat(paymentTypeString).contains("creditorBankBIC=BIC_Code");
        assertThat(paymentTypeString).doesNotContain("details=");
    }

    @Test
    void determineCancellationFeeShouldReturnZeroFee() {
        PaymentType3 paymentType = new PaymentType3(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now());

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_EUR);
    }

    @Test
    void determineCancellationFeeShouldReturn_0_15_Fee() {
        PaymentType3 paymentType = new PaymentType3(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now().minusHours(1));

        Money cancellationFee = paymentType.determineCancellationFee();

        assertThat(cancellationFee).isNotNull();
        assertThat(cancellationFee).isEqualTo(FEE_AMOUNT_0_15_EUR);

    }

}