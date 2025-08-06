package com.vaadin.starter.business.ui.views.masterdata.country;

import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.sdks.services.MasterDataService;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryFilterRequest;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Route(value = "countries", layout = MainLayout.class)
@PageTitle("Countries")
public class Countries extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<CountryDTO> grid;
    private Registration resizeListener;
    private ListDataProvider<CountryDTO> dataProvider;
    private UI ui;

    private final MasterDataService masterDataService;

    // Search form fields
    private TextField isoCodeFilter;
    private TextField countryNameFilter;
    private ComboBox<CountryDTO.RegionEnum> regionLkpIdFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    @Autowired
    public Countries(MasterDataService masterDataService) {
        this.masterDataService = masterDataService;
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

        regionLkpIdFilter = new ComboBox<>();
        regionLkpIdFilter.setItems(CountryDTO.RegionEnum.values());
        regionLkpIdFilter.setItemLabelGenerator(region -> {
            if (region == null) return "";
            return region.name();
        });
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
        formLayout.addFormItem(regionLkpIdFilter, "Region");
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

    private Grid<CountryDTO> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("countries");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(CountryDTO::getIsoCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ISO Code")
                .setSortable(true);
        grid.addColumn(CountryDTO::getCountryName)
                .setAutoWidth(true)
                .setHeader("Country Name")
                .setSortable(true);
        grid.addColumn(CountryDTO::getRegion)
                .setAutoWidth(true)
                .setHeader("Region")
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

        // Initialize with empty data provider
        dataProvider = new ListDataProvider<>(List.of());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(CountryDTO country) {
        Icon icon;
        if (country.getStatus() != null && country.getStatus() == CountryDTO.StatusEnum.ACTIVE) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(CountryDTO country) {
        return new Span(UIUtils.formatDate(Objects.requireNonNull(country.getDateCreated()).toLocalDate()));
    }

    private Component createActionButtons(CountryDTO country) {
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

    private void showDetails(CountryDTO country) {
        CountryDetails countryDetails = new CountryDetails(country, masterDataService, updatedCountry -> {
            // Reload data from service to ensure consistency
            loadCountriesData();
        });
        countryDetails.open();
    }

    private void deleteCountry(CountryDTO country) {
        System.out.println("[DEBUG_LOG] Preparing to delete country: " + country.getIsoCode());

        if (country.getCountryId() == null) {
            System.out.println("[DEBUG_LOG] Cannot delete country with null ID");
            UIUtils.showNotification("Cannot delete country with null ID");
            return;
        }

        // Create confirmation dialog
        Dialog confirmDialog = new Dialog();
        confirmDialog.setCloseOnEsc(false);
        confirmDialog.setCloseOnOutsideClick(false);

        // Add header
        H3 header = new H3("Confirm Delete");
        header.getStyle().set("margin-top", "0");

        // Add confirmation message
        Span message = new Span("Are you sure you want to delete the country: " + country.getCountryName() + "? This action cannot be undone.");
        message.getStyle().set("color", "var(--lumo-error-text-color)");

        // Create buttons
        Button confirmButton = UIUtils.createErrorPrimaryButton("Delete");
        confirmButton.addClickListener(e -> {
            confirmDialog.close();

            System.out.println("[DEBUG_LOG] Deleting country with ID: " + country.getCountryId());

            // Call the service to delete the country
            masterDataService.deleteCountry(country.getCountryId())
                .subscribe(response -> {
                    System.out.println("[DEBUG_LOG] Delete response status: " + response.getStatusCode());
                    if (response.getStatusCode().is2xxSuccessful()) {
                        ui.access(() -> {
                            UIUtils.showNotification("Country deleted: " + country.getCountryName());
                            // Reload data to reflect changes
                            loadCountriesData();
                        });
                    } else {
                        ui.access(() -> {
                            UIUtils.showNotification("Failed to delete country: " + country.getCountryName());
                        });
                    }
                }, error -> {
                    System.out.println("[DEBUG_LOG] Error deleting country: " + error.getMessage());
                    ui.access(() -> {
                        UIUtils.showNotification("Error deleting country: " + error.getMessage());
                    });
                });
        });

        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> confirmDialog.close());

        // Create button layout
        HorizontalLayout buttonLayout = new HorizontalLayout(confirmButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setSpacing(true);
        buttonLayout.getStyle().set("margin-top", "20px");

        // Add components to dialog
        Div content = new Div();
        content.add(header, message, buttonLayout);
        content.getStyle().set("padding", "20px");
        confirmDialog.add(content);

        // Open dialog
        confirmDialog.open();
    }

    private void filter() {
        // Load countries data from service
        loadCountriesData();
    }

    private void loadCountriesData() {
        CountryFilterRequest filterRequest = new CountryFilterRequest();
        filterRequest.setPaginationPageNumber(0);
        filterRequest.setPaginationPageSize(100);
        filterRequest.setPaginationSortBy("country_id");
        filterRequest.setPaginationSortDirection("DESC");

        System.out.println("[DEBUG_LOG] Starting to load countries data");

        masterDataService.filterCountries(filterRequest)
                .subscribe(response -> {
                    System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        List<CountryDTO> sdkCountries = response.getBody().getContent();
                        System.out.println("[DEBUG_LOG] Received countries: " + (sdkCountries != null ? sdkCountries.size() : "null"));

                        if (sdkCountries != null && !sdkCountries.isEmpty()) {
                            System.out.println("[DEBUG_LOG] First country ID: " + sdkCountries.getFirst().getCountryId());
                        }

                        if (sdkCountries != null) {
                            // Convert CountryDTO to Country

                            // Use UI.access() to safely update the UI from the async callback
                            ui.access(() -> {
                                try {
                                    dataProvider = new ListDataProvider<>(sdkCountries);
                                    grid.setDataProvider(dataProvider);
                                    System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + sdkCountries.size() + " items");
                                    ui.push(); // Force UI update to the client
                                } catch (Exception e) {
                                    System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
                                }
                            });
                        }
                    } else {
                        System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
                    }
                }, error -> {
                    System.out.println("[DEBUG_LOG] Error in filterCountries: " + error.getMessage());

                    // Handle error in UI thread
                    ui.access(() -> {
                        // You could show a notification here
                        System.out.println("[DEBUG_LOG] Error handled in UI thread");
                    });
                });
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

        // Apply region filter if selected
        if (regionLkpIdFilter.getValue() != null) {
            CountryDTO.RegionEnum selectedRegion = regionLkpIdFilter.getValue();
            dataProvider.addFilter(country ->
                    country.getRegion() != null && country.getRegion().equals(selectedRegion));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(country ->
                    Objects.equals(country.getStatus(), isActive ? CountryDTO.StatusEnum.ACTIVE : CountryDTO.StatusEnum.INACTIVE));
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
        CountryCreationDialog dialog = new CountryCreationDialog(this::addCountry, masterDataService);
        dialog.open();
    }

    private void addCountry(CountryDTO country) {
        if (dataProvider != null) {
            UIUtils.showNotification("Country added: " + country.getCountryName());
            // Reload data from service to ensure consistency
            loadCountriesData();
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));

            // Load data after UI is completely initialized
            System.out.println("[DEBUG_LOG] UI attached, loading countries data");
            loadCountriesData();
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
        List<Grid.Column<CountryDTO>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<CountryDTO> column : columns) {
            column.setVisible(!mobile);
        }
    }
}
