package com.vaadin.starter.business.backend;

import java.time.LocalDate;
import java.util.UUID;

public class BankAccount {

	private UUID id;
	private String bank;
	private String account;
	private String owner;
	private Double availability;
	private LocalDate updated;
	private String path;

	public BankAccount(UUID id, String bank, String account, String company,
	                   Double availability, LocalDate updated, String path) {
		this.id = id;
		this.bank = bank;
		this.account = account;
		this.owner = company;
		this.availability = availability;
		this.updated = updated;
		this.path = path;
	}

	public UUID getId() {
		return id;
	}

	public String getBank() {
		return bank;
	}

	public String getAccount() {
		return account;
	}

	public String getOwner() {
		return owner;
	}

	public Double getAvailability() {
		return availability;
	}

	public LocalDate getUpdated() {
		return updated;
	}

	public String getLogoPath() {
	    return path;
	}
}
