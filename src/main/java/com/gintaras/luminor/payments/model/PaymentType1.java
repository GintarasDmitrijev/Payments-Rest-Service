package com.gintaras.luminor.payments.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * Payment type that represent payment of TYPE1.
 *
 * @created 19/08/2020 - 8:20 PM
 * @author gintaras
 */
@Entity
@DiscriminatorValue("TYPE1")
public class PaymentType1 extends AbstractPaymentType {

    private final static float  FEE_FACTOR = 0.05f;

    private String details;

    public PaymentType1() {

    }

    public PaymentType1(Money amount, String debtorIBAN, String creditorIBAN, String details) {
        this(amount, debtorIBAN, creditorIBAN);
        this.details = details;
    }

    public PaymentType1(Money amount, String debtorIBAN, String creditorIBAN) {
        super(amount, debtorIBAN, creditorIBAN);
    }

    public PaymentType1(Money amount, String debtorIBAN, String creditorIBAN, LocalDateTime creationDate) {
        super(amount, debtorIBAN, creditorIBAN, creationDate);
    }

    @Override
    protected Money determineCancellationFee() {
        long hours = determineHoursPaymentIsInSystem();
        return new Money(BigDecimal.valueOf(hours * FEE_FACTOR)
                .setScale(2, RoundingMode.HALF_UP), Currency.getInstance("EUR"));
    }

    @JsonIgnore
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(", details=").append(details)
                .append("}");
        return builder.toString();
    }
}
