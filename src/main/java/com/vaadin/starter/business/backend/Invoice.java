package com.vaadin.starter.business.backend;

import java.time.LocalDate;
import java.util.UUID;

public class Invoice {

	private final UUID id;
	private final Status status;
	private final Order order;
	private final LocalDate invoiceDate;
	private final LocalDate dueDate;

	public enum Status {
		OUTSTANDING("Outstanding"), OPEN("Open"), PAID("Paid"), OVERDUE(
				"Overdue");

		private String name;

		Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public Invoice(UUID id, Status status, Order order, LocalDate invoiceDate,
	               LocalDate dueDate) {
		this.id = id;
		this.status = status;
		this.order = order;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
	}

	public UUID getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public Order getOrder() {
		return order;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
}
