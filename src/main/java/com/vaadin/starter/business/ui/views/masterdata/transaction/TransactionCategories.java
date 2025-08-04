package com.vaadin.starter.business.ui.views.masterdata.transaction;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "transaction-categories", layout = MainLayout.class)
@PageTitle("Transaction Categories")
public class TransactionCategories extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<TransactionCategory> grid;
    private Registration resizeListener;
    private ListDataProvider<TransactionCategory> dataProvider;
    private UI ui;

    // Search form fields
    private TextField categoryCodeFilter;
    private TextField categoryNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<Long> parentCategoryFilter;

    public TransactionCategories() {
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

        parentCategoryFilter = new ComboBox<>();
        // Populate with parent category IDs from mock data
        List<Long> parentIds = new ArrayList<>();
        parentIds.add(1L);
        parentIds.add(2L);
        parentIds.add(3L);
        parentCategoryFilter.setItems(parentIds);
        parentCategoryFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createCategoryButton = UIUtils.createSuccessButton("Create Transaction Category");
        createCategoryButton.addClickListener(e -> openCreateCategoryDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Transaction Category on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createCategoryButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(categoryCodeFilter, "Category Code");
        formLayout.addFormItem(categoryNameFilter, "Category Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(parentCategoryFilter, "Parent Category");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP)
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

    private Grid<TransactionCategory> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("transaction-categories");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(TransactionCategory::getCategoryId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Category ID")
                .setSortable(true);
        grid.addColumn(TransactionCategory::getCategoryCode)
                .setAutoWidth(true)
                .setHeader("Category Code")
                .setSortable(true);
        grid.addColumn(TransactionCategory::getCategoryName)
                .setAutoWidth(true)
                .setHeader("Category Name")
                .setSortable(true);
        grid.addColumn(TransactionCategory::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(TransactionCategory::getParentCategoryId)
                .setAutoWidth(true)
                .setHeader("Parent Category ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createSvgIcon))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("SVG Icon")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
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
        dataProvider = DataProvider.ofCollection(getMockTransactionCategories());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(TransactionCategory category) {
        Icon icon;
        if (category.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createSvgIcon(TransactionCategory category) {
        if (category.getSvgIcon() != null && !category.getSvgIcon().isEmpty()) {
            return new Span(category.getSvgIcon());
        } else {
            return new Span("No Icon");
        }
    }

    private Component createDate(TransactionCategory category) {
        return new Span(UIUtils.formatDate(category.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(TransactionCategory category) {
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

    private void showDetails(TransactionCategory category) {
        TransactionCategoryDetails categoryDetails = new TransactionCategoryDetails(category);
        categoryDetails.open();
    }

    private void deleteCategory(TransactionCategory category) {
        // This would be implemented to delete the category
        System.out.println("[DEBUG_LOG] Delete transaction category: " + category.getCategoryCode());
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

        // Apply parent category filter if selected
        if (parentCategoryFilter.getValue() != null) {
            Long parentCategoryId = parentCategoryFilter.getValue();
            dataProvider.addFilter(category -> 
                category.getParentCategoryId() != null && 
                category.getParentCategoryId().equals(parentCategoryId));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        categoryCodeFilter.clear();
        categoryNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        parentCategoryFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateCategoryDialog() {
        // This would be implemented to open a dialog for creating a new transaction category
        TransactionCategory newCategory = new TransactionCategory();
        newCategory.setStatus(Status.ACTIVE);
        TransactionCategoryDetails categoryDetails = new TransactionCategoryDetails(newCategory);
        categoryDetails.open();
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
        List<Grid.Column<TransactionCategory>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<TransactionCategory> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<TransactionCategory> getMockTransactionCategories() {
        List<TransactionCategory> categories = new ArrayList<>();

        categories.add(new TransactionCategory(1L, "INCOME", "Income", "Money received", null, Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5), "<svg>income</svg>"));
        categories.add(new TransactionCategory(2L, "EXPENSE", "Expense", "Money spent", null, Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4), "<svg>expense</svg>"));
        categories.add(new TransactionCategory(3L, "TRANSFER", "Transfer", "Money moved between accounts", null, Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3), "<svg>transfer</svg>"));
        categories.add(new TransactionCategory(4L, "SALARY", "Salary", "Regular income from employment", 1L, Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2), "<svg>salary</svg>"));
        categories.add(new TransactionCategory(5L, "INTEREST", "Interest", "Income from investments", 1L, Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1), "<svg>interest</svg>"));
        categories.add(new TransactionCategory(6L, "RENT", "Rent", "Housing expenses", 2L, Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now(), "<svg>rent</svg>"));
        categories.add(new TransactionCategory(7L, "UTILITIES", "Utilities", "Regular household bills", 2L, Status.INACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now(), "<svg>utilities</svg>"));
        categories.add(new TransactionCategory(8L, "GROCERIES", "Groceries", "Food and household supplies", 2L, Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now(), "<svg>groceries</svg>"));
        categories.add(new TransactionCategory(9L, "ENTERTAINMENT", "Entertainment", "Leisure activities", 2L, Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now(), "<svg>entertainment</svg>"));
        categories.add(new TransactionCategory(10L, "SAVINGS", "Savings", "Money set aside", 3L, Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), "<svg>savings</svg>"));

        return categories;
    }

    // TransactionCategory model class
    public static class TransactionCategory {
        private Long categoryId;
        private String categoryCode;
        private String categoryName;
        private String description;
        private Long parentCategoryId;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;
        private String svgIcon;

        public TransactionCategory() {
        }

        public TransactionCategory(Long categoryId, String categoryCode, String categoryName,
                      String description, Long parentCategoryId, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated, String svgIcon) {
            this.categoryId = categoryId;
            this.categoryCode = categoryCode;
            this.categoryName = categoryName;
            this.description = description;
            this.parentCategoryId = parentCategoryId;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
            this.svgIcon = svgIcon;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(Long parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public boolean isActive() {
            return status == Status.ACTIVE;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(LocalDateTime dateCreated) {
            this.dateCreated = dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }

        public void setDateUpdated(LocalDateTime dateUpdated) {
            this.dateUpdated = dateUpdated;
        }

        public String getSvgIcon() {
            return svgIcon;
        }

        public void setSvgIcon(String svgIcon) {
            this.svgIcon = svgIcon;
        }
    }
}