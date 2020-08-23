package com.gintaras.luminor.payments.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Abstract base payment type for all payment types.
 *
 * @created 19/08/2020 - 8:05 PM
 * @author gintaras
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "PAYMENT_TYPE")
public abstract class AbstractPaymentType extends RepresentationModel<AbstractPaymentType> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "AMOUNT_VALUE")),
            @AttributeOverride(name = "currency", column = @Column(name = "AMOUNT_CURRENCY"))
    })
    @Embedded
    private Money amount;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "FEE_VALUE")),
            @AttributeOverride(name = "currency", column = @Column(name = "FEE_CURRENCY"))
    })
    @Embedded
    private Money cancellationFee;
    private String debtorIBAN;
    private String creditorIBAN;
    private LocalDateTime creationDate;
    private Boolean cancelled = Boolean.FALSE;

    public AbstractPaymentType() {
    }

    public AbstractPaymentType(Money amount, String debtorIBAN, String creditorIBAN) {
        this.amount = amount;
        this.debtorIBAN = debtorIBAN;
        this.creditorIBAN = creditorIBAN;
        this.creationDate = LocalDateTime.now();
    }

    public AbstractPaymentType(Money amount, String debtorIBAN, String creditorIBAN, LocalDateTime creationDate) {
        this.amount = amount;
        this.debtorIBAN = debtorIBAN;
        this.creditorIBAN = creditorIBAN;
        this.creationDate = creationDate;
    }

    protected abstract Money determineCancellationFee();

    protected long determineHoursPaymentIsInSystem() {
        LocalDateTime fromDate = this.getCreationDate();
        LocalDateTime toDate = LocalDateTime.now();
        return ChronoUnit.HOURS.between(fromDate, toDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getCancellationFee() {
        this.cancellationFee = determineCancellationFee();
        return this.cancellationFee;
    }

    @JsonIgnore
    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @JsonIgnore
    public String getDebtorIBAN() {
        return debtorIBAN;
    }

    public void setDebtorIBAN(String debtorIBAN) {
        this.debtorIBAN = debtorIBAN;
    }

    @JsonIgnore
    public String getCreditorIBAN() {
        return creditorIBAN;
    }

    public void setCreditorIBAN(String creditorIBAN) {
        this.creditorIBAN = creditorIBAN;
    }

    @JsonIgnore
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @JsonIgnore
    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        return String.format("Payment{id=%d, amount=%.2f %s, debtorIBAN=%s, creditorIBAN=%s, creationDate=%s",
                id, amount.getAmount(), amount.getCurrency(), debtorIBAN, creditorIBAN, creationDate);
    }
}
