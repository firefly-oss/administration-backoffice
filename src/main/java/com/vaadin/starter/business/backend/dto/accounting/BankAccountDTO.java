package com.vaadin.starter.business.backend.dto.accounting;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for BankAccount.
 */
public class BankAccountDTO {

    private UUID id;
    private String bank;
    private String account;
    private String owner;
    private Double availability;
    private LocalDate updated;
    private String logoPath;

    /**
     * Default constructor.
     */
    public BankAccountDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the bank account ID
     * @param bank         the bank name
     * @param account      the account number
     * @param owner        the account owner
     * @param availability the account availability
     * @param updated      the last update date
     * @param logoPath     the logo path
     */
    public BankAccountDTO(UUID id, String bank, String account, String owner,
                        Double availability, LocalDate updated, String logoPath) {
        this.id = id;
        this.bank = bank;
        this.account = account;
        this.owner = owner;
        this.availability = availability;
        this.updated = updated;
        this.logoPath = logoPath;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getAvailability() {
        return availability;
    }

    public void setAvailability(Double availability) {
        this.availability = availability;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}