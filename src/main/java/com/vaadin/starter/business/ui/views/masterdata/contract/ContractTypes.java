package com.vaadin.starter.business.ui.views.masterdata.contract;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "contract-types", layout = MainLayout.class)
@PageTitle("Contract Types")
public class ContractTypes extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ContractType> grid;
    private Registration resizeListener;
    private ListDataProvider<ContractType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField contractCodeFilter;
    private TextField nameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public ContractTypes() {
        setViewContent(createContent());

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        contractCodeFilter = new TextField();
        contractCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        contractCodeFilter.setClearButtonVisible(true);

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        creationDateFilter = new DatePicker();
        creationDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createContractTypeButton = UIUtils.createSuccessButton("Create Contract Type");
        createContractTypeButton.addClickListener(e -> openCreateContractTypeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Contract Type on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createContractTypeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(contractCodeFilter, "Contract Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(creationDateFilter, "Creation Date After");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Create a container for the form and buttons
        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<ContractType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("contract-types");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(ContractType::getContractCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Contract Code")
                .setSortable(true);
        grid.addColumn(ContractType::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(ContractType::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Creation Date")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with view and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(getMockContractTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(ContractType contractType) {
        Icon icon;
        if (contractType.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(ContractType contractType) {
        return new Span(UIUtils.formatDate(contractType.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(ContractType contractType) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(contractType));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteContractType(contractType));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(ContractType contractType) {
        ContractTypeDetails contractTypeDetails = new ContractTypeDetails(contractType);
        contractTypeDetails.open();
    }

    private void deleteContractType(ContractType contractType) {
        // This would be implemented to delete the contract type
        System.out.println("[DEBUG_LOG] Delete contract type: " + contractType.getContractCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply contract code filter if not empty
        if (contractCodeFilter.getValue() != null && !contractCodeFilter.getValue().isEmpty()) {
            String contractCodeFilterValue = contractCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(contractType -> 
                contractType.getContractCode() != null &&
                contractType.getContractCode().toLowerCase().contains(contractCodeFilterValue));
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(contractType -> 
                contractType.getName() != null &&
                contractType.getName().toLowerCase().contains(nameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(contractType -> 
                contractType.getDescription() != null &&
                contractType.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(contractType -> 
                contractType.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(contractType -> 
                contractType.getDateCreated() != null && 
                !contractType.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        contractCodeFilter.clear();
        nameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateContractTypeDialog() {
        // This would be implemented to open a dialog for creating a new contract type
        System.out.println("[DEBUG_LOG] Create contract type dialog would open here");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        if (resizeListener != null) {
            resizeListener.remove();
        }
        super.onDetach(detachEvent);
    }

    private void updateVisibleColumns(int width) {
        boolean mobile = width < MOBILE_BREAKPOINT;
        List<Grid.Column<ContractType>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<ContractType> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<ContractType> getMockContractTypes() {
        List<ContractType> contractTypes = new ArrayList<>();

        contractTypes.add(new ContractType(1L, "CT001", "Standard Loan", "Standard loan contract for personal use", true, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        contractTypes.add(new ContractType(2L, "CT002", "Business Loan", "Business loan contract for commercial use", true, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        contractTypes.add(new ContractType(3L, "CT003", "Mortgage", "Mortgage contract for property purchase", false, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        contractTypes.add(new ContractType(4L, "CT004", "Auto Loan", "Auto loan contract for vehicle purchase", true, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        contractTypes.add(new ContractType(5L, "CT005", "Credit Card", "Credit card agreement contract", true, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        contractTypes.add(new ContractType(6L, "CT006", "Line of Credit", "Line of credit contract for flexible borrowing", false, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        contractTypes.add(new ContractType(7L, "CT007", "Student Loan", "Student loan contract for education expenses", true, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        contractTypes.add(new ContractType(8L, "CT008", "Equipment Lease", "Equipment lease contract for business equipment", true, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        contractTypes.add(new ContractType(9L, "CT009", "Refinance", "Refinance contract for existing loans", false, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        contractTypes.add(new ContractType(10L, "CT010", "Consolidation", "Consolidation contract for multiple loans", true, LocalDateTime.now(), LocalDateTime.now()));

        return contractTypes;
    }

    // Contract Type model class
    public static class ContractType {
        private Long contractId;
        private String contractCode;
        private String name;
        private String description;
        private Boolean isActive;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public ContractType(Long contractId, String contractCode, String name, String description, 
                           Boolean isActive, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.contractId = contractId;
            this.contractCode = contractCode;
            this.name = name;
            this.description = description;
            this.isActive = isActive;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getContractId() {
            return contractId;
        }

        public String getContractCode() {
            return contractCode;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public boolean isActive() {
            return isActive != null && isActive;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}