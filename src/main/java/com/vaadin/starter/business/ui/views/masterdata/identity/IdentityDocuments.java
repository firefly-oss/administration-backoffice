package com.vaadin.starter.business.ui.views.masterdata.identity;
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

@Route(value = "identity-documents", layout = MainLayout.class)
@PageTitle("Identity Documents")
public class IdentityDocuments extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<IdentityDocument> grid;
    private Registration resizeListener;
    private ListDataProvider<IdentityDocument> dataProvider;
    private UI ui;

    // Search form fields
    private TextField documentCodeFilter;
    private TextField documentNameFilter;
    private TextField categoryFilter;
    private TextField countryFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public IdentityDocuments() {
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
        documentCodeFilter = new TextField();
        documentCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        documentCodeFilter.setClearButtonVisible(true);

        documentNameFilter = new TextField();
        documentNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        documentNameFilter.setClearButtonVisible(true);

        categoryFilter = new TextField();
        categoryFilter.setValueChangeMode(ValueChangeMode.EAGER);
        categoryFilter.setClearButtonVisible(true);

        countryFilter = new TextField();
        countryFilter.setValueChangeMode(ValueChangeMode.EAGER);
        countryFilter.setClearButtonVisible(true);

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

        Button createDocumentButton = UIUtils.createSuccessButton("Create Identity Document");
        createDocumentButton.addClickListener(e -> openCreateDocumentDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Identity Document on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createDocumentButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(documentCodeFilter, "Document Code");
        formLayout.addFormItem(documentNameFilter, "Document Name");
        formLayout.addFormItem(categoryFilter, "Category");
        formLayout.addFormItem(countryFilter, "Country");
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

    private Grid<IdentityDocument> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("identity-documents");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(IdentityDocument::getDocumentCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Document Code")
                .setSortable(true);
        grid.addColumn(IdentityDocument::getDocumentName)
                .setAutoWidth(true)
                .setHeader("Document Name")
                .setSortable(true);
        grid.addColumn(IdentityDocument::getCategoryName)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(IdentityDocument::getCountryId)
                .setAutoWidth(true)
                .setHeader("Country ID")
                .setSortable(true);
        grid.addColumn(IdentityDocument::getDescription)
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
        dataProvider = DataProvider.ofCollection(getMockIdentityDocuments());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(IdentityDocument document) {
        Icon icon;
        if (document.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(IdentityDocument document) {
        return new Span(UIUtils.formatDate(document.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(IdentityDocument document) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(document));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteDocument(document));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(IdentityDocument document) {
        IdentityDocumentDetails documentDetails = new IdentityDocumentDetails(document);
        documentDetails.open();
    }

    private void deleteDocument(IdentityDocument document) {
        // This would be implemented to delete the document
        System.out.println("[DEBUG_LOG] Delete identity document: " + document.getDocumentCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply document code filter if not empty
        if (documentCodeFilter.getValue() != null && !documentCodeFilter.getValue().isEmpty()) {
            String documentCodeFilterValue = documentCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(document -> 
                document.getDocumentCode() != null &&
                document.getDocumentCode().toLowerCase().contains(documentCodeFilterValue));
        }

        // Apply document name filter if not empty
        if (documentNameFilter.getValue() != null && !documentNameFilter.getValue().isEmpty()) {
            String documentNameFilterValue = documentNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(document -> 
                document.getDocumentName() != null &&
                document.getDocumentName().toLowerCase().contains(documentNameFilterValue));
        }

        // Apply category filter if not empty
        if (categoryFilter.getValue() != null && !categoryFilter.getValue().isEmpty()) {
            String categoryFilterValue = categoryFilter.getValue().toLowerCase();
            dataProvider.addFilter(document -> 
                document.getCategoryName() != null &&
                document.getCategoryName().toLowerCase().contains(categoryFilterValue));
        }

        // Apply country filter if not empty
        if (countryFilter.getValue() != null && !countryFilter.getValue().isEmpty()) {
            try {
                Long countryIdFilterValue = Long.parseLong(countryFilter.getValue());
                dataProvider.addFilter(document -> 
                    document.getCountryId() != null &&
                    document.getCountryId().equals(countryIdFilterValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(document -> 
                document.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(document -> 
                document.getDateCreated() != null && 
                !document.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        documentCodeFilter.clear();
        documentNameFilter.clear();
        categoryFilter.clear();
        countryFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateDocumentDialog() {
        // This would be implemented to open a dialog for creating a new identity document
        System.out.println("[DEBUG_LOG] Create identity document dialog would open here");
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
        List<Grid.Column<IdentityDocument>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<IdentityDocument> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<IdentityDocument> getMockIdentityDocuments() {
        List<IdentityDocument> documents = new ArrayList<>();

        documents.add(new IdentityDocument(1L, "ID-001", "National ID Card", 1L, "National ID", 1L, "Standard national identification card", "^[0-9]{8}[A-Z]$", "8 digits followed by 1 letter", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        documents.add(new IdentityDocument(2L, "ID-002", "Passport", 2L, "Passport", 1L, "International passport", "^[A-Z]{2}[0-9]{6}$", "2 letters followed by 6 digits", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        documents.add(new IdentityDocument(3L, "ID-003", "Driver's License", 3L, "Driver License", 1L, "Driver's license with photo ID", "^[0-9]{9}$", "9 digits", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        documents.add(new IdentityDocument(4L, "ID-004", "Social Security Card", 4L, "Social Security", 1L, "Social security identification", "^[0-9]{3}-[0-9]{2}-[0-9]{4}$", "Format: XXX-XX-XXXX", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        documents.add(new IdentityDocument(5L, "ID-005", "Birth Certificate", 5L, "Birth Certificate", 1L, "Official birth certificate", "^BC-[0-9]{8}$", "BC- followed by 8 digits", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        documents.add(new IdentityDocument(6L, "ID-006", "Military ID", 6L, "Military ID", 1L, "Military identification card", "^MIL-[0-9]{10}$", "MIL- followed by 10 digits", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        documents.add(new IdentityDocument(7L, "ID-007", "Residence Permit", 7L, "Residence Permit", 2L, "Foreign resident permit", "^RP-[0-9]{8}-[A-Z]{2}$", "RP- followed by 8 digits and 2 letters", Status.INACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        documents.add(new IdentityDocument(8L, "ID-008", "Health Insurance Card", 8L, "Health Insurance", 1L, "National health insurance card", "^HI-[0-9]{12}$", "HI- followed by 12 digits", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        documents.add(new IdentityDocument(9L, "ID-009", "Student ID", 9L, "Student ID", 1L, "University/college student ID", "^STU-[0-9]{8}$", "STU- followed by 8 digits", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        documents.add(new IdentityDocument(10L, "ID-010", "Work Permit", 10L, "Work Permit", 2L, "Foreign worker permit", "^WP-[0-9]{6}-[A-Z]{3}$", "WP- followed by 6 digits and 3 letters", Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return documents;
    }

    // IdentityDocument model class
    public static class IdentityDocument {
        private Long documentId;
        private String documentCode;
        private String documentName;
        private Long categoryId;
        private String categoryName;
        private Long countryId;
        private String description;
        private String validationRegex;
        private String formatDescription;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public IdentityDocument(Long documentId, String documentCode, String documentName, Long categoryId,
                      String categoryName, Long countryId, String description, String validationRegex,
                      String formatDescription, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.documentId = documentId;
            this.documentCode = documentCode;
            this.documentName = documentName;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.countryId = countryId;
            this.description = description;
            this.validationRegex = validationRegex;
            this.formatDescription = formatDescription;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getDocumentId() {
            return documentId;
        }

        public String getDocumentCode() {
            return documentCode;
        }

        public String getDocumentName() {
            return documentName;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public Long getCountryId() {
            return countryId;
        }

        public String getDescription() {
            return description;
        }

        public String getValidationRegex() {
            return validationRegex;
        }

        public String getFormatDescription() {
            return formatDescription;
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
