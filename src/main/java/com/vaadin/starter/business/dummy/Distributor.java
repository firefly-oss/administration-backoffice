package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

public class Distributor {

    public enum Status {
        ACTIVE("Active"), INACTIVE("Inactive"), SUSPENDED("Suspended");

        private String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private Long id;
    private String name;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
    private Status status;
    private LocalDate contractDate;
    private double commissionRate;

    public Distributor(Long id, String name, String contactPerson, String email, 
                      String phone, String address, Status status, 
                      LocalDate contractDate, double commissionRate) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.contractDate = contractDate;
        this.commissionRate = commissionRate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}