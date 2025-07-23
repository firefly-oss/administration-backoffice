package com.vaadin.starter.business.dummy;

import java.time.LocalDate;
import java.util.List;

public class Catalog {

    public enum Type {
        STANDARD("Standard"), SEASONAL("Seasonal"), PROMOTIONAL("Promotional"), EXCLUSIVE("Exclusive");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private Long id;
    private String name;
    private String description;
    private Type type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distributorId;
    private String distributorName;
    private int itemCount;
    private boolean active;

    public Catalog(Long id, String name, String description, Type type, 
                  LocalDate startDate, LocalDate endDate, Long distributorId, 
                  String distributorName, int itemCount, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.itemCount = itemCount;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getDistributorId() {
        return distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public boolean isActive() {
        return active;
    }
}