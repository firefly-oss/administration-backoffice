/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.backend;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

	public enum Status {
		PENDING("Pending"), PROCESSED("Processed");

		private String name;

		Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private UUID id;
	private Status status;
	private String company;
	private String iban;
	private Double amount;
	private boolean attachment;
	private LocalDate date;

	public Transaction(UUID id, Status status, String company, String iban,
	                   Double amount, boolean attachment, LocalDate date) {
		this.id = id;
		this.status = status;
		this.company = company;
		this.iban = iban;
		this.amount = amount;
		this.attachment = attachment;
		this.date = date;
	}

	public UUID getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public String getCompany() {
		return company;
	}

	public String getIBAN() {
		return iban;
	}

	public Double getAmount() {
		return amount;
	}

	public boolean hasAttachment() {
		return attachment;
	}

	public LocalDate getDate() {
		return date;
	}
}
