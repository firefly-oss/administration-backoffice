package com.vaadin.starter.business.ui.views.masterdata.bank;

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
import java.util.Random;

@Route(value = "bank-institution-codes", layout = MainLayout.class)
@PageTitle("Bank Institution Codes")
public class BankInstitutionCodes extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<BankInstitutionCode> grid;
    private Registration resizeListener;
    private ListDataProvider<BankInstitutionCode> dataProvider;
    private UI ui;

    // Search form fields
    private TextField bankNameFilter;
    private TextField swiftCodeFilter;
    private TextField routingNumberFilter;
    private TextField ibanPrefixFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public BankInstitutionCodes() {
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
        bankNameFilter = new TextField();
        bankNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        bankNameFilter.setClearButtonVisible(true);

        swiftCodeFilter = new TextField();
        swiftCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        swiftCodeFilter.setClearButtonVisible(true);

        routingNumberFilter = new TextField();
        routingNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
        routingNumberFilter.setClearButtonVisible(true);

        ibanPrefixFilter = new TextField();
        ibanPrefixFilter.setValueChangeMode(ValueChangeMode.EAGER);
        ibanPrefixFilter.setClearButtonVisible(true);

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

        Button createBankInstitutionCodeButton = UIUtils.createSuccessButton("Create Bank Institution Code");
        createBankInstitutionCodeButton.addClickListener(e -> openCreateBankInstitutionCodeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Bank Institution Code on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createBankInstitutionCodeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(bankNameFilter, "Bank Name");
        formLayout.addFormItem(swiftCodeFilter, "SWIFT Code");
        formLayout.addFormItem(routingNumberFilter, "Routing Number");
        formLayout.addFormItem(ibanPrefixFilter, "IBAN Prefix");
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

    private Grid<BankInstitutionCode> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("bank-institution-codes");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(BankInstitutionCode::getBankName)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Bank Name")
                .setSortable(true);
        grid.addColumn(BankInstitutionCode::getSwiftCode)
                .setAutoWidth(true)
                .setHeader("SWIFT Code")
                .setSortable(true);
        grid.addColumn(BankInstitutionCode::getRoutingNumber)
                .setAutoWidth(true)
                .setHeader("Routing Number")
                .setSortable(true);
        grid.addColumn(BankInstitutionCode::getIbanPrefix)
                .setAutoWidth(true)
                .setHeader("IBAN Prefix")
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
        dataProvider = DataProvider.ofCollection(getMockBankInstitutionCodes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(BankInstitutionCode bankInstitutionCode) {
        Icon icon;
        if (bankInstitutionCode.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(BankInstitutionCode bankInstitutionCode) {
        return new Span(UIUtils.formatDate(bankInstitutionCode.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(BankInstitutionCode bankInstitutionCode) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(bankInstitutionCode));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteBankInstitutionCode(bankInstitutionCode));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(BankInstitutionCode bankInstitutionCode) {
        BankInstitutionCodeDetails bankInstitutionCodeDetails = new BankInstitutionCodeDetails(bankInstitutionCode);
        bankInstitutionCodeDetails.open();
    }

    private void deleteBankInstitutionCode(BankInstitutionCode bankInstitutionCode) {
        // This would be implemented to delete the bank institution code
        System.out.println("[DEBUG_LOG] Delete bank institution code: " + bankInstitutionCode.getBankName());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply bank name filter if not empty
        if (bankNameFilter.getValue() != null && !bankNameFilter.getValue().isEmpty()) {
            String bankNameFilterValue = bankNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.getBankName() != null &&
                bankInstitutionCode.getBankName().toLowerCase().contains(bankNameFilterValue));
        }

        // Apply SWIFT code filter if not empty
        if (swiftCodeFilter.getValue() != null && !swiftCodeFilter.getValue().isEmpty()) {
            String swiftCodeFilterValue = swiftCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.getSwiftCode() != null &&
                bankInstitutionCode.getSwiftCode().toLowerCase().contains(swiftCodeFilterValue));
        }

        // Apply routing number filter if not empty
        if (routingNumberFilter.getValue() != null && !routingNumberFilter.getValue().isEmpty()) {
            String routingNumberFilterValue = routingNumberFilter.getValue().toLowerCase();
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.getRoutingNumber() != null &&
                bankInstitutionCode.getRoutingNumber().toLowerCase().contains(routingNumberFilterValue));
        }

        // Apply IBAN prefix filter if not empty
        if (ibanPrefixFilter.getValue() != null && !ibanPrefixFilter.getValue().isEmpty()) {
            String ibanPrefixFilterValue = ibanPrefixFilter.getValue().toLowerCase();
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.getIbanPrefix() != null &&
                bankInstitutionCode.getIbanPrefix().toLowerCase().contains(ibanPrefixFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.isActive() == isActive);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(bankInstitutionCode -> 
                bankInstitutionCode.getDateCreated() != null && 
                !bankInstitutionCode.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        bankNameFilter.clear();
        swiftCodeFilter.clear();
        routingNumberFilter.clear();
        ibanPrefixFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateBankInstitutionCodeDialog() {
        // This would be implemented to open a dialog for creating a new bank institution code
        System.out.println("[DEBUG_LOG] Create bank institution code dialog would open here");
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
        List<Grid.Column<BankInstitutionCode>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<BankInstitutionCode> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<BankInstitutionCode> getMockBankInstitutionCodes() {
        List<BankInstitutionCode> bankInstitutionCodes = new ArrayList<>();

        bankInstitutionCodes.add(new BankInstitutionCode(1L, "Bank of America", "BOFAUS3N", "026009593", "US", 1L, 1L, true, null, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        bankInstitutionCodes.add(new BankInstitutionCode(2L, "JPMorgan Chase", "CHASUS33", "021000021", "US", 1L, 1L, true, null, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        bankInstitutionCodes.add(new BankInstitutionCode(3L, "Wells Fargo", "WFBIUS6S", "121000248", "US", 1L, 1L, false, null, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        bankInstitutionCodes.add(new BankInstitutionCode(4L, "Citibank", "CITIUS33", "021000089", "US", 1L, 1L, true, null, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        bankInstitutionCodes.add(new BankInstitutionCode(5L, "HSBC", "MRMDUS33", "021001088", "US", 1L, 1L, true, null, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        bankInstitutionCodes.add(new BankInstitutionCode(6L, "Barclays", "BARCGB22", "200000", "GB", 2L, 1L, false, null, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        bankInstitutionCodes.add(new BankInstitutionCode(7L, "Deutsche Bank", "DEUTDEFF", "10070000", "DE", 3L, 1L, true, null, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        bankInstitutionCodes.add(new BankInstitutionCode(8L, "BNP Paribas", "BNPAFRPP", "30004", "FR", 4L, 1L, true, null, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        bankInstitutionCodes.add(new BankInstitutionCode(9L, "Santander", "BSCHESMM", "0049", "ES", 5L, 1L, false, null, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        bankInstitutionCodes.add(new BankInstitutionCode(10L, "UniCredit", "UNCRITMM", "02008", "IT", 6L, 1L, true, null, LocalDateTime.now(), LocalDateTime.now()));

        return bankInstitutionCodes;
    }

    // Bank Institution Code model class
    public static class BankInstitutionCode {
        private Long institutionId;
        private String bankName;
        private String swiftCode;
        private String routingNumber;
        private String ibanPrefix;
        private Long countryId;
        private Long institutionTypeLkpId;
        private boolean active; // Represents StatusEnum
        private String svgIcon;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public BankInstitutionCode(Long institutionId, String bankName, String swiftCode, String routingNumber, 
                                  String ibanPrefix, Long countryId, Long institutionTypeLkpId, 
                                  boolean active, String svgIcon, LocalDateTime dateCreated, 
                                  LocalDateTime dateUpdated) {
            this.institutionId = institutionId;
            this.bankName = bankName;
            this.swiftCode = swiftCode;
            this.routingNumber = routingNumber;
            this.ibanPrefix = ibanPrefix;
            this.countryId = countryId;
            this.institutionTypeLkpId = institutionTypeLkpId;
            this.active = active;
            this.svgIcon = svgIcon;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getInstitutionId() {
            return institutionId;
        }

        public String getBankName() {
            return bankName;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public String getRoutingNumber() {
            return routingNumber;
        }

        public String getIbanPrefix() {
            return ibanPrefix;
        }

        public Long getCountryId() {
            return countryId;
        }

        public Long getInstitutionTypeLkpId() {
            return institutionTypeLkpId;
        }

        public boolean isActive() {
            return active;
        }

        public String getSvgIcon() {
            return svgIcon;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }
    }
}