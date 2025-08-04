package com.vaadin.starter.business.ui.views.masterdata.administrative;
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

@Route(value = "administrative-divisions", layout = MainLayout.class)
@PageTitle("Administrative Divisions")
public class AdministrativeDivisions extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<AdministrativeDivision> grid;
    private Registration resizeListener;
    private ListDataProvider<AdministrativeDivision> dataProvider;
    private UI ui;

    // Search form fields
    private TextField codeFilter;
    private TextField nameFilter;
    private TextField levelFilter;
    private ComboBox<Long> countryIdFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public AdministrativeDivisions() {
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

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

        levelFilter = new TextField();
        levelFilter.setValueChangeMode(ValueChangeMode.EAGER);
        levelFilter.setClearButtonVisible(true);

        countryIdFilter = new ComboBox<>();
        countryIdFilter.setItems(1L, 2L, 3L, 4L, 5L); // Mock country IDs
        countryIdFilter.setClearButtonVisible(true);

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

        Button createDivisionButton = UIUtils.createSuccessButton("Create Division");
        createDivisionButton.addClickListener(e -> openCreateDivisionDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Division on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createDivisionButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(codeFilter, "Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(levelFilter, "Level");
        formLayout.addFormItem(countryIdFilter, "Country");
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

    private Grid<AdministrativeDivision> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("administrative-divisions");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(AdministrativeDivision::getCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Code")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getLevel)
                .setAutoWidth(true)
                .setHeader("Level")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getCountryId)
                .setAutoWidth(true)
                .setHeader("Country ID")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getParentDivisionId)
                .setAutoWidth(true)
                .setHeader("Parent Division ID")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getPostalCodePattern)
                .setAutoWidth(true)
                .setHeader("Postal Code Pattern")
                .setSortable(true);
        grid.addColumn(AdministrativeDivision::getTimeZone)
                .setAutoWidth(true)
                .setHeader("Time Zone")
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
        dataProvider = DataProvider.ofCollection(getMockAdministrativeDivisions());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(AdministrativeDivision division) {
        Icon icon;
        if (division.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(AdministrativeDivision division) {
        return new Span(UIUtils.formatDate(division.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(AdministrativeDivision division) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(division));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteDivision(division));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(AdministrativeDivision division) {
        AdministrativeDivisionDetails divisionDetails = new AdministrativeDivisionDetails(division);
        divisionDetails.open();
    }

    private void deleteDivision(AdministrativeDivision division) {
        // This would be implemented to delete the division
        System.out.println("[DEBUG_LOG] Delete division: " + division.getCode());
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
            dataProvider.addFilter(division -> 
                division.getCode() != null &&
                division.getCode().toLowerCase().contains(codeFilterValue));
        }

        // Apply name filter if not empty
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(division -> 
                division.getName() != null &&
                division.getName().toLowerCase().contains(nameFilterValue));
        }

        // Apply level filter if not empty
        if (levelFilter.getValue() != null && !levelFilter.getValue().isEmpty()) {
            String levelFilterValue = levelFilter.getValue().toLowerCase();
            dataProvider.addFilter(division -> 
                division.getLevel() != null &&
                division.getLevel().toLowerCase().contains(levelFilterValue));
        }

        // Apply country ID filter if selected
        if (countryIdFilter.getValue() != null) {
            Long countryId = countryIdFilter.getValue();
            dataProvider.addFilter(division -> 
                division.getCountryId() != null && division.getCountryId().equals(countryId));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(division -> 
                division.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(division -> 
                division.getDateCreated() != null && 
                !division.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        codeFilter.clear();
        nameFilter.clear();
        levelFilter.clear();
        countryIdFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateDivisionDialog() {
        // This would be implemented to open a dialog for creating a new division
        System.out.println("[DEBUG_LOG] Create division dialog would open here");
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
        List<Grid.Column<AdministrativeDivision>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<AdministrativeDivision> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<AdministrativeDivision> getMockAdministrativeDivisions() {
        List<AdministrativeDivision> divisions = new ArrayList<>();

        divisions.add(new AdministrativeDivision(1L, 1L, "CA", "California", "State", null, Status.ACTIVE, "\\d{5}(-\\d{4})?", "America/Los_Angeles", LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        divisions.add(new AdministrativeDivision(2L, 1L, "NY", "New York", "State", null, Status.ACTIVE, "\\d{5}(-\\d{4})?", "America/New_York", LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        divisions.add(new AdministrativeDivision(3L, 1L, "TX", "Texas", "State", null, Status.ACTIVE, "\\d{5}(-\\d{4})?", "America/Chicago", LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        divisions.add(new AdministrativeDivision(4L, 2L, "ON", "Ontario", "Province", null, Status.ACTIVE, "[A-Z]\\d[A-Z] \\d[A-Z]\\d", "America/Toronto", LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        divisions.add(new AdministrativeDivision(5L, 2L, "BC", "British Columbia", "Province", null, Status.ACTIVE, "[A-Z]\\d[A-Z] \\d[A-Z]\\d", "America/Vancouver", LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        divisions.add(new AdministrativeDivision(6L, 3L, "LDN", "London", "City", 10L, Status.ACTIVE, "[A-Z]{1,2}\\d[A-Z\\d]? \\d[A-Z]{2}", "Europe/London", LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        divisions.add(new AdministrativeDivision(7L, 4L, "TYO", "Tokyo", "Prefecture", null, Status.ACTIVE, "\\d{3}-\\d{4}", "Asia/Tokyo", LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        divisions.add(new AdministrativeDivision(8L, 5L, "BJ", "Beijing", "Municipality", null, Status.ACTIVE, "\\d{6}", "Asia/Shanghai", LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        divisions.add(new AdministrativeDivision(9L, 6L, "SP", "São Paulo", "State", null, Status.INACTIVE, "\\d{5}-\\d{3}", "America/Sao_Paulo", LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        divisions.add(new AdministrativeDivision(10L, 7L, "NSW", "New South Wales", "State", null, Status.ACTIVE, "\\d{4}", "Australia/Sydney", LocalDateTime.now(), LocalDateTime.now()));

        return divisions;
    }

    // AdministrativeDivision model class
    public static class AdministrativeDivision {
        private Long divisionId;
        private Long countryId;
        private String code;
        private String name;
        private String level;
        private Long parentDivisionId;
        private Status status;
        private String postalCodePattern;
        private String timeZone;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public AdministrativeDivision(Long divisionId, Long countryId, String code, String name, 
                      String level, Long parentDivisionId, Status status, 
                      String postalCodePattern, String timeZone,
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.divisionId = divisionId;
            this.countryId = countryId;
            this.code = code;
            this.name = name;
            this.level = level;
            this.parentDivisionId = parentDivisionId;
            this.status = status;
            this.postalCodePattern = postalCodePattern;
            this.timeZone = timeZone;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getDivisionId() {
            return divisionId;
        }

        public Long getCountryId() {
            return countryId;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }

        public Long getParentDivisionId() {
            return parentDivisionId;
        }

        public Status getStatus() {
            return status;
        }

        public boolean isActive() {
            return status == Status.ACTIVE;
        }

        public String getPostalCodePattern() {
            return postalCodePattern;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}