package com.catalis.backoffice.configurations.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

import java.util.*;

@Route(value = "configurations", layout = MainLayout.class)
@PageTitle("Configurations | Bank Backoffice")
public class ConfigurationsView extends VerticalLayout {

    private final Map<Tab, Component> tabsToPages = new HashMap<>();
    private final Div pages = new Div();
    
    // Grids for different sections
    private final Grid<MasterDataItem> masterDataGrid = new Grid<>(MasterDataItem.class, false);
    private final Grid<PlatformConfig> platformConfigGrid = new Grid<>(PlatformConfig.class, false);
    private final Grid<User> userGrid = new Grid<>(User.class, false);
    private final Grid<Product> productGrid = new Grid<>(Product.class, false);

    public ConfigurationsView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button saveButton = new Button("Save Changes", VaadinIcon.CHECK.create());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button reloadButton = new Button("Reload", VaadinIcon.REFRESH.create());
        
        HorizontalLayout actions = new HorizontalLayout(saveButton, reloadButton);
        actions.setSpacing(true);

        add(new ViewToolbar("Configurations", actions));
        
        // Create tabs
        Tab masterDataTab = new Tab(VaadinIcon.DATABASE.create(), new Span("Master Data"));
        Tab platformConfigTab = new Tab(VaadinIcon.COG.create(), new Span("Platform Config"));
        Tab usersTab = new Tab(VaadinIcon.USERS.create(), new Span("Users"));
        Tab productsTab = new Tab(VaadinIcon.PACKAGE.create(), new Span("Products"));
        
        Tabs tabs = new Tabs(masterDataTab, platformConfigTab, usersTab, productsTab);
        tabs.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM);
        
        // Create pages for tabs
        Component masterDataPage = createMasterDataPage();
        Component platformConfigPage = createPlatformConfigPage();
        Component usersPage = createUsersPage();
        Component productsPage = createProductsPage();
        
        tabsToPages.put(masterDataTab, masterDataPage);
        tabsToPages.put(platformConfigTab, platformConfigPage);
        tabsToPages.put(usersTab, usersPage);
        tabsToPages.put(productsTab, productsPage);
        
        // Configure pages
        pages.setSizeFull();
        pages.add(masterDataPage, platformConfigPage, usersPage, productsPage);
        
        // Show only active page
        for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
            component.setVisible(false);
        }
        masterDataPage.setVisible(true);
        
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
    
    private Component createMasterDataPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add description
        Div description = new Div();
        description.setText("Master data includes reference data used across the system such as countries, currencies, and other standard values.");
        description.addClassNames(
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.MEDIUM
        );
        
        layout.add(description);
        
        // Configure master data grid
        configureMasterDataGrid();
        
        // Add grid with header
        H3 gridHeader = new H3("Master Data Items");
        gridHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(gridHeader, masterDataGrid);
        
        // Load mock data
        loadMockMasterData();
        
        return layout;
    }
    
    private Component createPlatformConfigPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add description
        Div description = new Div();
        description.setText("Platform configuration includes system-wide settings that control the behavior of the application.");
        description.addClassNames(
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.MEDIUM
        );
        
        layout.add(description);
        
        // Configure platform config grid
        configurePlatformConfigGrid();
        
        // Add grid with header
        H3 gridHeader = new H3("Platform Configurations");
        gridHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(gridHeader, platformConfigGrid);
        
        // Load mock data
        loadMockPlatformConfig();
        
        return layout;
    }
    
    private Component createUsersPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Configure users grid
        configureUserGrid();
        
        // Add grid with header
        H3 gridHeader = new H3("System Users");
        gridHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(gridHeader, userGrid);
        
        // Load mock data
        loadMockUsers();
        
        return layout;
    }
    
    private Component createProductsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Add description
        Div description = new Div();
        description.setText("Products configuration allows you to define and manage the banking products offered to clients.");
        description.addClassNames(
                LumoUtility.Padding.MEDIUM,
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.MEDIUM
        );
        
        layout.add(description);
        
        // Configure products grid
        configureProductGrid();
        
        // Add grid with header
        H3 gridHeader = new H3("Banking Products");
        gridHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        layout.add(gridHeader, productGrid);
        
        // Load mock data
        loadMockProducts();
        
        return layout;
    }
    
    private void configureMasterDataGrid() {
        masterDataGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        masterDataGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        masterDataGrid.setHeight("600px");
        
        masterDataGrid.addColumn(MasterDataItem::getId).setHeader("ID").setAutoWidth(true).setSortable(true);
        masterDataGrid.addColumn(MasterDataItem::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        masterDataGrid.addColumn(MasterDataItem::getCode).setHeader("Code").setAutoWidth(true).setSortable(true);
        masterDataGrid.addColumn(MasterDataItem::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        masterDataGrid.addColumn(MasterDataItem::getDescription).setHeader("Description").setAutoWidth(true).setSortable(true);
        masterDataGrid.addColumn(MasterDataItem::getActive).setHeader("Active").setAutoWidth(true).setSortable(true);
    }
    
    private void configurePlatformConfigGrid() {
        platformConfigGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        platformConfigGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        platformConfigGrid.setHeight("600px");
        
        platformConfigGrid.addColumn(PlatformConfig::getKey).setHeader("Key").setAutoWidth(true).setSortable(true);
        platformConfigGrid.addColumn(PlatformConfig::getValue).setHeader("Value").setAutoWidth(true).setSortable(true);
        platformConfigGrid.addColumn(PlatformConfig::getModule).setHeader("Module").setAutoWidth(true).setSortable(true);
        platformConfigGrid.addColumn(PlatformConfig::getDescription).setHeader("Description").setAutoWidth(true).setSortable(true);
        platformConfigGrid.addColumn(PlatformConfig::getLastModified).setHeader("Last Modified").setAutoWidth(true).setSortable(true);
        platformConfigGrid.addColumn(PlatformConfig::getModifiedBy).setHeader("Modified By").setAutoWidth(true).setSortable(true);
    }
    
    private void configureUserGrid() {
        userGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        userGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        userGrid.setHeight("600px");
        
        userGrid.addColumn(User::getUsername).setHeader("Username").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getFullName).setHeader("Full Name").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getEmail).setHeader("Email").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getRole).setHeader("Role").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getDepartment).setHeader("Department").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getStatus).setHeader("Status").setAutoWidth(true).setSortable(true);
        userGrid.addColumn(User::getLastLogin).setHeader("Last Login").setAutoWidth(true).setSortable(true);
    }
    
    private void configureProductGrid() {
        productGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        productGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        productGrid.setHeight("600px");
        
        productGrid.addColumn(Product::getId).setHeader("ID").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getCategory).setHeader("Category").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getDescription).setHeader("Description").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getFeatures).setHeader("Features").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getStatus).setHeader("Status").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(Product::getLastModified).setHeader("Last Modified").setAutoWidth(true).setSortable(true);
    }
    
    private void loadMockMasterData() {
        List<MasterDataItem> items = new ArrayList<>();
        
        // Countries
        items.add(new MasterDataItem(1, "Country", "US", "United States", "United States of America", "Yes"));
        items.add(new MasterDataItem(2, "Country", "CA", "Canada", "Canada", "Yes"));
        items.add(new MasterDataItem(3, "Country", "GB", "United Kingdom", "United Kingdom of Great Britain", "Yes"));
        
        // Currencies
        items.add(new MasterDataItem(4, "Currency", "USD", "US Dollar", "United States Dollar", "Yes"));
        items.add(new MasterDataItem(5, "Currency", "EUR", "Euro", "Euro", "Yes"));
        items.add(new MasterDataItem(6, "Currency", "GBP", "British Pound", "Pound Sterling", "Yes"));
        
        // Languages
        items.add(new MasterDataItem(7, "Language", "EN", "English", "English language", "Yes"));
        items.add(new MasterDataItem(8, "Language", "FR", "French", "French language", "Yes"));
        items.add(new MasterDataItem(9, "Language", "ES", "Spanish", "Spanish language", "Yes"));
        
        masterDataGrid.setItems(items);
    }
    
    private void loadMockPlatformConfig() {
        List<PlatformConfig> configs = new ArrayList<>();
        
        // Security settings
        configs.add(new PlatformConfig("security.password.min_length", "8", "Security", "Minimum password length", "2023-05-15", "admin"));
        configs.add(new PlatformConfig("security.password.require_special_chars", "true", "Security", "Require special characters in passwords", "2023-05-15", "admin"));
        configs.add(new PlatformConfig("security.session.timeout_minutes", "30", "Security", "Session timeout in minutes", "2023-05-15", "admin"));
        
        // Notification settings
        configs.add(new PlatformConfig("notifications.email.enabled", "true", "Notifications", "Enable email notifications", "2023-06-10", "admin"));
        configs.add(new PlatformConfig("notifications.sms.enabled", "true", "Notifications", "Enable SMS notifications", "2023-06-10", "admin"));
        configs.add(new PlatformConfig("notifications.push.enabled", "false", "Notifications", "Enable push notifications", "2023-06-10", "admin"));
        
        // Reporting settings
        configs.add(new PlatformConfig("reporting.default_format", "PDF", "Reporting", "Default report format", "2023-07-05", "admin"));
        configs.add(new PlatformConfig("reporting.max_rows", "10000", "Reporting", "Maximum rows in reports", "2023-07-05", "admin"));
        configs.add(new PlatformConfig("reporting.include_logo", "true", "Reporting", "Include logo in reports", "2023-07-05", "admin"));
        
        platformConfigGrid.setItems(configs);
    }
    
    private void loadMockUsers() {
        List<User> users = new ArrayList<>();
        
        users.add(new User("admin", "System Administrator", "admin@bankexample.com", "Administrator", "IT", "Active", "2023-08-15 09:30:45"));
        users.add(new User("jsmith", "John Smith", "john.smith@bankexample.com", "Manager", "Retail Banking", "Active", "2023-08-14 14:22:10"));
        users.add(new User("mjohnson", "Mary Johnson", "mary.johnson@bankexample.com", "Operator", "Customer Service", "Active", "2023-08-15 08:15:33"));
        users.add(new User("rwilliams", "Robert Williams", "robert.williams@bankexample.com", "Viewer", "Finance", "Inactive", "2023-07-28 11:05:22"));
        users.add(new User("sbrown", "Sarah Brown", "sarah.brown@bankexample.com", "Manager", "Corporate Banking", "Active", "2023-08-15 10:45:18"));
        
        userGrid.setItems(users);
    }
    
    private void loadMockProducts() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("ACC-001", "Basic Checking Account", "Account", "Standard checking account with no monthly fee", "Free debit card, Online banking, Mobile app", "Active", "2023-06-10"));
        products.add(new Product("ACC-002", "Premium Savings Account", "Account", "High-interest savings account", "2.5% APY, No minimum balance, No monthly fee", "Active", "2023-06-10"));
        products.add(new Product("CRD-001", "Standard Credit Card", "Card", "Basic credit card with rewards", "1% cashback, No annual fee, 18.99% APR", "Active", "2023-06-15"));
        products.add(new Product("CRD-002", "Premium Travel Card", "Card", "Travel rewards credit card", "3x points on travel, Airport lounge access, $95 annual fee", "Active", "2023-06-15"));
        products.add(new Product("LN-001", "Home Mortgage", "Loan", "Fixed-rate home mortgage", "3.5% - 5.5% APR, 15-30 year terms, No prepayment penalty", "Active", "2023-07-01"));
        
        productGrid.setItems(products);
    }
    
    // Master Data Item class
    public static class MasterDataItem {
        private final int id;
        private final String type;
        private final String code;
        private final String name;
        private final String description;
        private final String active;
        
        public MasterDataItem(int id, String type, String code, String name, String description, String active) {
            this.id = id;
            this.type = type;
            this.code = code;
            this.name = name;
            this.description = description;
            this.active = active;
        }
        
        public int getId() { return id; }
        public String getType() { return type; }
        public String getCode() { return code; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getActive() { return active; }
    }
    
    // Platform Config class
    public static class PlatformConfig {
        private final String key;
        private final String value;
        private final String module;
        private final String description;
        private final String lastModified;
        private final String modifiedBy;
        
        public PlatformConfig(String key, String value, String module, String description, String lastModified, String modifiedBy) {
            this.key = key;
            this.value = value;
            this.module = module;
            this.description = description;
            this.lastModified = lastModified;
            this.modifiedBy = modifiedBy;
        }
        
        public String getKey() { return key; }
        public String getValue() { return value; }
        public String getModule() { return module; }
        public String getDescription() { return description; }
        public String getLastModified() { return lastModified; }
        public String getModifiedBy() { return modifiedBy; }
    }
    
    // User class
    public static class User {
        private final String username;
        private final String fullName;
        private final String email;
        private final String role;
        private final String department;
        private final String status;
        private final String lastLogin;
        
        public User(String username, String fullName, String email, String role, String department, String status, String lastLogin) {
            this.username = username;
            this.fullName = fullName;
            this.email = email;
            this.role = role;
            this.department = department;
            this.status = status;
            this.lastLogin = lastLogin;
        }
        
        public String getUsername() { return username; }
        public String getFullName() { return fullName; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getDepartment() { return department; }
        public String getStatus() { return status; }
        public String getLastLogin() { return lastLogin; }
    }
    
    // Product class
    public static class Product {
        private final String id;
        private final String name;
        private final String category;
        private final String description;
        private final String features;
        private final String status;
        private final String lastModified;
        
        public Product(String id, String name, String category, String description, String features, String status, String lastModified) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.description = description;
            this.features = features;
            this.status = status;
            this.lastModified = lastModified;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getDescription() { return description; }
        public String getFeatures() { return features; }
        public String getStatus() { return status; }
        public String getLastModified() { return lastModified; }
    }
}