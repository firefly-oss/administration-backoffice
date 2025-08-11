package com.vaadin.starter.business.ui.views.products;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Route(value = "product-management", layout = MainLayout.class)
@PageTitle("Product Management")
public class ProductManagement extends ViewFrame {

    private Tabs productTabs;
    private Map<String, Component> tabContents = new HashMap<>();
    private Map<String, Grid<ProductItem>> productGrids = new HashMap<>();
    private Map<String, List<ProductItem>> productData = new HashMap<>();

    // Product categories (tabs)
    private final String[] PRODUCT_CATEGORIES = {
            "Accounts", "Cards", "Leasing", "Renting", "Factoring", "Confirming"
    };

    public ProductManagement() {
        setViewContent(createContent());
        initializeMockData();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createTabbedLayout());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createTabbedLayout() {
        // Create tabs
        productTabs = new Tabs();
        VerticalLayout tabsContent = new VerticalLayout();
        tabsContent.setPadding(false);
        tabsContent.setSpacing(false);
        tabsContent.setSizeFull();

        // Create a tab for each product category
        for (String category : PRODUCT_CATEGORIES) {
            Tab tab = new Tab(category);
            productTabs.add(tab);

            // Create content for this tab
            Component categoryContent = createTabContent(category);
            categoryContent.setVisible(false); // Initially hidden
            tabsContent.add(categoryContent);

            // Store reference for later use
            tabContents.put(category, categoryContent);
        }

        // Make first tab visible by default
        if (PRODUCT_CATEGORIES.length > 0) {
            tabContents.get(PRODUCT_CATEGORIES[0]).setVisible(true);
            productTabs.setSelectedIndex(0);
        }

        // Handle tab selection
        productTabs.addSelectedChangeListener(event -> {
            // Hide all content
            tabContents.values().forEach(content -> content.setVisible(false));

            // Show selected tab content
            Tab selectedTab = productTabs.getSelectedTab();
            if (selectedTab != null) {
                String selectedCategory = selectedTab.getLabel();
                Component content = tabContents.get(selectedCategory);
                if (content != null) {
                    content.setVisible(true);
                }
            }
        });

        // Create layout with tabs and content
        VerticalLayout layout = new VerticalLayout(productTabs, tabsContent);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setSizeFull();

        return layout;
    }

    private Component createTabContent(String category) {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        content.setSpacing(true);
        content.setSizeFull();

        // Add search form
        content.add(createSearchForm(category));

        // Add grid
        Grid<ProductItem> grid = createGrid(category);
        productGrids.put(category, grid);
        content.add(grid);
        content.expand(grid);

        return content;
    }

    private Component createSearchForm(String category) {
        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName(LumoStyles.Padding.Bottom.M);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Create filter fields
        TextField nameFilter = new TextField("Name");
        nameFilter.setPlaceholder("Filter by name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);

        TextField codeFilter = new TextField("Code");
        codeFilter.setPlaceholder("Filter by code");
        codeFilter.setClearButtonVisible(true);
        codeFilter.setValueChangeMode(ValueChangeMode.EAGER);

        ComboBox<String> statusFilter = new ComboBox<>("Status");
        statusFilter.setPlaceholder("Filter by status");
        statusFilter.setItems("Active", "Inactive", "Pending");
        statusFilter.setClearButtonVisible(true);

        DatePicker dateFilter = new DatePicker("Date");
        dateFilter.setPlaceholder("Filter by date");
        dateFilter.setClearButtonVisible(true);

        // Add fields to form
        formLayout.add(nameFilter, codeFilter, statusFilter, dateFilter);

        // Create buttons
        Button resetBtn = UIUtils.createTertiaryButton("Clear");
        resetBtn.addClickListener(e -> {
            nameFilter.clear();
            codeFilter.clear();
            statusFilter.clear();
            dateFilter.clear();
            refreshGrid(category);
        });

        Button searchBtn = UIUtils.createPrimaryButton("Search");
        searchBtn.addClickListener(e -> {
            Grid<ProductItem> grid = productGrids.get(category);
            ListDataProvider<ProductItem> dataProvider = (ListDataProvider<ProductItem>) grid.getDataProvider();

            dataProvider.clearFilters();

            if (!nameFilter.isEmpty()) {
                dataProvider.addFilter(item -> 
                    item.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase()));
            }

            if (!codeFilter.isEmpty()) {
                dataProvider.addFilter(item -> 
                    item.getCode().toLowerCase().contains(codeFilter.getValue().toLowerCase()));
            }

            if (statusFilter.getValue() != null) {
                dataProvider.addFilter(item -> 
                    item.getStatus().equals(statusFilter.getValue()));
            }

            if (dateFilter.getValue() != null) {
                dataProvider.addFilter(item -> 
                    item.getCreationDate().equals(dateFilter.getValue()));
            }
        });

        Button createBtn = UIUtils.createSuccessButton("Create " + category.substring(0, category.length() - 1));
        createBtn.addClickListener(e -> showProductDialog(category, null));

        // Create button layout
        HorizontalLayout buttonLayout = new HorizontalLayout(createBtn, searchBtn, resetBtn);
        buttonLayout.setSpacing(true);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        Div wrapper = new Div(formLayout, buttonLayout);
        wrapper.addClassName(LumoStyles.Padding.Bottom.L);
        wrapper.getStyle().set("padding", "1em");
        wrapper.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        wrapper.getStyle().set("border-radius", "var(--lumo-border-radius)");
        wrapper.getStyle().set("background-color", "var(--lumo-base-color)");
        wrapper.getStyle().set("margin-bottom", "1em");

        return wrapper;
    }

    private Grid<ProductItem> createGrid(String category) {
        Grid<ProductItem> grid = new Grid<>();
        grid.setHeight("100%");

        grid.addColumn(ProductItem::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1)
                .setSortable(true);

        grid.addColumn(ProductItem::getCode)
                .setHeader("Code")
                .setWidth("120px")
                .setFlexGrow(0)
                .setSortable(true);

        grid.addColumn(ProductItem::getDescription)
                .setHeader("Description")
                .setWidth("300px")
                .setFlexGrow(1);

        grid.addColumn(new ComponentRenderer<>(item -> {
            Span badge = new Span(item.getStatus());
            String theme = "badge";

            if ("Active".equals(item.getStatus())) {
                theme = "badge success";
            } else if ("Inactive".equals(item.getStatus())) {
                theme = "badge error";
            } else if ("Pending".equals(item.getStatus())) {
                theme = "badge contrast";
            }

            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("120px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn(item -> UIUtils.formatDate(item.getCreationDate()))
                .setHeader("Creation Date")
                .setWidth("150px")
                .setFlexGrow(0)
                .setSortable(true);

        // Add actions column with Configure button
        grid.addColumn(new ComponentRenderer<>(item -> {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSpacing(true);

            Button configureButton = UIUtils.createButton(VaadinIcon.COG);
            configureButton.addClickListener(e -> showConfigureDialog(category, item));
            configureButton.getElement().getThemeList().add("small");
            configureButton.getElement().getThemeList().add("tertiary");
            configureButton.getElement().setAttribute("title", "Configure");

            Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
            deleteButton.addClickListener(e -> deleteProduct(category, item));
            deleteButton.getElement().getThemeList().add("small");
            deleteButton.getElement().getThemeList().add("tertiary");
            deleteButton.getElement().getThemeList().add("error");

            layout.add(configureButton, deleteButton);
            return layout;
        }))
                .setHeader("Actions")
                .setWidth("200px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        return grid;
    }

    private void showProductDialog(String category, ProductItem item) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");

        boolean isEdit = item != null;
        String dialogTitle = isEdit ? "Edit " + category.substring(0, category.length() - 1) : 
                                     "Create " + category.substring(0, category.length() - 1);
        dialog.add(new H3(dialogTitle));

        FormLayout form = new FormLayout();
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        TextField nameField = new TextField("Name");
        nameField.setRequired(true);
        nameField.setWidthFull();

        TextField codeField = new TextField("Code");
        codeField.setRequired(true);
        codeField.setWidthFull();

        TextField descriptionField = new TextField("Description");
        descriptionField.setWidthFull();

        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Active", "Inactive", "Pending");
        statusField.setValue("Active");
        statusField.setRequired(true);
        statusField.setWidthFull();

        DatePicker creationDateField = new DatePicker("Creation Date");
        creationDateField.setValue(LocalDate.now());
        creationDateField.setWidthFull();

        // If editing, populate fields with existing values
        if (isEdit) {
            nameField.setValue(item.getName());
            codeField.setValue(item.getCode());
            descriptionField.setValue(item.getDescription());
            statusField.setValue(item.getStatus());
            creationDateField.setValue(item.getCreationDate());
        }

        form.add(nameField, 2);
        form.add(codeField, descriptionField);
        form.add(statusField, creationDateField);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button saveButton = new Button("Save", e -> {
            if (validateForm(nameField, codeField, statusField)) {
                saveProduct(
                        category,
                        isEdit ? item.getId() : UUID.randomUUID().toString(),
                        nameField.getValue(),
                        codeField.getValue(),
                        descriptionField.getValue(),
                        statusField.getValue(),
                        creationDateField.getValue(),
                        isEdit
                );
                dialog.close();
            }
        });
        saveButton.getElement().getThemeList().add("primary");

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, saveButton);
        buttons.setJustifyContentMode(FlexLayout.JustifyContentMode.END);
        buttons.setWidthFull();

        VerticalLayout dialogLayout = new VerticalLayout(form, buttons);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(true);
        dialogLayout.getStyle().set("padding", "0 1em 1em 1em");

        dialog.add(dialogLayout);
        dialog.open();
    }

    private void showConfigureDialog(String category, ProductItem item) {
        Dialog dialog = new Dialog();
        dialog.setWidth("500px");
        dialog.add(new H3("Configure Fees for " + item.getName()));

        FormLayout form = new FormLayout();
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        NumberField standardFeeField = new NumberField("Standard Fee (%)");
        standardFeeField.setValue(item.getStandardFee());
        standardFeeField.setMin(0);
        standardFeeField.setMax(100);
        standardFeeField.setStep(0.01);
        standardFeeField.setWidthFull();

        NumberField premiumFeeField = new NumberField("Premium Fee (%)");
        premiumFeeField.setValue(item.getPremiumFee());
        premiumFeeField.setMin(0);
        premiumFeeField.setMax(100);
        premiumFeeField.setStep(0.01);
        premiumFeeField.setWidthFull();

        NumberField discountFeeField = new NumberField("Discount (%)");
        discountFeeField.setValue(item.getDiscountFee());
        discountFeeField.setMin(0);
        discountFeeField.setMax(100);
        discountFeeField.setStep(0.01);
        discountFeeField.setWidthFull();

        form.add(standardFeeField, premiumFeeField, discountFeeField);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button saveButton = new Button("Save", e -> {
            item.setStandardFee(standardFeeField.getValue());
            item.setPremiumFee(premiumFeeField.getValue());
            item.setDiscountFee(discountFeeField.getValue());

            refreshGrid(category);
            dialog.close();

            Notification notification = new Notification(
                    "Fees configured successfully for " + item.getName(), 
                    3000, 
                    Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.open();
        });
        saveButton.getElement().getThemeList().add("primary");

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, saveButton);
        buttons.setJustifyContentMode(FlexLayout.JustifyContentMode.END);
        buttons.setWidthFull();

        VerticalLayout dialogLayout = new VerticalLayout(form, buttons);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(true);
        dialogLayout.getStyle().set("padding", "0 1em 1em 1em");

        dialog.add(dialogLayout);
        dialog.open();
    }

    private boolean validateForm(TextField nameField, TextField codeField, ComboBox<String> statusField) {
        boolean isValid = true;

        if (nameField.isEmpty()) {
            nameField.setInvalid(true);
            isValid = false;
        }

        if (codeField.isEmpty()) {
            codeField.setInvalid(true);
            isValid = false;
        }

        if (statusField.isEmpty()) {
            statusField.setInvalid(true);
            isValid = false;
        }

        return isValid;
    }

    private void saveProduct(String category, String id, String name, String code, 
                           String description, String status, LocalDate creationDate, 
                           boolean isEdit) {
        ProductItem product = new ProductItem(
                id, name, code, description, status, creationDate,
                0.0, 0.0, 0.0
        );

        List<ProductItem> items = productData.get(category);

        if (isEdit) {
            // Update existing product
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(id)) {
                    // Preserve fee values when updating
                    product.setStandardFee(items.get(i).getStandardFee());
                    product.setPremiumFee(items.get(i).getPremiumFee());
                    product.setDiscountFee(items.get(i).getDiscountFee());
                    items.set(i, product);
                    break;
                }
            }
            Notification.show(category.substring(0, category.length() - 1) + " updated successfully", 
                    3000, Notification.Position.BOTTOM_CENTER);
        } else {
            // Add new product
            items.add(product);
            Notification.show(category.substring(0, category.length() - 1) + " created successfully", 
                    3000, Notification.Position.BOTTOM_CENTER);
        }

        refreshGrid(category);
    }

    private void deleteProduct(String category, ProductItem item) {
        Dialog confirmDialog = new Dialog();
        confirmDialog.setWidth("400px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.add(new H3("Confirm Delete"));
        layout.add(new Span("Are you sure you want to delete " + item.getName() + "?"));

        Button cancelButton = new Button("Cancel", e -> confirmDialog.close());
        Button deleteButton = new Button("Delete", e -> {
            List<ProductItem> items = productData.get(category);
            items.removeIf(p -> p.getId().equals(item.getId()));
            refreshGrid(category);
            confirmDialog.close();

            Notification notification = new Notification(
                    category.substring(0, category.length() - 1) + " deleted successfully", 
                    3000, 
                    Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.open();
        });
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("primary");

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, deleteButton);
        buttons.setJustifyContentMode(FlexLayout.JustifyContentMode.END);
        buttons.setWidthFull();

        layout.add(buttons);
        confirmDialog.add(layout);
        confirmDialog.open();
    }

    private void refreshGrid(String category) {
        Grid<ProductItem> grid = productGrids.get(category);
        if (grid != null) {
            List<ProductItem> items = productData.get(category);
            grid.setItems(items);
        }
    }

    private void initializeMockData() {
        // Initialize data for each category
        for (String category : PRODUCT_CATEGORIES) {
            List<ProductItem> items = new ArrayList<>();
            productData.put(category, items);

            // Add 3-5 mock items for each category
            int itemCount = 3 + (int)(Math.random() * 3);
            for (int i = 1; i <= itemCount; i++) {
                String id = UUID.randomUUID().toString();
                String name = category.substring(0, category.length() - 1) + " " + i;
                String code = category.substring(0, 3).toUpperCase() + "-" + (100 + i);
                String description = "Sample " + category.substring(0, category.length() - 1) + " product " + i;
                String status = Math.random() > 0.3 ? "Active" : (Math.random() > 0.5 ? "Inactive" : "Pending");
                LocalDate creationDate = LocalDate.now().minusDays((long)(Math.random() * 365));

                // Random fee percentages
                double standardFee = Math.round(Math.random() * 1000) / 100.0;
                double premiumFee = Math.round(Math.random() * 1500) / 100.0;
                double discountFee = Math.round(Math.random() * 500) / 100.0;

                items.add(new ProductItem(id, name, code, description, status, creationDate, 
                        standardFee, premiumFee, discountFee));
            }

            // Set the grid data
            Grid<ProductItem> grid = productGrids.get(category);
            if (grid != null) {
                grid.setItems(items);
            }
        }
    }

    // Product item class
    public static class ProductItem {
        private String id;
        private String name;
        private String code;
        private String description;
        private String status;
        private LocalDate creationDate;
        private double standardFee;
        private double premiumFee;
        private double discountFee;

        public ProductItem(String id, String name, String code, String description, 
                         String status, LocalDate creationDate, 
                         double standardFee, double premiumFee, double discountFee) {
            this.id = id;
            this.name = name;
            this.code = code;
            this.description = description;
            this.status = status;
            this.creationDate = creationDate;
            this.standardFee = standardFee;
            this.premiumFee = premiumFee;
            this.discountFee = discountFee;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public LocalDate getCreationDate() { return creationDate; }
        public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

        public double getStandardFee() { return standardFee; }
        public void setStandardFee(double standardFee) { this.standardFee = standardFee; }

        public double getPremiumFee() { return premiumFee; }
        public void setPremiumFee(double premiumFee) { this.premiumFee = premiumFee; }

        public double getDiscountFee() { return discountFee; }
        public void setDiscountFee(double discountFee) { this.discountFee = discountFee; }
    }
}
