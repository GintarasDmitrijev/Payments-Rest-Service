package com.gintaras.luminor.payments.repository;

/**
 *  Payments JPA repository.
 *
 * @created 19/08/2020 - 8:53 PM
 * @author gintaras
 */

import com.gintaras.luminor.payments.model.AbstractPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<AbstractPaymentType, Long> {
}
