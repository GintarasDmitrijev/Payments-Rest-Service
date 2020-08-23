package com.gintaras.luminor.payments.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents amount of money along with its currency.
 *
 * @created 19/08/2020 - 8:12 PM
 * @author gintaras
 */
@Embeddable
public class Money {

    private final BigDecimal amount;
    private final Currency currency;

    public Money() {
        this.amount = BigDecimal.ZERO;
        this.currency = null;
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount.setScale(getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Money)) {
            return false;
        }

        final Money other = (Money) obj;
        return Objects.equals(getAmount(), other.getAmount())
                && Objects.equals(getCurrency(), other.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getCurrency());
    }
}
