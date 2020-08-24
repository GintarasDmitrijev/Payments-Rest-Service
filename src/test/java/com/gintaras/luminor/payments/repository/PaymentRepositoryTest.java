package com.gintaras.luminor.payments.repository;


import com.gintaras.luminor.payments.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit test for {@link PaymentRepository}
 *
 * @created 20/08/2020 - 5:05 PM
 * @author gintaras
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PaymentRepositoryTest {

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));
    public static final String IBAN = "IBAN_Code";
    public static final String DETAILS_TEXT = "Text";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PaymentRepository repository;

    @Test
    public void should_find_no_payments_if_repository_is_empty() {
        // when
        Iterable<AbstractPaymentType> payments = repository.findAll();
        // then
        assertThat(payments).isEmpty();
    }

    @Test
    public void should_find_all_payments() {
        // when
        PaymentType1 payment1 = new PaymentType1(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);
        entityManager.persist(payment1);

        PaymentType2 payment2 = new PaymentType2(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);
        entityManager.persist(payment2);

        PaymentType3 payment3 = new PaymentType3(AMOUNT_1_USD, IBAN, IBAN, IBAN);
        entityManager.persist(payment3);

        Iterable<AbstractPaymentType> payments = repository.findAll();

        // then
        assertThat(payments).hasSize(3).contains(payment1, payment2, payment3);
    }

}