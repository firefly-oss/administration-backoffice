package com.vaadin.starter.business.ui.views.masterdata.language;
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

@Route(value = "language-locales", layout = MainLayout.class)
@PageTitle("Language Locales")
public class LanguageLocales extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<LanguageLocale> grid;
    private Registration resizeListener;
    private ListDataProvider<LanguageLocale> dataProvider;
    private UI ui;

    // Search form fields
    private TextField languageCodeFilter;
    private TextField countryCodeFilter;
    private TextField localeCodeFilter;
    private TextField languageNameFilter;
    private ComboBox<String> rtlFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public LanguageLocales() {
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
        languageCodeFilter = new TextField();
        languageCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        languageCodeFilter.setClearButtonVisible(true);

        countryCodeFilter = new TextField();
        countryCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        countryCodeFilter.setClearButtonVisible(true);

        localeCodeFilter = new TextField();
        localeCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        localeCodeFilter.setClearButtonVisible(true);

        languageNameFilter = new TextField();
        languageNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        languageNameFilter.setClearButtonVisible(true);

        rtlFilter = new ComboBox<>();
        rtlFilter.setItems("Yes", "No");
        rtlFilter.setClearButtonVisible(true);

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

        Button createLocaleButton = UIUtils.createSuccessButton("Create Language Locale");
        createLocaleButton.addClickListener(e -> openCreateLocaleDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Language Locale on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLocaleButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(languageCodeFilter, "Language Code");
        formLayout.addFormItem(countryCodeFilter, "Country Code");
        formLayout.addFormItem(localeCodeFilter, "Locale Code");
        formLayout.addFormItem(languageNameFilter, "Language Name");
        formLayout.addFormItem(rtlFilter, "RTL");
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

    private Grid<LanguageLocale> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("language-locales");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(LanguageLocale::getLanguageCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Language Code")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getCountryCode)
                .setAutoWidth(true)
                .setHeader("Country Code")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getLocaleCode)
                .setAutoWidth(true)
                .setHeader("Locale Code")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getLanguageName)
                .setAutoWidth(true)
                .setHeader("Language Name")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getNativeName)
                .setAutoWidth(true)
                .setHeader("Native Name")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getRegionName)
                .setAutoWidth(true)
                .setHeader("Region Name")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getRtl)
                .setAutoWidth(true)
                .setHeader("RTL")
                .setSortable(true);
        grid.addColumn(LanguageLocale::getSortOrder)
                .setAutoWidth(true)
                .setHeader("Sort Order")
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
        dataProvider = DataProvider.ofCollection(getMockLanguageLocales());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(LanguageLocale locale) {
        Icon icon;
        if (locale.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(LanguageLocale locale) {
        return new Span(UIUtils.formatDate(locale.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(LanguageLocale locale) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(locale));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteLocale(locale));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(LanguageLocale locale) {
        LanguageLocaleDetails localeDetails = new LanguageLocaleDetails(locale);
        localeDetails.open();
    }

    private void deleteLocale(LanguageLocale locale) {
        // This would be implemented to delete the locale
        System.out.println("[DEBUG_LOG] Delete language locale: " + locale.getLocaleCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply language code filter if not empty
        if (languageCodeFilter.getValue() != null && !languageCodeFilter.getValue().isEmpty()) {
            String languageCodeFilterValue = languageCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(locale -> 
                locale.getLanguageCode() != null &&
                locale.getLanguageCode().toLowerCase().contains(languageCodeFilterValue));
        }

        // Apply country code filter if not empty
        if (countryCodeFilter.getValue() != null && !countryCodeFilter.getValue().isEmpty()) {
            String countryCodeFilterValue = countryCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(locale -> 
                locale.getCountryCode() != null &&
                locale.getCountryCode().toLowerCase().contains(countryCodeFilterValue));
        }

        // Apply locale code filter if not empty
        if (localeCodeFilter.getValue() != null && !localeCodeFilter.getValue().isEmpty()) {
            String localeCodeFilterValue = localeCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(locale -> 
                locale.getLocaleCode() != null &&
                locale.getLocaleCode().toLowerCase().contains(localeCodeFilterValue));
        }

        // Apply language name filter if not empty
        if (languageNameFilter.getValue() != null && !languageNameFilter.getValue().isEmpty()) {
            String languageNameFilterValue = languageNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(locale -> 
                locale.getLanguageName() != null &&
                locale.getLanguageName().toLowerCase().contains(languageNameFilterValue));
        }

        // Apply RTL filter if selected
        if (rtlFilter.getValue() != null) {
            boolean isRtl = "Yes".equals(rtlFilter.getValue());
            dataProvider.addFilter(locale -> 
                locale.getRtl() != null && locale.getRtl() == isRtl);
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(locale -> 
                locale.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(locale -> 
                locale.getDateCreated() != null && 
                !locale.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        languageCodeFilter.clear();
        countryCodeFilter.clear();
        localeCodeFilter.clear();
        languageNameFilter.clear();
        rtlFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLocaleDialog() {
        // This would be implemented to open a dialog for creating a new language locale
        System.out.println("[DEBUG_LOG] Create language locale dialog would open here");
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
        List<Grid.Column<LanguageLocale>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<LanguageLocale> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<LanguageLocale> getMockLanguageLocales() {
        List<LanguageLocale> locales = new ArrayList<>();

        locales.add(new LanguageLocale(1L, "en", "US", "en_US", "English", "English", "United States", false, 1, Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        locales.add(new LanguageLocale(2L, "es", "ES", "es_ES", "Spanish", "Español", "Spain", false, 2, Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        locales.add(new LanguageLocale(3L, "fr", "FR", "fr_FR", "French", "Français", "France", false, 3, Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        locales.add(new LanguageLocale(4L, "de", "DE", "de_DE", "German", "Deutsch", "Germany", false, 4, Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        locales.add(new LanguageLocale(5L, "it", "IT", "it_IT", "Italian", "Italiano", "Italy", false, 5, Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        locales.add(new LanguageLocale(6L, "pt", "BR", "pt_BR", "Portuguese", "Português", "Brazil", false, 6, Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        locales.add(new LanguageLocale(7L, "ja", "JP", "ja_JP", "Japanese", "日本語", "Japan", false, 7, Status.ACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        locales.add(new LanguageLocale(8L, "zh", "CN", "zh_CN", "Chinese", "中文", "China", false, 8, Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        locales.add(new LanguageLocale(9L, "ar", "SA", "ar_SA", "Arabic", "العربية", "Saudi Arabia", true, 9, Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        locales.add(new LanguageLocale(10L, "ru", "RU", "ru_RU", "Russian", "Русский", "Russia", false, 10, Status.INACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return locales;
    }

    // LanguageLocale model class
    public static class LanguageLocale {
        private Long localeId;
        private String languageCode;
        private String countryCode;
        private String localeCode;
        private String languageName;
        private String nativeName;
        private String regionName;
        private Boolean rtl;
        private Integer sortOrder;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public LanguageLocale(Long localeId, String languageCode, String countryCode, String localeCode, 
                      String languageName, String nativeName, String regionName, Boolean rtl, 
                      Integer sortOrder, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.localeId = localeId;
            this.languageCode = languageCode;
            this.countryCode = countryCode;
            this.localeCode = localeCode;
            this.languageName = languageName;
            this.nativeName = nativeName;
            this.regionName = regionName;
            this.rtl = rtl;
            this.sortOrder = sortOrder;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getLocaleId() {
            return localeId;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getLocaleCode() {
            return localeCode;
        }

        public String getLanguageName() {
            return languageName;
        }

        public String getNativeName() {
            return nativeName;
        }

        public String getRegionName() {
            return regionName;
        }

        public Boolean getRtl() {
            return rtl;
        }

        public Integer getSortOrder() {
            return sortOrder;
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
