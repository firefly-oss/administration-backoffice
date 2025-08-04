package com.vaadin.starter.business.ui.views.masterdata.country;

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

@Route(value = "countries", layout = MainLayout.class)
@PageTitle("Countries")
public class Countries extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<Country> grid;
    private Registration resizeListener;
    private ListDataProvider<Country> dataProvider;
    private UI ui;

    // Search form fields
    private TextField isoCodeFilter;
    private TextField countryNameFilter;
    private TextField regionLkpIdFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public Countries() {
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

        countryNameFilter = new TextField();
        countryNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        countryNameFilter.setClearButtonVisible(true);

        regionLkpIdFilter = new TextField();
        regionLkpIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        regionLkpIdFilter.setClearButtonVisible(true);

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

        Button createCountryButton = UIUtils.createSuccessButton("Create Country");
        createCountryButton.addClickListener(e -> openCreateCountryDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Country on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createCountryButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(isoCodeFilter, "ISO Code");
        formLayout.addFormItem(countryNameFilter, "Country Name");
        formLayout.addFormItem(regionLkpIdFilter, "Region ID");
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

    private Grid<Country> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("countries");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(Country::getIsoCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ISO Code")
                .setSortable(true);
        grid.addColumn(Country::getCountryName)
                .setAutoWidth(true)
                .setHeader("Country Name")
                .setSortable(true);
        grid.addColumn(Country::getRegionLkpId)
                .setAutoWidth(true)
                .setHeader("Region ID")
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
        dataProvider = DataProvider.ofCollection(getMockCountries());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(Country country) {
        Icon icon;
        if (country.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Country country) {
        return new Span(UIUtils.formatDate(country.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(Country country) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(country));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteCountry(country));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(Country country) {
        CountryDetails countryDetails = new CountryDetails(country);
        countryDetails.open();
    }

    private void deleteCountry(Country country) {
        // This would be implemented to delete the country
        System.out.println("[DEBUG_LOG] Delete country: " + country.getIsoCode());
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
            dataProvider.addFilter(country -> 
                country.getIsoCode() != null &&
                country.getIsoCode().toLowerCase().contains(isoCodeFilterValue));
        }

        // Apply country name filter if not empty
        if (countryNameFilter.getValue() != null && !countryNameFilter.getValue().isEmpty()) {
            String countryNameFilterValue = countryNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(country -> 
                country.getCountryName() != null &&
                country.getCountryName().toLowerCase().contains(countryNameFilterValue));
        }

        // Apply region ID filter if not empty
        if (regionLkpIdFilter.getValue() != null && !regionLkpIdFilter.getValue().isEmpty()) {
            try {
                Long regionId = Long.parseLong(regionLkpIdFilter.getValue());
                dataProvider.addFilter(country -> 
                    country.getRegionLkpId() != null && country.getRegionLkpId().equals(regionId));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(country -> 
                country.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(country -> 
                country.getDateCreated() != null && 
                !country.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        isoCodeFilter.clear();
        countryNameFilter.clear();
        regionLkpIdFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateCountryDialog() {
        // This would be implemented to open a dialog for creating a new country
        System.out.println("[DEBUG_LOG] Create country dialog would open here");
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
        List<Grid.Column<Country>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<Country> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<Country> getMockCountries() {
        List<Country> countries = new ArrayList<>();

        countries.add(new Country(1L, "US", "United States", 1L, true, null, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        countries.add(new Country(2L, "CA", "Canada", 1L, true, null, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        countries.add(new Country(3L, "MX", "Mexico", 1L, false, null, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        countries.add(new Country(4L, "GB", "United Kingdom", 2L, true, null, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        countries.add(new Country(5L, "FR", "France", 2L, true, null, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        countries.add(new Country(6L, "DE", "Germany", 2L, false, null, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        countries.add(new Country(7L, "JP", "Japan", 3L, true, null, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        countries.add(new Country(8L, "CN", "China", 3L, true, null, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        countries.add(new Country(9L, "IN", "India", 3L, false, null, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        countries.add(new Country(10L, "BR", "Brazil", 4L, true, null, LocalDateTime.now(), LocalDateTime.now()));

        return countries;
    }

    // Country model class
    public static class Country {
        private Long countryId;
        private String isoCode;
        private String countryName;
        private Long regionLkpId;
        private Boolean isActive;
        private String svgFlag;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public Country(Long countryId, String isoCode, String countryName, Long regionLkpId, 
                      Boolean isActive, String svgFlag, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.countryId = countryId;
            this.isoCode = isoCode;
            this.countryName = countryName;
            this.regionLkpId = regionLkpId;
            this.isActive = isActive;
            this.svgFlag = svgFlag;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getCountryId() {
            return countryId;
        }

        public String getIsoCode() {
            return isoCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public Long getRegionLkpId() {
            return regionLkpId;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public boolean isActive() {
            return isActive != null && isActive;
        }

        public String getSvgFlag() {
            return svgFlag;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}
