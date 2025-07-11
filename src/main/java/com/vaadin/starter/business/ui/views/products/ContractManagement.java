package com.vaadin.starter.business.ui.views.products;

import com.catalis.common.contract.sdk.model.ContractDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.mapper.customers.ContractsMapper;
import com.vaadin.starter.business.backend.sdks.services.ContractsService;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractFilterRequest;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Route(value = "contract-management", layout = MainLayout.class)
@PageTitle("Contract Management")
public class ContractManagement extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ContractDTO> grid;
    private Registration resizeListener;
    private ListDataProvider<ContractDTO> dataProvider;
    private UI ui;

    // Filter form fields
    private TextField contractNumberFilter;
    private TextField contractTypeFilter;
    private TextField statusFilter;
    private TextField productIdFilter;
    private TextField descriptionFilter;
    private DatePicker startDateFilter;
    private DatePicker startDateFromFilter;
    private DatePicker startDateToFilter;
    private DatePicker endDateFilter;
    private DatePicker endDateFromFilter;
    private DatePicker endDateToFilter;
    private DatePicker dateCreatedFromFilter;
    private DatePicker dateCreatedToFilter;
    private DatePicker dateUpdatedFromFilter;
    private DatePicker dateUpdatedToFilter;

    private final ContractsService contractsService;
    private final ContractsMapper contractsMapper;

    @Autowired
    public ContractManagement(ContractsService contractsService, ContractsMapper contractsMapper) {
        this.contractsService = contractsService;
        this.contractsMapper = contractsMapper;
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        contractNumberFilter = new TextField();
        contractNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
        contractNumberFilter.setClearButtonVisible(true);

        contractTypeFilter = new TextField();
        contractTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        contractTypeFilter.setClearButtonVisible(true);

        statusFilter = new TextField();
        statusFilter.setValueChangeMode(ValueChangeMode.EAGER);
        statusFilter.setClearButtonVisible(true);

        productIdFilter = new TextField();
        productIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        productIdFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        startDateFilter = new DatePicker();
        startDateFilter.setClearButtonVisible(true);

        startDateFromFilter = new DatePicker();
        startDateFromFilter.setClearButtonVisible(true);

        startDateToFilter = new DatePicker();
        startDateToFilter.setClearButtonVisible(true);

        endDateFilter = new DatePicker();
        endDateFilter.setClearButtonVisible(true);

        endDateFromFilter = new DatePicker();
        endDateFromFilter.setClearButtonVisible(true);

        endDateToFilter = new DatePicker();
        endDateToFilter.setClearButtonVisible(true);

        dateCreatedFromFilter = new DatePicker();
        dateCreatedFromFilter.setClearButtonVisible(true);

        dateCreatedToFilter = new DatePicker();
        dateCreatedToFilter.setClearButtonVisible(true);

        dateUpdatedFromFilter = new DatePicker();
        dateUpdatedFromFilter.setClearButtonVisible(true);

        dateUpdatedToFilter = new DatePicker();
        dateUpdatedToFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilters());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilters());

        Button createContractButton = UIUtils.createSuccessButton("Create Contract");
        createContractButton.addClickListener(e -> openCreateContractDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Contract on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createContractButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(contractNumberFilter, "Contract Number");
        formLayout.addFormItem(contractTypeFilter, "Contract Type");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(productIdFilter, "Product ID");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(startDateFromFilter, "Start Date From");
        formLayout.addFormItem(startDateToFilter, "Start Date To");
        formLayout.addFormItem(endDateFromFilter, "End Date From");
        formLayout.addFormItem(endDateToFilter, "End Date To");
        formLayout.addFormItem(dateCreatedFromFilter, "Created From");
        formLayout.addFormItem(dateCreatedToFilter, "Created To");
        formLayout.addFormItem(dateUpdatedFromFilter, "Updated From");
        formLayout.addFormItem(dateUpdatedToFilter, "Updated To");

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

    private Grid<ContractDTO> createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
        grid.addThemeName("mobile");

        grid.setId("contracts");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(ContractDTO::getContractId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Contract ID")
                .setSortable(true);
        grid.addColumn(ContractDTO::getContractNumber)
                .setAutoWidth(true)
                .setHeader("Contract Number")
                .setSortable(true);
        grid.addColumn(contract -> contract.getContractType() != null ? contract.getContractType().toString() : "")
                .setAutoWidth(true)
                .setHeader("Contract Type")
                .setSortable(true);
        grid.addColumn(contract -> contract.getContractStatus() != null ? contract.getContractStatus().toString() : "")
                .setAutoWidth(true)
                .setHeader("Status")
                .setSortable(true);
        grid.addColumn(ContractDTO::getProductId)
                .setAutoWidth(true)
                .setHeader("Product ID")
                .setSortable(true);
        grid.addColumn(contract -> contract.getStartDate() != null ? contract.getStartDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) : "")
                .setAutoWidth(true)
                .setComparator(ContractDTO::getStartDate)
                .setFlexGrow(0)
                .setHeader("Start Date");
        grid.addColumn(contract -> contract.getEndDate() != null ? contract.getEndDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) : "")
                .setAutoWidth(true)
                .setComparator(ContractDTO::getEndDate)
                .setFlexGrow(0)
                .setHeader("End Date");
        grid.addColumn(ContractDTO::getDocumentManagerRef)
                .setAutoWidth(true)
                .setHeader("Document Ref")
                .setSortable(true);

        // Initialize with empty data provider
        dataProvider = new ListDataProvider<>(List.of());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private void loadContractsData() {
        ContractFilterRequest filterRequest = new ContractFilterRequest();
        filterRequest.setPaginationPageNumber(0);
        filterRequest.setPaginationPageSize(10);
        filterRequest.setPaginationSortBy("contractId");
        filterRequest.setPaginationSortDirection("DESC");

        System.out.println("[DEBUG_LOG] Starting to load contracts data");

        contractsService.filterContracts(filterRequest)
                .subscribe(response -> {
                    System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        List<ContractDTO> sdkContracts = response.getBody().getContent();
                        System.out.println("[DEBUG_LOG] Received contracts: " + (sdkContracts != null ? sdkContracts.size() : "null"));

                        if (sdkContracts != null && !sdkContracts.isEmpty()) {
                            System.out.println("[DEBUG_LOG] First contract ID: " + sdkContracts.get(0).getContractId());
                        }

                        if (sdkContracts != null) {
                            // Use UI.access() to safely update the UI from the async callback
                            ui.access(() -> {
                                try {
                                    dataProvider = new ListDataProvider<>(sdkContracts);
                                    grid.setDataProvider(dataProvider);
                                    System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + sdkContracts.size() + " items");
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
                    System.out.println("[DEBUG_LOG] Error in filterContracts: " + error.getMessage());

                    // Handle error in UI thread
                    ui.access(() -> {
                        // You could show a notification here
                        System.out.println("[DEBUG_LOG] Error handled in UI thread");
                    });
                });
    }

    private void viewDetails(ContractDTO contract) {
        // Navigate to contract details page
        // UI.getCurrent().navigate(ContractDetails.class, contract.getContractId());
        // For now, just log the contract ID
        System.out.println("[DEBUG_LOG] Viewing contract details for ID: " + contract.getContractId());
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
            System.out.println("[DEBUG_LOG] UI attached, loading contracts data");
            loadContractsData();
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
        List<Grid.Column<ContractDTO>> columns = grid.getColumns();

        // "Desktop" columns
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setVisible(!mobile);
        }
    }

    private void applyFilters() {
        if (dataProvider == null) {
            System.out.println("[DEBUG_LOG] DataProvider is null, cannot apply filters");
            return;
        }

        dataProvider.clearFilters();

        // Apply contract number filter
        if (contractNumberFilter.getValue() != null && !contractNumberFilter.getValue().isEmpty()) {
            String contractNumberValue = contractNumberFilter.getValue().toLowerCase();
            dataProvider.addFilter(contract ->
                    contract.getContractNumber() != null &&
                            contract.getContractNumber().toLowerCase().contains(contractNumberValue));
        }

        // Apply contract type filter
        if (contractTypeFilter.getValue() != null && !contractTypeFilter.getValue().isEmpty()) {
            String contractTypeValue = contractTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(contract ->
                    contract.getContractType() != null &&
                            contract.getContractType().toString().toLowerCase().contains(contractTypeValue));
        }

        // Apply status filter
        if (statusFilter.getValue() != null && !statusFilter.getValue().isEmpty()) {
            String statusValue = statusFilter.getValue().toLowerCase();
            dataProvider.addFilter(contract ->
                    contract.getContractStatus() != null &&
                            contract.getContractStatus().toString().toLowerCase().contains(statusValue));
        }

        // Apply product ID filter
        if (productIdFilter.getValue() != null && !productIdFilter.getValue().isEmpty()) {
            try {
                Long productId = Long.parseLong(productIdFilter.getValue());
                dataProvider.addFilter(contract -> contract.getProductId() != null && contract.getProductId().equals(productId));
            } catch (NumberFormatException e) {
                // Invalid ID format, ignore this filter
                System.out.println("[DEBUG_LOG] Invalid product ID format: " + productIdFilter.getValue());
            }
        }

        // Apply description filter
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(contract ->
                    contract.getDocumentManagerRef() != null &&
                            contract.getDocumentManagerRef().toLowerCase().contains(descriptionValue));
        }

        // Apply start date from filter
        if (startDateFromFilter.getValue() != null) {
            LocalDate fromDate = startDateFromFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getStartDate() != null &&
                            !contract.getStartDate().toLocalDate().isBefore(fromDate));
        }

        // Apply start date to filter
        if (startDateToFilter.getValue() != null) {
            LocalDate toDate = startDateToFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getStartDate() != null &&
                            !contract.getStartDate().toLocalDate().isAfter(toDate));
        }

        // Apply end date from filter
        if (endDateFromFilter.getValue() != null) {
            LocalDate fromDate = endDateFromFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getEndDate() != null &&
                            !contract.getEndDate().toLocalDate().isBefore(fromDate));
        }

        // Apply end date to filter
        if (endDateToFilter.getValue() != null) {
            LocalDate toDate = endDateToFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getEndDate() != null &&
                            !contract.getEndDate().toLocalDate().isAfter(toDate));
        }

        // Apply date created from filter
        if (dateCreatedFromFilter.getValue() != null) {
            LocalDate fromDate = dateCreatedFromFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getDateCreated() != null &&
                            !contract.getDateCreated().toLocalDate().isBefore(fromDate));
        }

        // Apply date created to filter
        if (dateCreatedToFilter.getValue() != null) {
            LocalDate toDate = dateCreatedToFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getDateCreated() != null &&
                            !contract.getDateCreated().toLocalDate().isAfter(toDate));
        }

        // Apply date updated from filter
        if (dateUpdatedFromFilter.getValue() != null) {
            LocalDate fromDate = dateUpdatedFromFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getDateUpdated() != null &&
                            !contract.getDateUpdated().toLocalDate().isBefore(fromDate));
        }

        // Apply date updated to filter
        if (dateUpdatedToFilter.getValue() != null) {
            LocalDate toDate = dateUpdatedToFilter.getValue();
            dataProvider.addFilter(contract ->
                    contract.getDateUpdated() != null &&
                            !contract.getDateUpdated().toLocalDate().isAfter(toDate));
        }

        System.out.println("[DEBUG_LOG] Filters applied successfully");
    }

    private void clearFilters() {
        // Clear all filter fields
        contractNumberFilter.clear();
        contractTypeFilter.clear();
        statusFilter.clear();
        productIdFilter.clear();
        descriptionFilter.clear();
        startDateFilter.clear();
        startDateFromFilter.clear();
        startDateToFilter.clear();
        endDateFilter.clear();
        endDateFromFilter.clear();
        endDateToFilter.clear();
        dateCreatedFromFilter.clear();
        dateCreatedToFilter.clear();
        dateUpdatedFromFilter.clear();
        dateUpdatedToFilter.clear();

        // Clear all filters from data provider
        if (dataProvider != null) {
            dataProvider.clearFilters();
            System.out.println("[DEBUG_LOG] All filters cleared");
        }
    }

    private void openCreateContractDialog() {
        // CreateContract createContractDialog = new CreateContract(contractsService, this::loadContractsData);
        // createContractDialog.open();
        System.out.println("[DEBUG_LOG] Create Contract dialog would open here");
    }
}
