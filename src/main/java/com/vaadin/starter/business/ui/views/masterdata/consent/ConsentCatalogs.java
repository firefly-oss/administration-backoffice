package com.vaadin.starter.business.ui.views.masterdata.consent;

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
import com.vaadin.flow.component.textfield.IntegerField;
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

@Route(value = "consent-catalogs", layout = MainLayout.class)
@PageTitle("Consent Catalogs")
public class ConsentCatalogs extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ConsentCatalog> grid;
    private Registration resizeListener;
    private ListDataProvider<ConsentCatalog> dataProvider;
    private UI ui;

    // Search form fields
    private TextField consentTypeFilter;
    private TextField consentDescriptionFilter;
    private TextField consentVersionFilter;
    private TextField consentSourceFilter;
    private IntegerField expiryPeriodDaysFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public ConsentCatalogs() {
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
        consentTypeFilter = new TextField();
        consentTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        consentTypeFilter.setClearButtonVisible(true);

        consentDescriptionFilter = new TextField();
        consentDescriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        consentDescriptionFilter.setClearButtonVisible(true);

        consentVersionFilter = new TextField();
        consentVersionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        consentVersionFilter.setClearButtonVisible(true);

        consentSourceFilter = new TextField();
        consentSourceFilter.setValueChangeMode(ValueChangeMode.EAGER);
        consentSourceFilter.setClearButtonVisible(true);

        expiryPeriodDaysFilter = new IntegerField();
        expiryPeriodDaysFilter.setClearButtonVisible(true);

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

        Button createConsentCatalogButton = UIUtils.createSuccessButton("Create Consent Catalog");
        createConsentCatalogButton.addClickListener(e -> openCreateConsentCatalogDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Consent Catalog on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createConsentCatalogButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(consentTypeFilter, "Consent Type");
        formLayout.addFormItem(consentDescriptionFilter, "Description");
        formLayout.addFormItem(consentVersionFilter, "Version");
        formLayout.addFormItem(consentSourceFilter, "Source");
        formLayout.addFormItem(expiryPeriodDaysFilter, "Expiry Period (Days)");
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

    private Grid<ConsentCatalog> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("consent-catalogs");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(ConsentCatalog::getConsentType)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Consent Type")
                .setSortable(true);
        grid.addColumn(ConsentCatalog::getConsentDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(ConsentCatalog::getConsentVersion)
                .setAutoWidth(true)
                .setHeader("Version")
                .setSortable(true);
        grid.addColumn(ConsentCatalog::getConsentSource)
                .setAutoWidth(true)
                .setHeader("Source")
                .setSortable(true);
        grid.addColumn(ConsentCatalog::getExpiryPeriodDays)
                .setAutoWidth(true)
                .setHeader("Expiry Period (Days)")
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
        dataProvider = DataProvider.ofCollection(getMockConsentCatalogs());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(ConsentCatalog consentCatalog) {
        Icon icon;
        if (consentCatalog.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(ConsentCatalog consentCatalog) {
        return new Span(UIUtils.formatDate(consentCatalog.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(ConsentCatalog consentCatalog) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(consentCatalog));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteConsentCatalog(consentCatalog));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(ConsentCatalog consentCatalog) {
        ConsentCatalogDetails consentCatalogDetails = new ConsentCatalogDetails(consentCatalog);
        consentCatalogDetails.open();
    }

    private void deleteConsentCatalog(ConsentCatalog consentCatalog) {
        // This would be implemented to delete the consent catalog
        System.out.println("[DEBUG_LOG] Delete consent catalog: " + consentCatalog.getConsentType());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply consent type filter if not empty
        if (consentTypeFilter.getValue() != null && !consentTypeFilter.getValue().isEmpty()) {
            String consentTypeFilterValue = consentTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getConsentType() != null &&
                consentCatalog.getConsentType().toLowerCase().contains(consentTypeFilterValue));
        }

        // Apply description filter if not empty
        if (consentDescriptionFilter.getValue() != null && !consentDescriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = consentDescriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getConsentDescription() != null &&
                consentCatalog.getConsentDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply version filter if not empty
        if (consentVersionFilter.getValue() != null && !consentVersionFilter.getValue().isEmpty()) {
            String versionFilterValue = consentVersionFilter.getValue().toLowerCase();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getConsentVersion() != null &&
                consentCatalog.getConsentVersion().toLowerCase().contains(versionFilterValue));
        }

        // Apply source filter if not empty
        if (consentSourceFilter.getValue() != null && !consentSourceFilter.getValue().isEmpty()) {
            String sourceFilterValue = consentSourceFilter.getValue().toLowerCase();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getConsentSource() != null &&
                consentCatalog.getConsentSource().toLowerCase().contains(sourceFilterValue));
        }

        // Apply expiry period filter if not empty
        if (expiryPeriodDaysFilter.getValue() != null) {
            Integer expiryPeriodValue = expiryPeriodDaysFilter.getValue();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getExpiryPeriodDays() != null &&
                consentCatalog.getExpiryPeriodDays().equals(expiryPeriodValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(consentCatalog -> 
                consentCatalog.getDateCreated() != null && 
                !consentCatalog.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        consentTypeFilter.clear();
        consentDescriptionFilter.clear();
        consentVersionFilter.clear();
        consentSourceFilter.clear();
        expiryPeriodDaysFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateConsentCatalogDialog() {
        // This would be implemented to open a dialog for creating a new consent catalog
        System.out.println("[DEBUG_LOG] Create consent catalog dialog would open here");
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
        List<Grid.Column<ConsentCatalog>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<ConsentCatalog> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<ConsentCatalog> getMockConsentCatalogs() {
        List<ConsentCatalog> consentCatalogs = new ArrayList<>();

        consentCatalogs.add(new ConsentCatalog(1L, "Marketing", "Consent for marketing communications", 365, "1.0", "Web", "ACTIVE", LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        consentCatalogs.add(new ConsentCatalog(2L, "Data Sharing", "Consent for sharing data with third parties", 180, "2.1", "Mobile App", "ACTIVE", LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        consentCatalogs.add(new ConsentCatalog(3L, "Cookies", "Consent for using cookies", 90, "1.5", "Web", "INACTIVE", LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        consentCatalogs.add(new ConsentCatalog(4L, "Location", "Consent for location tracking", 30, "3.0", "Mobile App", "ACTIVE", LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        consentCatalogs.add(new ConsentCatalog(5L, "Biometrics", "Consent for biometric authentication", 730, "1.0", "Mobile App", "ACTIVE", LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        consentCatalogs.add(new ConsentCatalog(6L, "Profiling", "Consent for user profiling", 365, "2.0", "Web", "INACTIVE", LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        consentCatalogs.add(new ConsentCatalog(7L, "Email", "Consent for email communications", 180, "1.2", "Web", "ACTIVE", LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        consentCatalogs.add(new ConsentCatalog(8L, "SMS", "Consent for SMS notifications", 180, "1.0", "Mobile App", "ACTIVE", LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        consentCatalogs.add(new ConsentCatalog(9L, "Analytics", "Consent for analytics tracking", 90, "2.3", "Web", "INACTIVE", LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        consentCatalogs.add(new ConsentCatalog(10L, "Terms of Service", "Consent for terms of service", 0, "4.1", "Web", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));

        return consentCatalogs;
    }

    // Consent Catalog model class
    public static class ConsentCatalog {
        private Long consentId;
        private String consentType;
        private String consentDescription;
        private Integer expiryPeriodDays;
        private String consentVersion;
        private String consentSource;
        private String status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public ConsentCatalog(Long consentId, String consentType, String consentDescription, 
                             Integer expiryPeriodDays, String consentVersion, String consentSource, 
                             String status, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.consentId = consentId;
            this.consentType = consentType;
            this.consentDescription = consentDescription;
            this.expiryPeriodDays = expiryPeriodDays;
            this.consentVersion = consentVersion;
            this.consentSource = consentSource;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getConsentId() {
            return consentId;
        }

        public String getConsentType() {
            return consentType;
        }

        public String getConsentDescription() {
            return consentDescription;
        }

        public Integer getExpiryPeriodDays() {
            return expiryPeriodDays;
        }

        public String getConsentVersion() {
            return consentVersion;
        }

        public String getConsentSource() {
            return consentSource;
        }

        public String getStatus() {
            return status;
        }

        public boolean isActive() {
            return "ACTIVE".equals(status);
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}
