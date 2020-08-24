package com.gintaras.luminor.payments.service;

import com.gintaras.luminor.payments.exception.PaymentValidationException;
import com.gintaras.luminor.payments.model.AbstractPaymentType;
import com.gintaras.luminor.payments.model.Money;
import com.gintaras.luminor.payments.model.PaymentType1;
import com.gintaras.luminor.payments.model.PaymentType2;
import com.gintaras.luminor.payments.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * JUnit test for {@link PaymentServiceImpl}.
 *
 * @created 23/08/2020 - 1:08 PM
 * @author gintaras
 */
@SpringBootTest
class PaymentServiceImplTest {

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));

    private static final String IBAN = "IBAN_Code";
    private static final String DETAILS_TEXT = "Text";
    public static final long ID_1 = 1L;

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;


    @Test
    void shouldFindAllPayments() {
        PaymentType1 paymentType1 = new PaymentType1(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);
        PaymentType2 paymentType2 = new PaymentType2(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(paymentType1, paymentType2));

        List<AbstractPaymentType> result = paymentService.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void shouldFindOnePaymentById() {
        PaymentType1 paymentType1 = new PaymentType1(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT);
        when(paymentRepository.findById(ID_1)).thenReturn(Optional.of(paymentType1));

        AbstractPaymentType result = paymentService.findById(ID_1).get();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldNotThrowValidationException_ifPaymentCreatedToday() {
        PaymentType1 paymentType1 = new PaymentType1(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now().minusHours(1));

        assertDoesNotThrow(() -> paymentService.validatePaymentForCancellation(paymentType1));
    }

    @Test
    void shouldThrowValidationException_ifPaymentCreatedNotToday() {
        PaymentType1 paymentType1 = new PaymentType1(AMOUNT_1_USD, IBAN, IBAN, LocalDateTime.now().minusDays(2));

        Exception exception = assertThrows(PaymentValidationException.class, () -> {
            paymentService.validatePaymentForCancellation(paymentType1);
        });

        String expectedMessage = "The payment with id null could not be cancelled";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}