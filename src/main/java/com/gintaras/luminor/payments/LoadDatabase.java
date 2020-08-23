package com.gintaras.luminor.payments;

import com.gintaras.luminor.payments.model.Money;
import com.gintaras.luminor.payments.model.Type1PaymentType;
import com.gintaras.luminor.payments.model.Type2PaymentType;
import com.gintaras.luminor.payments.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * Preload database with some amount of data.
 *
 * @created 22/08/2020 - 11:14 AM
 * @author gintaras
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private static final Money AMOUNT_1_USD = new Money(BigDecimal.ONE, Currency.getInstance("USD"));
    public static final String IBAN = "IBAN_Code";
    public static final String DETAILS_TEXT = "Text";

    @Bean
    CommandLineRunner initDatabase(PaymentRepository paymentRepository) {

        return args -> {
            log.info("Preloading " + paymentRepository.save(new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT)));
            log.info("Preloading " + paymentRepository.save(new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT)));
            log.info("Preloading " + paymentRepository.save(new Type2PaymentType(AMOUNT_1_USD, IBAN, IBAN, DETAILS_TEXT)));
            log.info("Preloading " + paymentRepository.save(new Type1PaymentType(AMOUNT_1_USD, IBAN, IBAN
                    ,LocalDateTime.now().minusHours(1))));
        };
    }
}
