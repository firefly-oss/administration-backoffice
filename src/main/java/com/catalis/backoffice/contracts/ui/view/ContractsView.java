package com.catalis.backoffice.contracts.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "contracts", layout = MainLayout.class)
@PageTitle("Contracts | Bank Backoffice")
public class ContractsView extends VerticalLayout {

    private final Grid<Contract> grid = new Grid<>(Contract.class, false);
    private final Random random = new Random();

    public ContractsView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button createButton = new Button("New Contract", VaadinIcon.PLUS.create());
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button importButton = new Button("Import", VaadinIcon.UPLOAD.create());
        Button exportButton = new Button("Export", VaadinIcon.DOWNLOAD.create());
        
        HorizontalLayout actions = new HorizontalLayout(createButton, importButton, exportButton);
        actions.setSpacing(true);

        add(new ViewToolbar("Contracts", actions));
        
        // Configure grid
        configureGrid();
        
        // Add grid to layout
        add(grid);
        
        // Load mock data
        loadMockData();
    }
    
    private void configureGrid() {
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        grid.setSizeFull();
        
        grid.addColumn(Contract::getId).setHeader("Contract ID").setAutoWidth(true).setSortable(true);
        grid.addColumn(Contract::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        grid.addColumn(Contract::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        grid.addColumn(contract -> contract.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
            .setHeader("Start Date").setAutoWidth(true).setSortable(true);
        grid.addColumn(contract -> contract.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
            .setHeader("End Date").setAutoWidth(true).setSortable(true);
        grid.addColumn(Contract::getAmount).setHeader("Amount").setAutoWidth(true).setSortable(true);
        grid.addColumn(this::createStatusComponent).setHeader("Status").setAutoWidth(true);
        
        // Add action column
        grid.addComponentColumn(contract -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);
            
            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.getElement().setAttribute("aria-label", "Edit");
            
            Button deleteButton = new Button(VaadinIcon.TRASH.create());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            deleteButton.getElement().setAttribute("aria-label", "Delete");
            
            actions.add(editButton, deleteButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }
    
    private Span createStatusComponent(Contract contract) {
        Span status = new Span(contract.getStatus());
        status.getElement().getThemeList().clear();
        
        switch (contract.getStatus()) {
            case "Active":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Pending":
                status.getElement().getThemeList().add("badge");
                break;
            case "Expired":
                status.getElement().getThemeList().add("badge error");
                break;
            default:
                status.getElement().getThemeList().add("badge contrast");
        }
        
        return status;
    }
    
    private void loadMockData() {
        List<Contract> contracts = new ArrayList<>();
        
        // Generate mock contracts
        String[] clientNames = {"Acme Corporation", "Globex Inc.", "Stark Industries", "Wayne Enterprises", 
                               "Umbrella Corp.", "Cyberdyne Systems", "Soylent Corp.", "Massive Dynamic"};
        
        String[] contractTypes = {"Loan", "Mortgage", "Credit Line", "Investment", "Insurance", "Leasing"};
        String[] statuses = {"Active", "Pending", "Expired"};
        
        for (int i = 1; i <= 50; i++) {
            String id = String.format("CTR-%06d", i);
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String type = contractTypes[random.nextInt(contractTypes.length)];
            
            LocalDate startDate = LocalDate.now().minusMonths(random.nextInt(24));
            LocalDate endDate = startDate.plusMonths(random.nextInt(36) + 12);
            
            double amount = 10000 + random.nextInt(990000);
            String status = statuses[random.nextInt(statuses.length)];
            
            contracts.add(new Contract(id, clientName, type, startDate, endDate, amount, status));
        }
        
        grid.setItems(contracts);
    }
    
    // Contract data class
    public static class Contract {
        private final String id;
        private final String clientName;
        private final String type;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final double amount;
        private final String status;
        
        public Contract(String id, String clientName, String type, LocalDate startDate, 
                       LocalDate endDate, double amount, String status) {
            this.id = id;
            this.clientName = clientName;
            this.type = type;
            this.startDate = startDate;
            this.endDate = endDate;
            this.amount = amount;
            this.status = status;
        }
        
        public String getId() {
            return id;
        }
        
        public String getClientName() {
            return clientName;
        }
        
        public String getType() {
            return type;
        }
        
        public LocalDate getStartDate() {
            return startDate;
        }
        
        public LocalDate getEndDate() {
            return endDate;
        }
        
        public String getAmount() {
            return String.format("$%,.2f", amount);
        }
        
        public String getStatus() {
            return status;
        }
    }
}