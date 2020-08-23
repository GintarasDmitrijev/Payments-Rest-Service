package com.gintaras.luminor.payments.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the JSON structure of response.
 *
 * @created 22/08/2020 - 10:48 AM
 * @author gintaras
 */
public class Embedded {

    @JsonProperty("type1PaymentTypes")
    @JsonAlias("type2PaymentTypes")
    List<PaymentType> payments = new ArrayList<>();

    public Embedded() {

    }

    public List<PaymentType> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentType> payments) {
        this.payments = payments;
    }
}
