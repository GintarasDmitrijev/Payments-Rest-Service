package com.gintaras.luminor.payments.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents JSON response used in web test.
 *
 * @created 22/08/2020 - 10:46 AM
 * @author gintaras
 */
public class Response {

    @JsonProperty("_embedded")
    private Embedded embedded;

    @JsonIgnore
    private List<String> _links;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }


    public List<String> get_links() {
        return _links;
    }

    public void set_links(List<String> _links) {
        this._links = _links;
    }
}
