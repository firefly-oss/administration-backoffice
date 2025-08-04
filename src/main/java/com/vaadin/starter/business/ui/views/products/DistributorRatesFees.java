package com.vaadin.starter.business.ui.views.products;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.dto.products.RateFeeDTO;
import com.vaadin.starter.business.backend.service.ProductService;
import com.vaadin.starter.business.dummy.Distributor;
import com.vaadin.starter.business.dummy.DummyData;
import com.vaadin.starter.business.dummy.Product;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "distributor-rates-fees", layout = MainLayout.class)
@PageTitle("Distributor Rates and Fees")
public class DistributorRatesFees extends ViewFrame implements HasUrlParameter<Long> {

    private final ProductService productService;
    private Distributor distributor;
    private Grid<RateFeeDTO> grid;
    private ListDataProvider<RateFeeDTO> dataProvider;
    private List<RateFeeDTO> ratesFees = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Tabs productTabs;
    private Map<String, Grid<RateFeeDTO>> productGrids = new HashMap<>();
    private Map<String, Component> tabContents = new HashMap<>();

    @Autowired
    public DistributorRatesFees(ProductService productService) {
        this.productService = productService;
        // Initialize dummy products
        initializeDummyProducts();
    }

    private void initializeDummyProducts() {
        // Add some dummy products for demonstration
        products.add(new Product("P1", "Personal Loan", "Loan", "Short-term personal loan", 5.99, true, LocalDate.now()));
        products.add(new Product("P2", "Business Loan", "Loan", "Business financing", 7.5, true, LocalDate.now()));
        products.add(new Product("P3", "Savings Account", "Account", "High-interest savings", 2.1, true, LocalDate.now()));
        products.add(new Product("P4", "Investment Fund", "Investment", "Diversified investment portfolio", 0.0, true, LocalDate.now()));
        products.add(new Product("P5", "Credit Card", "Card", "Rewards credit card", 19.99, true, LocalDate.now()));
    }

    @Override
    public void setParameter(BeforeEvent event, Long distributorId) {
        // Find the distributor by ID
        Optional<Distributor> optionalDistributor = DummyData.getDistributors().stream()
                .filter(d -> d.getId().equals(distributorId))
                .findFirst();

        if (optionalDistributor.isPresent()) {
            this.distributor = optionalDistributor.get();
            setViewContent(createContent());
        } else {
            Notification notification = new Notification("Distributor not found", 3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            getUI().ifPresent(ui -> ui.navigate(RatesFees.class));
        }
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createHeader(), createTabbedLayout());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createTabbedLayout() {
        // Initialize with dummy data for demonstration
        initializeDummyRatesFees();

        // Create tabs for each product category
        List<String> categories = products.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());

        // Create tabs
        productTabs = new Tabs();
        VerticalLayout tabsContent = new VerticalLayout();
        tabsContent.setPadding(false);
        tabsContent.setSpacing(false);
        tabsContent.setSizeFull();

        // Create a tab for each product category
        for (String category : categories) {
            Tab tab = new Tab(category);
            productTabs.add(tab);

            // Create grid for this category
            Grid<RateFeeDTO> categoryGrid = createGridForCategory(category);
            VerticalLayout categoryContent = new VerticalLayout(categoryGrid);
            categoryContent.setPadding(false);
            categoryContent.setSpacing(false);
            categoryContent.setSizeFull();
            categoryContent.setVisible(false); // Initially hidden

            tabsContent.add(categoryContent);

            // Store references for later use
            productGrids.put(category, categoryGrid);
            tabContents.put(category, categoryContent);
        }

        // Make first tab visible by default
        if (!categories.isEmpty()) {
            tabContents.get(categories.get(0)).setVisible(true);
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

    private Grid<RateFeeDTO> createGridForCategory(String category) {
        Grid<RateFeeDTO> categoryGrid = new Grid<>();

        // Filter rates and fees for this category
        List<RateFeeDTO> categoryRatesFees = ratesFees.stream()
                .filter(rateFee -> rateFee.getProductCategory().equals(category))
                .collect(Collectors.toList());

        ListDataProvider<RateFeeDTO> categoryDataProvider = DataProvider.ofCollection(categoryRatesFees);
        categoryGrid.setDataProvider(categoryDataProvider);
        categoryGrid.setHeight("100%");

        categoryGrid.addColumn(RateFeeDTO::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        categoryGrid.addColumn(RateFeeDTO::getType)
                .setHeader("Type")
                .setWidth("150px")
                .setFlexGrow(1);

        categoryGrid.addColumn(rateFee -> String.format("%.2f%%", rateFee.getValue()))
                .setHeader("Value")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        categoryGrid.addColumn(RateFeeDTO::getCalculationMethod)
                .setHeader("Calculation Method")
                .setWidth("150px")
                .setFlexGrow(1);

        categoryGrid.addColumn(new ComponentRenderer<>(rateFee -> {
            Span badge = new Span(rateFee.isActive() ? "Active" : "Inactive");
            String theme = rateFee.isActive() ? "badge success" : "badge";
            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        categoryGrid.addColumn(rateFee -> UIUtils.formatDate(rateFee.getEffectiveDate()))
                .setHeader("Effective Date")
                .setWidth("150px")
                .setFlexGrow(0);

        categoryGrid.addColumn(rateFee -> UIUtils.formatDate(rateFee.getExpirationDate()))
                .setHeader("Expiration Date")
                .setWidth("150px")
                .setFlexGrow(0);

        // Add edit and delete buttons
        categoryGrid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("120px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        return categoryGrid;
    }

    private Component createHeader() {
        H3 title = new H3("Rates and Fees for " + distributor.getName());
        title.addClassName(LumoStyles.Heading.H3);

        Button backButton = new Button("Back to Rates and Fees", VaadinIcon.ARROW_LEFT.create());
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(RatesFees.class)));
        UIUtils.setPointerCursor(backButton);

        Button addButton = new Button("Add Rate/Fee", VaadinIcon.PLUS.create());
        addButton.getElement().getThemeList().add("primary");
        addButton.addClickListener(e -> showRateFeeDialog(null));
        UIUtils.setPointerCursor(addButton);

        HorizontalLayout headerLayout = new HorizontalLayout(backButton, title, addButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setPadding(true);

        return headerLayout;
    }

    private Grid<RateFeeDTO> createGrid() {
        // Initialize with dummy data for demonstration
        initializeDummyRatesFees();

        grid = new Grid<>();
        dataProvider = DataProvider.ofCollection(ratesFees);
        grid.setDataProvider(dataProvider);
        grid.setHeight("100%");

        grid.addColumn(RateFeeDTO::getName)
                .setHeader("Name")
                .setWidth("200px")
                .setFlexGrow(1);

        grid.addColumn(RateFeeDTO::getProductCategory)
                .setHeader("Product Category")
                .setWidth("150px")
                .setFlexGrow(1);

        grid.addColumn(RateFeeDTO::getType)
                .setHeader("Type")
                .setWidth("150px")
                .setFlexGrow(1);

        grid.addColumn(rateFee -> String.format("%.2f%%", rateFee.getValue()))
                .setHeader("Value")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.END);

        grid.addColumn(RateFeeDTO::getCalculationMethod)
                .setHeader("Calculation Method")
                .setWidth("150px")
                .setFlexGrow(1);

        grid.addColumn(new ComponentRenderer<>(rateFee -> {
            Span badge = new Span(rateFee.isActive() ? "Active" : "Inactive");
            String theme = rateFee.isActive() ? "badge success" : "badge";
            badge.getElement().getThemeList().add(theme);
            return badge;
        }))
                .setHeader("Status")
                .setWidth("100px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn(rateFee -> UIUtils.formatDate(rateFee.getEffectiveDate()))
                .setHeader("Effective Date")
                .setWidth("150px")
                .setFlexGrow(0);

        grid.addColumn(rateFee -> UIUtils.formatDate(rateFee.getExpirationDate()))
                .setHeader("Expiration Date")
                .setWidth("150px")
                .setFlexGrow(0);

        // Add edit and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("120px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        return grid;
    }

    private Component createActionButtons(RateFeeDTO rateFee) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        Button editButton = UIUtils.createButton(VaadinIcon.EDIT);
        editButton.addClickListener(e -> showRateFeeDialog(rateFee));
        editButton.getElement().getThemeList().add("small");
        editButton.getElement().getThemeList().add("tertiary");

        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteRateFee(rateFee));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().getThemeList().add("error");

        layout.add(editButton, deleteButton);
        return layout;
    }

    private void showRateFeeDialog(RateFeeDTO rateFee) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");

        boolean isEdit = rateFee != null;
        String dialogTitle = isEdit ? "Edit Rate/Fee" : "Add Rate/Fee";
        dialog.add(new H3(dialogTitle));

        FormLayout form = new FormLayout();
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        TextField nameField = new TextField("Name");
        nameField.setRequired(true);
        nameField.setWidthFull();

        ComboBox<String> productCategoryField = new ComboBox<>("Product Category");
        productCategoryField.setItems(products.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList()));
        productCategoryField.setRequired(true);
        productCategoryField.setWidthFull();

        ComboBox<String> typeField = new ComboBox<>("Type");
        typeField.setItems("Interest Rate", "Fee", "Commission", "Discount");
        typeField.setRequired(true);
        typeField.setWidthFull();

        NumberField valueField = new NumberField("Value (%)");
        valueField.setMin(0);
        valueField.setMax(100);
        valueField.setStep(0.01);
        valueField.setWidthFull();

        ComboBox<String> calculationMethodField = new ComboBox<>("Calculation Method");
        calculationMethodField.setItems("Simple", "Compound", "Fixed", "Variable");
        calculationMethodField.setRequired(true);
        calculationMethodField.setWidthFull();

        ComboBox<Boolean> activeField = new ComboBox<>("Status");
        activeField.setItems(true, false);
        activeField.setItemLabelGenerator(active -> active ? "Active" : "Inactive");
        activeField.setValue(true);
        activeField.setWidthFull();

        DatePicker effectiveDateField = new DatePicker("Effective Date");
        effectiveDateField.setRequired(true);
        effectiveDateField.setWidthFull();
        effectiveDateField.setValue(LocalDate.now());

        DatePicker expirationDateField = new DatePicker("Expiration Date");
        expirationDateField.setWidthFull();
        expirationDateField.setValue(LocalDate.now().plusYears(1));

        // If editing, populate fields with existing values
        if (isEdit) {
            nameField.setValue(rateFee.getName());
            productCategoryField.setValue(rateFee.getProductCategory());
            typeField.setValue(rateFee.getType());
            valueField.setValue(rateFee.getValue());
            calculationMethodField.setValue(rateFee.getCalculationMethod());
            activeField.setValue(rateFee.isActive());
            effectiveDateField.setValue(rateFee.getEffectiveDate());
            expirationDateField.setValue(rateFee.getExpirationDate());
        }

        form.add(nameField, 2);
        form.add(productCategoryField, typeField);
        form.add(valueField, calculationMethodField);
        form.add(activeField, 1);
        form.add(effectiveDateField, expirationDateField);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        UIUtils.setPointerCursor(cancelButton);
        Button saveButton = new Button("Save", e -> {
            if (validateForm(nameField, productCategoryField, typeField, valueField, calculationMethodField, effectiveDateField)) {
                saveRateFee(
                        isEdit ? rateFee.getId() : "RF" + (ratesFees.size() + 1),
                        nameField.getValue(),
                        productCategoryField.getValue(),
                        typeField.getValue(),
                        valueField.getValue(),
                        calculationMethodField.getValue(),
                        activeField.getValue(),
                        effectiveDateField.getValue(),
                        expirationDateField.getValue(),
                        isEdit
                );
                dialog.close();
            }
        });
        saveButton.getElement().getThemeList().add("primary");
        UIUtils.setPointerCursor(saveButton);

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

    private boolean validateForm(TextField nameField, ComboBox<String> productCategoryField, 
                                ComboBox<String> typeField, NumberField valueField, 
                                ComboBox<String> calculationMethodField, DatePicker effectiveDateField) {
        boolean isValid = true;

        if (nameField.isEmpty()) {
            nameField.setInvalid(true);
            isValid = false;
        }

        if (productCategoryField.isEmpty()) {
            productCategoryField.setInvalid(true);
            isValid = false;
        }

        if (typeField.isEmpty()) {
            typeField.setInvalid(true);
            isValid = false;
        }

        if (valueField.isEmpty()) {
            valueField.setInvalid(true);
            isValid = false;
        }

        if (calculationMethodField.isEmpty()) {
            calculationMethodField.setInvalid(true);
            isValid = false;
        }

        if (effectiveDateField.isEmpty()) {
            effectiveDateField.setInvalid(true);
            isValid = false;
        }

        return isValid;
    }

    private void saveRateFee(String id, String name, String productCategory, String type, 
                           Double value, String calculationMethod, Boolean isActive, 
                           LocalDate effectiveDate, LocalDate expirationDate, boolean isEdit) {
        RateFeeDTO rateFee = new RateFeeDTO(
                id, name, productCategory, type, value, calculationMethod, 
                isActive, effectiveDate, expirationDate
        );

        if (isEdit) {
            // Update existing rate/fee
            for (int i = 0; i < ratesFees.size(); i++) {
                if (ratesFees.get(i).getId().equals(id)) {
                    ratesFees.set(i, rateFee);
                    break;
                }
            }
            Notification.show("Rate/Fee updated successfully", 3000, Notification.Position.BOTTOM_CENTER);
        } else {
            // Add new rate/fee
            ratesFees.add(rateFee);
            Notification.show("Rate/Fee added successfully", 3000, Notification.Position.BOTTOM_CENTER);
        }

        // Refresh all grids
        refreshGrids();
    }

    private void refreshGrids() {
        // Refresh the main grid if it exists
        if (dataProvider != null) {
            dataProvider.refreshAll();
        }

        // Refresh all category grids
        for (String category : productGrids.keySet()) {
            Grid<RateFeeDTO> grid = productGrids.get(category);
            ListDataProvider<RateFeeDTO> provider = (ListDataProvider<RateFeeDTO>) grid.getDataProvider();

            // Update the data in the provider
            List<RateFeeDTO> categoryRatesFees = ratesFees.stream()
                    .filter(rateFee -> rateFee.getProductCategory().equals(category))
                    .collect(Collectors.toList());

            provider.getItems().clear();
            provider.getItems().addAll(categoryRatesFees);
            provider.refreshAll();
        }
    }

    private void deleteRateFee(RateFeeDTO rateFee) {
        Dialog confirmDialog = new Dialog();
        confirmDialog.setWidth("400px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.add(new H3("Confirm Delete"));
        layout.add(new Span("Are you sure you want to delete the rate/fee: " + rateFee.getName() + "?"));

        Button cancelButton = new Button("Cancel", e -> confirmDialog.close());
        UIUtils.setPointerCursor(cancelButton);
        Button deleteButton = new Button("Delete", e -> {
            ratesFees.removeIf(rf -> rf.getId().equals(rateFee.getId()));
            refreshGrids();
            confirmDialog.close();
            Notification.show("Rate/Fee deleted successfully", 3000, Notification.Position.BOTTOM_CENTER);
        });
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("primary");
        UIUtils.setPointerCursor(deleteButton);

        HorizontalLayout buttons = new HorizontalLayout(cancelButton, deleteButton);
        buttons.setJustifyContentMode(FlexLayout.JustifyContentMode.END);
        buttons.setWidthFull();

        layout.add(buttons);
        confirmDialog.add(layout);
        confirmDialog.open();
    }

    private void initializeDummyRatesFees() {
        // Add some dummy data for demonstration
        ratesFees.add(new RateFeeDTO(
                "RF1", 
                "Standard Interest Rate", 
                "Loan", 
                "Interest Rate", 
                5.99, 
                "Simple", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));

        ratesFees.add(new RateFeeDTO(
                "RF2", 
                "Late Payment Fee", 
                "Loan", 
                "Fee", 
                2.5, 
                "Fixed", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));

        ratesFees.add(new RateFeeDTO(
                "RF3", 
                "Premium Interest Rate", 
                "Investment", 
                "Interest Rate", 
                7.25, 
                "Compound", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));

        ratesFees.add(new RateFeeDTO(
                "RF4", 
                "Account Maintenance Fee", 
                "Account", 
                "Fee", 
                1.0, 
                "Fixed", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));

        ratesFees.add(new RateFeeDTO(
                "RF5", 
                "Distributor Commission", 
                "Loan", 
                "Commission", 
                3.0, 
                "Variable", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));

        ratesFees.add(new RateFeeDTO(
                "RF6", 
                "Card Annual Fee", 
                "Card", 
                "Fee", 
                25.0, 
                "Fixed", 
                true, 
                LocalDate.now(), 
                LocalDate.now().plusYears(1)
        ));
    }
}
