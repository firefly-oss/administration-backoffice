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

@Route(value = "identity-document-categories", layout = MainLayout.class)
@PageTitle("Identity Document Categories")
public class IdentityDocumentCategories extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<IdentityDocumentCategory> grid;
    private Registration resizeListener;
    private ListDataProvider<IdentityDocumentCategory> dataProvider;
    private UI ui;

    // Search form fields
    private TextField categoryCodeFilter;
    private TextField categoryNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public IdentityDocumentCategories() {
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
        categoryCodeFilter = new TextField();
        categoryCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        categoryCodeFilter.setClearButtonVisible(true);

        categoryNameFilter = new TextField();
        categoryNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        categoryNameFilter.setClearButtonVisible(true);

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

        Button createCategoryButton = UIUtils.createSuccessButton("Create Identity Document Category");
        createCategoryButton.addClickListener(e -> openCreateCategoryDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Identity Document Category on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createCategoryButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(categoryCodeFilter, "Category Code");
        formLayout.addFormItem(categoryNameFilter, "Category Name");
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

    private Grid<IdentityDocumentCategory> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("identity-document-categories");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(IdentityDocumentCategory::getCategoryId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Category ID")
                .setSortable(true);
        grid.addColumn(IdentityDocumentCategory::getCategoryCode)
                .setAutoWidth(true)
                .setHeader("Category Code")
                .setSortable(true);
        grid.addColumn(IdentityDocumentCategory::getCategoryName)
                .setAutoWidth(true)
                .setHeader("Category Name")
                .setSortable(true);
        grid.addColumn(IdentityDocumentCategory::getDescription)
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
        dataProvider = DataProvider.ofCollection(getMockIdentityDocumentCategories());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(IdentityDocumentCategory category) {
        Icon icon;
        if (category.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(IdentityDocumentCategory category) {
        return new Span(UIUtils.formatDate(category.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(IdentityDocumentCategory category) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(category));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteCategory(category));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(IdentityDocumentCategory category) {
        IdentityDocumentCategoryDetails categoryDetails = new IdentityDocumentCategoryDetails(category);
        categoryDetails.open();
    }

    private void deleteCategory(IdentityDocumentCategory category) {
        // This would be implemented to delete the category
        System.out.println("[DEBUG_LOG] Delete identity document category: " + category.getCategoryCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply category code filter if not empty
        if (categoryCodeFilter.getValue() != null && !categoryCodeFilter.getValue().isEmpty()) {
            String categoryCodeFilterValue = categoryCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(category -> 
                category.getCategoryCode() != null &&
                category.getCategoryCode().toLowerCase().contains(categoryCodeFilterValue));
        }

        // Apply category name filter if not empty
        if (categoryNameFilter.getValue() != null && !categoryNameFilter.getValue().isEmpty()) {
            String categoryNameFilterValue = categoryNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(category -> 
                category.getCategoryName() != null &&
                category.getCategoryName().toLowerCase().contains(categoryNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(category -> 
                category.getDescription() != null &&
                category.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(category -> 
                category.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(category -> 
                category.getDateCreated() != null && 
                !category.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        categoryCodeFilter.clear();
        categoryNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateCategoryDialog() {
        // This would be implemented to open a dialog for creating a new identity document category
        System.out.println("[DEBUG_LOG] Create identity document category dialog would open here");
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
        List<Grid.Column<IdentityDocumentCategory>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<IdentityDocumentCategory> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<IdentityDocumentCategory> getMockIdentityDocumentCategories() {
        List<IdentityDocumentCategory> categories = new ArrayList<>();

        categories.add(new IdentityDocumentCategory(1L, "CAT-001", "National ID", "National identification documents", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        categories.add(new IdentityDocumentCategory(2L, "CAT-002", "Passport", "International travel documents", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        categories.add(new IdentityDocumentCategory(3L, "CAT-003", "Driver License", "Driving permits and licenses", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        categories.add(new IdentityDocumentCategory(4L, "CAT-004", "Social Security", "Social security identification", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        categories.add(new IdentityDocumentCategory(5L, "CAT-005", "Birth Certificate", "Birth registration documents", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        categories.add(new IdentityDocumentCategory(6L, "CAT-006", "Military ID", "Military identification cards", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        categories.add(new IdentityDocumentCategory(7L, "CAT-007", "Residence Permit", "Residence authorization documents", Status.INACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        categories.add(new IdentityDocumentCategory(8L, "CAT-008", "Health Insurance", "Health insurance cards", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        categories.add(new IdentityDocumentCategory(9L, "CAT-009", "Student ID", "Educational institution IDs", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        categories.add(new IdentityDocumentCategory(10L, "CAT-010", "Work Permit", "Work authorization documents", Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return categories;
    }

    // IdentityDocumentCategory model class
    public static class IdentityDocumentCategory {
        private Long categoryId;
        private String categoryCode;
        private String categoryName;
        private String description;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public IdentityDocumentCategory(Long categoryId, String categoryCode, String categoryName,
                      String description, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.categoryId = categoryId;
            this.categoryCode = categoryCode;
            this.categoryName = categoryName;
            this.description = description;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public String getCategoryName() {
            return categoryName;
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