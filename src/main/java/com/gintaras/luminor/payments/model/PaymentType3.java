package com.gintaras.luminor.payments.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * Payment type that represent payment of TYPE3.
 *
 * @created 19/08/2020 - 8:22 PM
 * @author gintaras
 */
@Entity
@DiscriminatorValue("TYPE3")
public class PaymentType3 extends AbstractPaymentType {

    private final static float  FEE_FACTOR = 0.15f;

    private String creditorBankBIC;

    public PaymentType3(Money amount, String debtorIBAN, String creditorIBAN, String creditorBankBIC) {
        this(amount, debtorIBAN, creditorIBAN);
        this.creditorBankBIC = creditorBankBIC;
    }

    public PaymentType3(Money amount, String debtorIBAN, String creditorIBAN) {
        super(amount, debtorIBAN, creditorIBAN);
    }

    public PaymentType3(Money amount, String debtorIBAN, String creditorIBAN, LocalDateTime creationDate) {
        super(amount, debtorIBAN, creditorIBAN, creationDate);
    }

    @Override
    protected Money determineCancellationFee() {
        long hours = determineHoursPaymentIsInSystem();
        return new Money(BigDecimal.valueOf(hours * FEE_FACTOR)
                .setScale(2, RoundingMode.HALF_UP), Currency.getInstance("EUR"));
    }

    public String getCreditorBankBIC() {
        return creditorBankBIC;
    }

    public void setCreditorBankBIC(String creditorBankBIC) {
        this.creditorBankBIC = creditorBankBIC;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("creditorBankBIC=").append(creditorBankBIC)
                .append("}");
        return builder.toString();
    }
}
