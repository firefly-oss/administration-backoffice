package com.vaadin.starter.business.ui.views.masterdata.currency;

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

@Route(value = "currencies", layout = MainLayout.class)
@PageTitle("Currencies")
public class Currencies extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<Currency> grid;
    private Registration resizeListener;
    private ListDataProvider<Currency> dataProvider;
    private UI ui;

    // Search form fields
    private TextField isoCodeFilter;
    private TextField currencyNameFilter;
    private TextField symbolFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public Currencies() {
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
        isoCodeFilter = new TextField();
        isoCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        isoCodeFilter.setClearButtonVisible(true);

        currencyNameFilter = new TextField();
        currencyNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        currencyNameFilter.setClearButtonVisible(true);

        symbolFilter = new TextField();
        symbolFilter.setValueChangeMode(ValueChangeMode.EAGER);
        symbolFilter.setClearButtonVisible(true);

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

        Button createCurrencyButton = UIUtils.createSuccessButton("Create Currency");
        createCurrencyButton.addClickListener(e -> openCreateCurrencyDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Currency on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createCurrencyButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(isoCodeFilter, "ISO Code");
        formLayout.addFormItem(currencyNameFilter, "Currency Name");
        formLayout.addFormItem(symbolFilter, "Symbol");
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

    private Grid<Currency> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("currencies");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(Currency::getIsoCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ISO Code")
                .setSortable(true);
        grid.addColumn(Currency::getCurrencyName)
                .setAutoWidth(true)
                .setHeader("Currency Name")
                .setSortable(true);
        grid.addColumn(Currency::getSymbol)
                .setAutoWidth(true)
                .setHeader("Symbol")
                .setSortable(true);
        grid.addColumn(Currency::getDecimalPrecision)
                .setAutoWidth(true)
                .setHeader("Decimal Precision")
                .setSortable(true);
        grid.addColumn(Currency::getIsMajor)
                .setAutoWidth(true)
                .setHeader("Is Major")
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
        dataProvider = DataProvider.ofCollection(getMockCurrencies());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(Currency currency) {
        Icon icon;
        if (currency.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Currency currency) {
        return new Span(UIUtils.formatDate(currency.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(Currency currency) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(currency));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteCurrency(currency));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(Currency currency) {
        CurrencyDetails currencyDetails = new CurrencyDetails(currency);
        currencyDetails.open();
    }

    private void deleteCurrency(Currency currency) {
        // This would be implemented to delete the currency
        System.out.println("[DEBUG_LOG] Delete currency: " + currency.getIsoCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply ISO code filter if not empty
        if (isoCodeFilter.getValue() != null && !isoCodeFilter.getValue().isEmpty()) {
            String isoCodeFilterValue = isoCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(currency -> 
                currency.getIsoCode() != null &&
                currency.getIsoCode().toLowerCase().contains(isoCodeFilterValue));
        }

        // Apply currency name filter if not empty
        if (currencyNameFilter.getValue() != null && !currencyNameFilter.getValue().isEmpty()) {
            String currencyNameFilterValue = currencyNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(currency -> 
                currency.getCurrencyName() != null &&
                currency.getCurrencyName().toLowerCase().contains(currencyNameFilterValue));
        }

        // Apply symbol filter if not empty
        if (symbolFilter.getValue() != null && !symbolFilter.getValue().isEmpty()) {
            String symbolFilterValue = symbolFilter.getValue().toLowerCase();
            dataProvider.addFilter(currency -> 
                currency.getSymbol() != null &&
                currency.getSymbol().toLowerCase().contains(symbolFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(currency -> 
                currency.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(currency -> 
                currency.getDateCreated() != null && 
                !currency.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        isoCodeFilter.clear();
        currencyNameFilter.clear();
        symbolFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateCurrencyDialog() {
        // This would be implemented to open a dialog for creating a new currency
        System.out.println("[DEBUG_LOG] Create currency dialog would open here");
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
        List<Grid.Column<Currency>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<Currency> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<Currency> getMockCurrencies() {
        List<Currency> currencies = new ArrayList<>();

        currencies.add(new Currency(1L, "USD", "US Dollar", "$", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        currencies.add(new Currency(2L, "EUR", "Euro", "€", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        currencies.add(new Currency(3L, "GBP", "British Pound", "£", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        currencies.add(new Currency(4L, "JPY", "Japanese Yen", "¥", 0, true, Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        currencies.add(new Currency(5L, "CHF", "Swiss Franc", "Fr", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        currencies.add(new Currency(6L, "CAD", "Canadian Dollar", "C$", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        currencies.add(new Currency(7L, "AUD", "Australian Dollar", "A$", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        currencies.add(new Currency(8L, "CNY", "Chinese Yuan", "¥", 2, true, Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        currencies.add(new Currency(9L, "INR", "Indian Rupee", "₹", 2, false, Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        currencies.add(new Currency(10L, "BRL", "Brazilian Real", "R$", 2, false, Status.INACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return currencies;
    }

    // Currency model class
    public static class Currency {
        private Long currencyId;
        private String isoCode;
        private String currencyName;
        private String symbol;
        private Integer decimalPrecision;
        private Boolean isMajor;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public Currency(Long currencyId, String isoCode, String currencyName, String symbol, 
                      Integer decimalPrecision, Boolean isMajor, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.currencyId = currencyId;
            this.isoCode = isoCode;
            this.currencyName = currencyName;
            this.symbol = symbol;
            this.decimalPrecision = decimalPrecision;
            this.isMajor = isMajor;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getCurrencyId() {
            return currencyId;
        }

        public String getIsoCode() {
            return isoCode;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public String getSymbol() {
            return symbol;
        }

        public Integer getDecimalPrecision() {
            return decimalPrecision;
        }

        public Boolean getIsMajor() {
            return isMajor;
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