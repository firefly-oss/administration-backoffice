package com.vaadin.starter.business.ui.views.masterdata.document;
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

@Route(value = "document-template-types", layout = MainLayout.class)
@PageTitle("Document Template Types")
public class DocumentTemplateTypes extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<DocumentTemplateType> grid;
    private Registration resizeListener;
    private ListDataProvider<DocumentTemplateType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField typeCodeFilter;
    private TextField typeNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public DocumentTemplateTypes() {
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
        typeCodeFilter = new TextField();
        typeCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        typeCodeFilter.setClearButtonVisible(true);

        typeNameFilter = new TextField();
        typeNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        typeNameFilter.setClearButtonVisible(true);

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

        Button createTypeButton = UIUtils.createSuccessButton("Create Template Type");
        createTypeButton.addClickListener(e -> openCreateTypeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Template Type on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createTypeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(typeCodeFilter, "Type Code");
        formLayout.addFormItem(typeNameFilter, "Type Name");
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

    private Grid<DocumentTemplateType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("document-template-types");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(DocumentTemplateType::getTypeId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Type ID")
                .setSortable(true);
        grid.addColumn(DocumentTemplateType::getTypeCode)
                .setAutoWidth(true)
                .setHeader("Type Code")
                .setSortable(true);
        grid.addColumn(DocumentTemplateType::getTypeName)
                .setAutoWidth(true)
                .setHeader("Type Name")
                .setSortable(true);
        grid.addColumn(DocumentTemplateType::getDescription)
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
        dataProvider = DataProvider.ofCollection(getMockDocumentTemplateTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(DocumentTemplateType type) {
        Icon icon;
        if (type.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(DocumentTemplateType type) {
        return new Span(UIUtils.formatDate(type.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(DocumentTemplateType type) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(type));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteType(type));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(DocumentTemplateType type) {
        DocumentTemplateTypeDetails typeDetails = new DocumentTemplateTypeDetails(type);
        typeDetails.open();
    }

    private void deleteType(DocumentTemplateType type) {
        // This would be implemented to delete the type
        System.out.println("[DEBUG_LOG] Delete document template type: " + type.getTypeId());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply type code filter if not empty
        if (typeCodeFilter.getValue() != null && !typeCodeFilter.getValue().isEmpty()) {
            String typeCodeFilterValue = typeCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(type -> 
                type.getTypeCode() != null &&
                type.getTypeCode().toLowerCase().contains(typeCodeFilterValue));
        }

        // Apply type name filter if not empty
        if (typeNameFilter.getValue() != null && !typeNameFilter.getValue().isEmpty()) {
            String typeNameFilterValue = typeNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(type -> 
                type.getTypeName() != null &&
                type.getTypeName().toLowerCase().contains(typeNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(type -> 
                type.getDescription() != null &&
                type.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(type -> 
                type.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(type -> 
                type.getDateCreated() != null && 
                !type.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        typeCodeFilter.clear();
        typeNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateTypeDialog() {
        // This would be implemented to open a dialog for creating a new document template type
        System.out.println("[DEBUG_LOG] Create document template type dialog would open here");
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
        List<Grid.Column<DocumentTemplateType>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<DocumentTemplateType> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<DocumentTemplateType> getMockDocumentTemplateTypes() {
        List<DocumentTemplateType> types = new ArrayList<>();

        types.add(new DocumentTemplateType(1L, "EMAIL", "Email Template", "Templates for email communications", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        types.add(new DocumentTemplateType(2L, "PDF", "PDF Template", "Templates for PDF documents", Status.ACTIVE, LocalDateTime.now().minusDays(29), LocalDateTime.now().minusDays(4)));
        types.add(new DocumentTemplateType(3L, "CONTRACT", "Contract Template", "Templates for legal contracts", Status.ACTIVE, LocalDateTime.now().minusDays(28), LocalDateTime.now().minusDays(3)));
        types.add(new DocumentTemplateType(4L, "FORM", "Form Template", "Templates for online forms", Status.ACTIVE, LocalDateTime.now().minusDays(27), LocalDateTime.now().minusDays(2)));
        types.add(new DocumentTemplateType(5L, "LETTER", "Letter Template", "Templates for formal letters", Status.ACTIVE, LocalDateTime.now().minusDays(26), LocalDateTime.now().minusDays(1)));
        types.add(new DocumentTemplateType(6L, "INVOICE", "Invoice Template", "Templates for invoices", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now()));
        types.add(new DocumentTemplateType(7L, "REPORT", "Report Template", "Templates for reports", Status.INACTIVE, LocalDateTime.now().minusDays(24), LocalDateTime.now()));
        types.add(new DocumentTemplateType(8L, "NOTIFICATION", "Notification Template", "Templates for system notifications", Status.ACTIVE, LocalDateTime.now().minusDays(23), LocalDateTime.now()));
        types.add(new DocumentTemplateType(9L, "RECEIPT", "Receipt Template", "Templates for receipts", Status.INACTIVE, LocalDateTime.now().minusDays(22), LocalDateTime.now()));
        types.add(new DocumentTemplateType(10L, "CERTIFICATE", "Certificate Template", "Templates for certificates", Status.ACTIVE, LocalDateTime.now().minusDays(21), LocalDateTime.now()));

        return types;
    }

    // DocumentTemplateType model class
    public static class DocumentTemplateType {
        private Long typeId;
        private String typeCode;
        private String typeName;
        private String description;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public DocumentTemplateType(Long typeId, String typeCode, String typeName, 
                      String description, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.typeId = typeId;
            this.typeCode = typeCode;
            this.typeName = typeName;
            this.description = description;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getTypeId() {
            return typeId;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getDescription() {
            return description;
        }

        public Status getStatus() {
            return status;
        }

        public boolean isActive() {
            return status == Status.ACTIVE;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}