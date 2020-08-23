package com.gintaras.luminor.payments.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertSame;


/**
 * JUnit test for {@link Money}
 *
 * @created 20/08/2020 - 6:22 PM
 * @author gintaras
 */
class MoneyTest {

    private static final Currency USD = Currency.getInstance("USD");

    @Test
    public void testGetCurrency() {
        final Money money = new Money(BigDecimal.ZERO, USD);
        assertSame(money.getCurrency(), USD);

    }

}