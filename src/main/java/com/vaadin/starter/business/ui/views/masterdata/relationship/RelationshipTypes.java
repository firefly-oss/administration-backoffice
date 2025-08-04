package com.vaadin.starter.business.ui.views.masterdata.relationship;
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

@Route(value = "relationship-types", layout = MainLayout.class)
@PageTitle("Relationship Types")
public class RelationshipTypes extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<RelationshipType> grid;
    private Registration resizeListener;
    private ListDataProvider<RelationshipType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField codeFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public RelationshipTypes() {
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
        codeFilter = new TextField();
        codeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        codeFilter.setClearButtonVisible(true);

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

        Button createRelationshipTypeButton = UIUtils.createSuccessButton("Create Relationship Type");
        createRelationshipTypeButton.addClickListener(e -> openCreateRelationshipTypeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Relationship Type on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createRelationshipTypeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(codeFilter, "Code");
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

    private Grid<RelationshipType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("relationship-types");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(RelationshipType::getCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Code")
                .setSortable(true);
        grid.addColumn(RelationshipType::getDescription)
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
        dataProvider = DataProvider.ofCollection(getMockRelationshipTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(RelationshipType relationshipType) {
        Icon icon;
        if (relationshipType.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(RelationshipType relationshipType) {
        return new Span(UIUtils.formatDate(relationshipType.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(RelationshipType relationshipType) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(relationshipType));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteRelationshipType(relationshipType));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(RelationshipType relationshipType) {
        RelationshipTypeDetails relationshipTypeDetails = new RelationshipTypeDetails(relationshipType);
        relationshipTypeDetails.open();
    }

    private void deleteRelationshipType(RelationshipType relationshipType) {
        // This would be implemented to delete the relationship type
        System.out.println("[DEBUG_LOG] Delete relationship type: " + relationshipType.getCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply code filter if not empty
        if (codeFilter.getValue() != null && !codeFilter.getValue().isEmpty()) {
            String codeFilterValue = codeFilter.getValue().toLowerCase();
            dataProvider.addFilter(relationshipType -> 
                relationshipType.getCode() != null &&
                relationshipType.getCode().toLowerCase().contains(codeFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(relationshipType -> 
                relationshipType.getDescription() != null &&
                relationshipType.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(relationshipType -> 
                relationshipType.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(relationshipType -> 
                relationshipType.getDateCreated() != null && 
                !relationshipType.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        codeFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateRelationshipTypeDialog() {
        // This would be implemented to open a dialog for creating a new relationship type
        System.out.println("[DEBUG_LOG] Create relationship type dialog would open here");
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
        List<Grid.Column<RelationshipType>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<RelationshipType> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<RelationshipType> getMockRelationshipTypes() {
        List<RelationshipType> relationshipTypes = new ArrayList<>();

        relationshipTypes.add(new RelationshipType(1L, "PARENT", "Parent-Child Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        relationshipTypes.add(new RelationshipType(2L, "SPOUSE", "Spousal Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        relationshipTypes.add(new RelationshipType(3L, "SIBLING", "Sibling Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        relationshipTypes.add(new RelationshipType(4L, "GUARDIAN", "Guardian Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        relationshipTypes.add(new RelationshipType(5L, "BUSINESS", "Business Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        relationshipTypes.add(new RelationshipType(6L, "PARTNER", "Business Partner Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        relationshipTypes.add(new RelationshipType(7L, "AGENT", "Agent Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        relationshipTypes.add(new RelationshipType(8L, "EMPLOYEE", "Employee Relationship", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        relationshipTypes.add(new RelationshipType(9L, "CUSTOMER", "Customer Relationship", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        relationshipTypes.add(new RelationshipType(10L, "SUPPLIER", "Supplier Relationship", Status.INACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return relationshipTypes;
    }

    // RelationshipType model class
    public static class RelationshipType {
        private Long relationshipTypeId;
        private String code;
        private String description;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public RelationshipType(Long relationshipTypeId, String code, String description, 
                      Status status, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.relationshipTypeId = relationshipTypeId;
            this.code = code;
            this.description = description;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getRelationshipTypeId() {
            return relationshipTypeId;
        }

        public String getCode() {
            return code;
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
