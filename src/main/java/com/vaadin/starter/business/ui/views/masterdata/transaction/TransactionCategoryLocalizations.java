package com.vaadin.starter.business.ui.views.masterdata.transaction;

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

@Route(value = "transaction-category-localizations", layout = MainLayout.class)
@PageTitle("Transaction Category Localizations")
public class TransactionCategoryLocalizations extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<TransactionCategoryLocalization> grid;
    private Registration resizeListener;
    private ListDataProvider<TransactionCategoryLocalization> dataProvider;
    private UI ui;

    // Search form fields
    private TextField categoryIdFilter;
    private TextField localeIdFilter;
    private TextField categoryNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public TransactionCategoryLocalizations() {
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
        categoryIdFilter = new TextField();
        categoryIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        categoryIdFilter.setClearButtonVisible(true);

        localeIdFilter = new TextField();
        localeIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        localeIdFilter.setClearButtonVisible(true);

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

        Button createLocalizationButton = UIUtils.createSuccessButton("Create Category Localization");
        createLocalizationButton.addClickListener(e -> openCreateLocalizationDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Category Localization on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLocalizationButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(categoryIdFilter, "Category ID");
        formLayout.addFormItem(localeIdFilter, "Locale ID");
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

    private Grid<TransactionCategoryLocalization> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("transaction-category-localizations");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(TransactionCategoryLocalization::getLocalizationId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Localization ID")
                .setSortable(true);
        grid.addColumn(TransactionCategoryLocalization::getCategoryId)
                .setAutoWidth(true)
                .setHeader("Category ID")
                .setSortable(true);
        grid.addColumn(TransactionCategoryLocalization::getLocaleId)
                .setAutoWidth(true)
                .setHeader("Locale ID")
                .setSortable(true);
        grid.addColumn(TransactionCategoryLocalization::getCategoryName)
                .setAutoWidth(true)
                .setHeader("Category Name")
                .setSortable(true);
        grid.addColumn(TransactionCategoryLocalization::getDescription)
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
        dataProvider = DataProvider.ofCollection(getMockTransactionCategoryLocalizations());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(TransactionCategoryLocalization localization) {
        Icon icon;
        if (localization.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(TransactionCategoryLocalization localization) {
        return new Span(UIUtils.formatDate(localization.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(TransactionCategoryLocalization localization) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(localization));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteLocalization(localization));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(TransactionCategoryLocalization localization) {
        TransactionCategoryLocalizationDetails localizationDetails = new TransactionCategoryLocalizationDetails(localization);
        localizationDetails.open();
    }

    private void deleteLocalization(TransactionCategoryLocalization localization) {
        // This would be implemented to delete the localization
        System.out.println("[DEBUG_LOG] Delete transaction category localization: " + localization.getLocalizationId());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply category ID filter if not empty
        if (categoryIdFilter.getValue() != null && !categoryIdFilter.getValue().isEmpty()) {
            try {
                Long categoryIdFilterValue = Long.parseLong(categoryIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getCategoryId() != null &&
                    localization.getCategoryId().equals(categoryIdFilterValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply locale ID filter if not empty
        if (localeIdFilter.getValue() != null && !localeIdFilter.getValue().isEmpty()) {
            try {
                Long localeIdFilterValue = Long.parseLong(localeIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getLocaleId() != null &&
                    localization.getLocaleId().equals(localeIdFilterValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply category name filter if not empty
        if (categoryNameFilter.getValue() != null && !categoryNameFilter.getValue().isEmpty()) {
            String categoryNameFilterValue = categoryNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getCategoryName() != null &&
                localization.getCategoryName().toLowerCase().contains(categoryNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getDescription() != null &&
                localization.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(localization -> 
                localization.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(localization -> 
                localization.getDateCreated() != null && 
                !localization.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        categoryIdFilter.clear();
        localeIdFilter.clear();
        categoryNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLocalizationDialog() {
        // This would be implemented to open a dialog for creating a new transaction category localization
        TransactionCategoryLocalization newLocalization = new TransactionCategoryLocalization();
        newLocalization.setStatus(Status.ACTIVE);
        TransactionCategoryLocalizationDetails localizationDetails = new TransactionCategoryLocalizationDetails(newLocalization);
        localizationDetails.open();
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
        List<Grid.Column<TransactionCategoryLocalization>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<TransactionCategoryLocalization> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<TransactionCategoryLocalization> getMockTransactionCategoryLocalizations() {
        List<TransactionCategoryLocalization> localizations = new ArrayList<>();

        localizations.add(new TransactionCategoryLocalization(1L, 1L, 1L, "Income - English", "Money received", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        localizations.add(new TransactionCategoryLocalization(2L, 1L, 2L, "Ingresos - Spanish", "Dinero recibido", Status.ACTIVE, LocalDateTime.now().minusDays(29), LocalDateTime.now().minusDays(4)));
        localizations.add(new TransactionCategoryLocalization(3L, 2L, 1L, "Expense - English", "Money spent", Status.ACTIVE, LocalDateTime.now().minusDays(28), LocalDateTime.now().minusDays(3)));
        localizations.add(new TransactionCategoryLocalization(4L, 2L, 2L, "Gastos - Spanish", "Dinero gastado", Status.ACTIVE, LocalDateTime.now().minusDays(27), LocalDateTime.now().minusDays(2)));
        localizations.add(new TransactionCategoryLocalization(5L, 3L, 1L, "Transfer - English", "Money moved between accounts", Status.ACTIVE, LocalDateTime.now().minusDays(26), LocalDateTime.now().minusDays(1)));
        localizations.add(new TransactionCategoryLocalization(6L, 3L, 2L, "Transferencia - Spanish", "Dinero movido entre cuentas", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now()));
        localizations.add(new TransactionCategoryLocalization(7L, 4L, 1L, "Salary - English", "Regular income from employment", Status.ACTIVE, LocalDateTime.now().minusDays(24), LocalDateTime.now()));
        localizations.add(new TransactionCategoryLocalization(8L, 4L, 2L, "Salario - Spanish", "Ingreso regular por empleo", Status.INACTIVE, LocalDateTime.now().minusDays(23), LocalDateTime.now()));
        localizations.add(new TransactionCategoryLocalization(9L, 5L, 1L, "Interest - English", "Income from investments", Status.ACTIVE, LocalDateTime.now().minusDays(22), LocalDateTime.now()));
        localizations.add(new TransactionCategoryLocalization(10L, 5L, 2L, "Interés - Spanish", "Ingresos por inversiones", Status.INACTIVE, LocalDateTime.now().minusDays(21), LocalDateTime.now()));

        return localizations;
    }

    // TransactionCategoryLocalization model class
    public static class TransactionCategoryLocalization {
        private Long localizationId;
        private Long categoryId;
        private Long localeId;
        private String categoryName;
        private String description;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public TransactionCategoryLocalization() {
        }

        public TransactionCategoryLocalization(Long localizationId, Long categoryId, Long localeId, 
                      String categoryName, String description, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.localizationId = localizationId;
            this.categoryId = categoryId;
            this.localeId = localeId;
            this.categoryName = categoryName;
            this.description = description;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getLocalizationId() {
            return localizationId;
        }

        public void setLocalizationId(Long localizationId) {
            this.localizationId = localizationId;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public Long getLocaleId() {
            return localeId;
        }

        public void setLocaleId(Long localeId) {
            this.localeId = localeId;
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
    }
}