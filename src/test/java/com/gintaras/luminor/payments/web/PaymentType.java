package com.gintaras.luminor.payments.web;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Payment type DTO.
 * @created 22/08/2020 - 12:08 PM
 * @author gintaras
 */
public class PaymentType {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String details;

    @JsonIgnore
    private String cancellationFee;

    @JsonIgnore
    private List<String> _links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public String getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(String cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> get_links() {
        return _links;
    }

    public void set_links(List<String> _links) {
        this._links = _links;
    }
}
