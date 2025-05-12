package com.catalis.backoffice.erp.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Route(value = "erp", layout = MainLayout.class)
@PageTitle("ERP | Bank Backoffice")
public class ERPView extends VerticalLayout {

    private final Random random = new Random();
    private final Map<Tab, Component> tabsToPages = new HashMap<>();
    private final Div pages = new Div();
    
    // Grids for different sections
    private final Grid<FinancialEntry> financialGrid = new Grid<>(FinancialEntry.class, false);
    private final Grid<InventoryItem> inventoryGrid = new Grid<>(InventoryItem.class, false);
    private final Grid<Employee> employeeGrid = new Grid<>(Employee.class, false);

    public ERPView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button exportButton = new Button("Export Data", VaadinIcon.DOWNLOAD.create());
        exportButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button settingsButton = new Button("ERP Settings", VaadinIcon.COG.create());
        
        HorizontalLayout actions = new HorizontalLayout(exportButton, settingsButton);
        actions.setSpacing(true);

        add(new ViewToolbar("Enterprise Resource Planning", actions));
        
        // Create tabs
        Tab financialTab = new Tab(VaadinIcon.MONEY.create(), new Span("Financial"));
        Tab inventoryTab = new Tab(VaadinIcon.PACKAGE.create(), new Span("Inventory"));
        Tab hrTab = new Tab(VaadinIcon.USERS.create(), new Span("Human Resources"));
        
        Tabs tabs = new Tabs(financialTab, inventoryTab, hrTab);
        tabs.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM);
        
        // Create pages for tabs
        Component financialPage = createFinancialPage();
        Component inventoryPage = createInventoryPage();
        Component hrPage = createHRPage();
        
        tabsToPages.put(financialTab, financialPage);
        tabsToPages.put(inventoryTab, inventoryPage);
        tabsToPages.put(hrTab, hrPage);
        
        // Configure pages
        pages.setSizeFull();
        pages.add(financialPage, inventoryPage, hrPage);
        
        // Show only active page
        for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
            component.setVisible(false);
        }
        financialPage.setVisible(true);
        
        // Add tab change listener
        tabs.addSelectedChangeListener(event -> {
            for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
                component.setVisible(false);
            }
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        
        add(tabs, pages);
    }
    
    private Component createFinancialPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add financial summary cards
        HorizontalLayout summaryLayout = new HorizontalLayout();
        summaryLayout.setWidthFull();
        summaryLayout.setPadding(true);
        summaryLayout.setSpacing(true);
        
        summaryLayout.add(
            createSummaryCard("Revenue (MTD)", "$2.45M", VaadinIcon.MONEY_DEPOSIT),
            createSummaryCard("Expenses (MTD)", "$1.78M", VaadinIcon.MONEY_WITHDRAW),
            createSummaryCard("Profit (MTD)", "$670K", VaadinIcon.CHART_LINE),
            createSummaryCard("Budget Variance", "+5.2%", VaadinIcon.CALC_BOOK)
        );
        
        layout.add(summaryLayout);
        
        // Configure financial grid
        configureFinancialGrid();
        
        // Add grid with header
        H3 transactionsHeader = new H3("Financial Transactions");
        transactionsHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(transactionsHeader, financialGrid);
        
        // Load mock data
        loadMockFinancialData();
        
        return layout;
    }
    
    private Component createInventoryPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add inventory summary
        HorizontalLayout summaryLayout = new HorizontalLayout();
        summaryLayout.setWidthFull();
        summaryLayout.setPadding(true);
        summaryLayout.setSpacing(true);
        
        summaryLayout.add(
            createSummaryCard("Total Items", "1,245", VaadinIcon.PACKAGE),
            createSummaryCard("Low Stock Items", "32", VaadinIcon.EXCLAMATION_CIRCLE),
            createSummaryCard("Total Value", "$1.2M", VaadinIcon.MONEY),
            createSummaryCard("Pending Orders", "18", VaadinIcon.TRUCK)
        );
        
        layout.add(summaryLayout);
        
        // Configure inventory grid
        configureInventoryGrid();
        
        // Add search field
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidthFull();
        searchLayout.setPadding(true);
        searchLayout.addClassNames(LumoUtility.Background.CONTRAST_5);
        
        com.vaadin.flow.component.textfield.TextField searchField = new com.vaadin.flow.component.textfield.TextField();
        searchField.setPlaceholder("Search inventory...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();
        
        Button addItemButton = new Button("Add Item", VaadinIcon.PLUS.create());
        addItemButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        searchLayout.add(searchField, addItemButton);
        searchLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        
        // Add grid with header
        H3 inventoryHeader = new H3("Inventory Items");
        inventoryHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(searchLayout, inventoryHeader, inventoryGrid);
        
        // Load mock data
        loadMockInventoryData();
        
        return layout;
    }
    
    private Component createHRPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add HR summary
        HorizontalLayout summaryLayout = new HorizontalLayout();
        summaryLayout.setWidthFull();
        summaryLayout.setPadding(true);
        summaryLayout.setSpacing(true);
        
        summaryLayout.add(
            createSummaryCard("Total Employees", "245", VaadinIcon.USERS),
            createSummaryCard("Departments", "12", VaadinIcon.BUILDING),
            createSummaryCard("New Hires (MTD)", "8", VaadinIcon.USER_CHECK),
            createSummaryCard("Open Positions", "15", VaadinIcon.USER_CLOCK)
        );
        
        layout.add(summaryLayout);
        
        // Configure employee grid
        configureEmployeeGrid();
        
        // Add search field
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidthFull();
        searchLayout.setPadding(true);
        searchLayout.addClassNames(LumoUtility.Background.CONTRAST_5);
        
        com.vaadin.flow.component.textfield.TextField searchField = new com.vaadin.flow.component.textfield.TextField();
        searchField.setPlaceholder("Search employees...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();
        
        Button addEmployeeButton = new Button("Add Employee", VaadinIcon.PLUS.create());
        addEmployeeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        searchLayout.add(searchField, addEmployeeButton);
        searchLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        
        // Add grid with header
        H3 employeeHeader = new H3("Employee Directory");
        employeeHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(searchLayout, employeeHeader, employeeGrid);
        
        // Load mock data
        loadMockEmployeeData();
        
        return layout;
    }
    
    private Component createSummaryCard(String title, String value, VaadinIcon icon) {
        VerticalLayout card = new VerticalLayout();
        card.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.SMALL
        );
        card.setSpacing(false);
        card.setWidth("25%");
        
        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        
        com.vaadin.flow.component.icon.Icon cardIcon = icon.create();
        cardIcon.setColor("var(--lumo-primary-color)");
        
        Span titleSpan = new Span(title);
        titleSpan.addClassNames(
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.TextColor.SECONDARY
        );
        
        header.add(cardIcon, titleSpan);
        
        Span valueSpan = new Span(value);
        valueSpan.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.FontWeight.BOLD,
                LumoUtility.TextColor.PRIMARY
        );
        
        card.add(header, valueSpan);
        return card;
    }
    
    private void configureFinancialGrid() {
        financialGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        financialGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        financialGrid.setHeight("400px");
        
        financialGrid.addColumn(FinancialEntry::getId).setHeader("Transaction ID").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getDate).setHeader("Date").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getDescription).setHeader("Description").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getCategory).setHeader("Category").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getAmount).setHeader("Amount").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        financialGrid.addColumn(FinancialEntry::getDepartment).setHeader("Department").setAutoWidth(true).setSortable(true);
    }
    
    private void configureInventoryGrid() {
        inventoryGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        inventoryGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        inventoryGrid.setHeight("400px");
        
        inventoryGrid.addColumn(InventoryItem::getId).setHeader("Item ID").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getCategory).setHeader("Category").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getQuantity).setHeader("Quantity").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getUnitPrice).setHeader("Unit Price").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getTotalValue).setHeader("Total Value").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getLocation).setHeader("Location").setAutoWidth(true).setSortable(true);
        inventoryGrid.addColumn(InventoryItem::getLastUpdated).setHeader("Last Updated").setAutoWidth(true).setSortable(true);
    }
    
    private void configureEmployeeGrid() {
        employeeGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        employeeGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        employeeGrid.setHeight("400px");
        
        employeeGrid.addColumn(Employee::getId).setHeader("Employee ID").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getEmail).setHeader("Email").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getPhone).setHeader("Phone").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getDepartment).setHeader("Department").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getPosition).setHeader("Position").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getHireDate).setHeader("Hire Date").setAutoWidth(true).setSortable(true);
        employeeGrid.addColumn(Employee::getStatus).setHeader("Status").setAutoWidth(true).setSortable(true);
    }
    
    private void loadMockFinancialData() {
        List<FinancialEntry> entries = new ArrayList<>();
        
        // Generate mock financial entries
        String[] descriptions = {"Salary Payment", "Office Supplies", "Software License", "Rent Payment", 
                                "Utility Bill", "Marketing Campaign", "Client Meeting", "Equipment Purchase"};
        
        String[] categories = {"Payroll", "Office Expenses", "IT", "Facilities", 
                              "Utilities", "Marketing", "Client Relations", "Equipment"};
        
        String[] types = {"Expense", "Revenue"};
        
        String[] departments = {"Retail Banking", "Corporate Banking", "IT", "Operations", 
                               "HR", "Finance", "Legal", "Administration"};
        
        for (int i = 1; i <= 20; i++) {
            String id = String.format("TRX-%06d", i);
            
            LocalDate date = LocalDate.now().minusDays(random.nextInt(180));
            String formattedDate = date.toString();
            
            int descIndex = random.nextInt(descriptions.length);
            String description = descriptions[descIndex];
            String category = categories[descIndex];
            
            String type = types[random.nextInt(types.length)];
            double amount = 500 + random.nextInt(9500);
            String formattedAmount = String.format("$%,.2f", amount);
            
            String department = departments[random.nextInt(departments.length)];
            
            entries.add(new FinancialEntry(id, formattedDate, description, category, formattedAmount, type, department));
        }
        
        financialGrid.setItems(entries);
    }
    
    private void loadMockInventoryData() {
        List<InventoryItem> items = new ArrayList<>();
        
        // Generate mock inventory items
        String[] itemNames = {"Desktop Computer", "Laptop", "Monitor", "Keyboard", "Mouse", 
                             "Office Chair", "Desk", "Filing Cabinet", "Printer", "Scanner"};
        
        String[] categories = {"IT Equipment", "IT Equipment", "IT Equipment", "IT Equipment", "IT Equipment", 
                              "Furniture", "Furniture", "Furniture", "Office Equipment", "Office Equipment"};
        
        String[] locations = {"Main Office", "Branch 1", "Branch 2", "Branch 3", "Warehouse"};
        
        for (int i = 0; i < itemNames.length; i++) {
            String id = String.format("ITM-%04d", i + 1);
            String name = itemNames[i];
            String category = categories[i];
            
            int quantity = 5 + random.nextInt(95);
            
            double unitPrice = 50 + random.nextInt(950);
            String formattedUnitPrice = String.format("$%,.2f", unitPrice);
            
            double totalValue = unitPrice * quantity;
            String formattedTotalValue = String.format("$%,.2f", totalValue);
            
            String location = locations[random.nextInt(locations.length)];
            
            LocalDate lastUpdated = LocalDate.now().minusDays(random.nextInt(90));
            String formattedLastUpdated = lastUpdated.toString();
            
            items.add(new InventoryItem(id, name, category, quantity, formattedUnitPrice, 
                                       formattedTotalValue, location, formattedLastUpdated));
        }
        
        inventoryGrid.setItems(items);
    }
    
    private void loadMockEmployeeData() {
        List<Employee> employees = new ArrayList<>();
        
        // Generate mock employees
        String[] firstNames = {"John", "Jane", "Robert", "Emily", "Michael", "Sarah", "David", "Lisa"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Wilson"};
        String[] departments = {"Retail Banking", "Corporate Banking", "IT", "Operations", "HR", "Finance", "Legal"};
        String[] positions = {"Manager", "Specialist", "Analyst", "Assistant", "Director", "Coordinator"};
        String[] statuses = {"Active", "On Leave", "Terminated"};
        
        for (int i = 0; i < 20; i++) {
            String id = String.format("EMP-%04d", i + 1);
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String name = firstName + " " + lastName;
            
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@bankexample.com";
            String phone = "+1 " + (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(9000) + 1000);
            
            String department = departments[random.nextInt(departments.length)];
            String position = positions[random.nextInt(positions.length)];
            
            LocalDate hireDate = LocalDate.now().minusMonths(random.nextInt(120));
            String formattedHireDate = hireDate.toString();
            
            String status = statuses[random.nextInt(statuses.length)];
            
            employees.add(new Employee(id, name, email, phone, department, position, formattedHireDate, status));
        }
        
        employeeGrid.setItems(employees);
    }
    
    // Financial Entry data class
    public static class FinancialEntry {
        private final String id;
        private final String date;
        private final String description;
        private final String category;
        private final String amount;
        private final String type;
        private final String department;
        
        public FinancialEntry(String id, String date, String description, String category, 
                             String amount, String type, String department) {
            this.id = id;
            this.date = date;
            this.description = description;
            this.category = category;
            this.amount = amount;
            this.type = type;
            this.department = department;
        }
        
        public String getId() { return id; }
        public String getDate() { return date; }
        public String getDescription() { return description; }
        public String getCategory() { return category; }
        public String getAmount() { return amount; }
        public String getType() { return type; }
        public String getDepartment() { return department; }
    }
    
    // Inventory Item data class
    public static class InventoryItem {
        private final String id;
        private final String name;
        private final String category;
        private final int quantity;
        private final String unitPrice;
        private final String totalValue;
        private final String location;
        private final String lastUpdated;
        
        public InventoryItem(String id, String name, String category, int quantity, String unitPrice,
                            String totalValue, String location, String lastUpdated) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalValue = totalValue;
            this.location = location;
            this.lastUpdated = lastUpdated;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public int getQuantity() { return quantity; }
        public String getUnitPrice() { return unitPrice; }
        public String getTotalValue() { return totalValue; }
        public String getLocation() { return location; }
        public String getLastUpdated() { return lastUpdated; }
    }
    
    // Employee data class
    public static class Employee {
        private final String id;
        private final String name;
        private final String email;
        private final String phone;
        private final String department;
        private final String position;
        private final String hireDate;
        private final String status;
        
        public Employee(String id, String name, String email, String phone, String department,
                       String position, String hireDate, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.department = department;
            this.position = position;
            this.hireDate = hireDate;
            this.status = status;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getDepartment() { return department; }
        public String getPosition() { return position; }
        public String getHireDate() { return hireDate; }
        public String getStatus() { return status; }
    }
}