package com.vaadin.starter.business.ui.views.masterdata.lookup;

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

import java.util.ArrayList;
import java.util.List;

@Route(value = "lookup-domains", layout = MainLayout.class)
@PageTitle("Lookup Domains")
public class LookupDomains extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<LookupDomain> grid;
    private Registration resizeListener;
    private ListDataProvider<LookupDomain> dataProvider;
    private UI ui;

    // Search form fields
    private TextField domainCodeFilter;
    private TextField domainNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<String> hierarchyAllowedFilter;
    private ComboBox<String> multiselectAllowedFilter;

    public LookupDomains() {
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
        domainCodeFilter = new TextField();
        domainCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        domainCodeFilter.setClearButtonVisible(true);

        domainNameFilter = new TextField();
        domainNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        domainNameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        hierarchyAllowedFilter = new ComboBox<>();
        hierarchyAllowedFilter.setItems("Yes", "No");
        hierarchyAllowedFilter.setClearButtonVisible(true);

        multiselectAllowedFilter = new ComboBox<>();
        multiselectAllowedFilter.setItems("Yes", "No");
        multiselectAllowedFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createDomainButton = UIUtils.createSuccessButton("Create Lookup Domain");
        createDomainButton.addClickListener(e -> openCreateDomainDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Lookup Domain on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createDomainButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(domainCodeFilter, "Domain Code");
        formLayout.addFormItem(domainNameFilter, "Domain Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(hierarchyAllowedFilter, "Hierarchy Allowed");
        formLayout.addFormItem(multiselectAllowedFilter, "Multiselect Allowed");

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

    private Grid<LookupDomain> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("lookup-domains");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(LookupDomain::getDomainId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Domain ID")
                .setSortable(true);
        grid.addColumn(LookupDomain::getDomainCode)
                .setAutoWidth(true)
                .setHeader("Domain Code")
                .setSortable(true);
        grid.addColumn(LookupDomain::getDomainName)
                .setAutoWidth(true)
                .setHeader("Domain Name")
                .setSortable(true);
        grid.addColumn(LookupDomain::getDomainDesc)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createHierarchyAllowed))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Hierarchy Allowed")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createMultiselectAllowed))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Multiselect Allowed")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);

        // Add Actions column with view and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(getMockLookupDomains());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(LookupDomain domain) {
        Icon icon;
        if (domain.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createHierarchyAllowed(LookupDomain domain) {
        Icon icon;
        if (domain.getHierarchyAllowed() != null && domain.getHierarchyAllowed()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createMultiselectAllowed(LookupDomain domain) {
        Icon icon;
        if (domain.getMultiselectAllowed() != null && domain.getMultiselectAllowed()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createActionButtons(LookupDomain domain) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(domain));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteDomain(domain));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(LookupDomain domain) {
        LookupDomainDetails domainDetails = new LookupDomainDetails(domain);
        domainDetails.open();
    }

    private void deleteDomain(LookupDomain domain) {
        // This would be implemented to delete the domain
        System.out.println("[DEBUG_LOG] Delete lookup domain: " + domain.getDomainCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply domain code filter if not empty
        if (domainCodeFilter.getValue() != null && !domainCodeFilter.getValue().isEmpty()) {
            String domainCodeFilterValue = domainCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(domain -> 
                domain.getDomainCode() != null &&
                domain.getDomainCode().toLowerCase().contains(domainCodeFilterValue));
        }

        // Apply domain name filter if not empty
        if (domainNameFilter.getValue() != null && !domainNameFilter.getValue().isEmpty()) {
            String domainNameFilterValue = domainNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(domain -> 
                domain.getDomainName() != null &&
                domain.getDomainName().toLowerCase().contains(domainNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(domain -> 
                domain.getDomainDesc() != null &&
                domain.getDomainDesc().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            Status statusValue = "Active".equals(statusFilter.getValue()) ? Status.ACTIVE : Status.INACTIVE;
            dataProvider.addFilter(domain -> 
                domain.getStatus() == statusValue);
        }

        // Apply hierarchy allowed filter if selected
        if (hierarchyAllowedFilter.getValue() != null) {
            boolean hierarchyAllowed = "Yes".equals(hierarchyAllowedFilter.getValue());
            dataProvider.addFilter(domain -> 
                domain.getHierarchyAllowed() != null && domain.getHierarchyAllowed() == hierarchyAllowed);
        }

        // Apply multiselect allowed filter if selected
        if (multiselectAllowedFilter.getValue() != null) {
            boolean multiselectAllowed = "Yes".equals(multiselectAllowedFilter.getValue());
            dataProvider.addFilter(domain -> 
                domain.getMultiselectAllowed() != null && domain.getMultiselectAllowed() == multiselectAllowed);
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        domainCodeFilter.clear();
        domainNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        hierarchyAllowedFilter.clear();
        multiselectAllowedFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateDomainDialog() {
        // This would be implemented to open a dialog for creating a new lookup domain
        LookupDomain newDomain = new LookupDomain();
        newDomain.setStatus(Status.ACTIVE);
        LookupDomainDetails domainDetails = new LookupDomainDetails(newDomain);
        domainDetails.open();
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
        List<Grid.Column<LookupDomain>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<LookupDomain> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<LookupDomain> getMockLookupDomains() {
        List<LookupDomain> domains = new ArrayList<>();

        domains.add(new LookupDomain(1L, "COUNTRY", "Countries", "List of countries", 
                null, false, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(2L, "CURRENCY", "Currencies", "List of currencies", 
                null, false, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(3L, "LANGUAGE", "Languages", "List of languages", 
                null, false, true, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(4L, "REGION", "Regions", "List of regions", 
                1L, true, false, true, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(5L, "DOCUMENT_TYPE", "Document Types", "Types of documents", 
                null, true, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(6L, "PAYMENT_METHOD", "Payment Methods", "Available payment methods", 
                null, false, true, true, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(7L, "ACCOUNT_TYPE", "Account Types", "Types of accounts", 
                null, false, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(8L, "RELATIONSHIP", "Relationship Types", "Types of relationships", 
                null, true, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(9L, "INDUSTRY", "Industries", "List of industries", 
                null, true, false, false, null, null, Status.ACTIVE));
        domains.add(new LookupDomain(10L, "STATUS_REASON", "Status Reasons", "Reasons for status changes", 
                null, false, false, true, null, null, Status.INACTIVE));

        return domains;
    }

    // LookupDomain model class
    public static class LookupDomain {
        private Long domainId;
        private String domainCode;
        private String domainName;
        private String domainDesc;
        private Long parentDomainId;
        private Boolean hierarchyAllowed;
        private Boolean multiselectAllowed;
        private Boolean tenantOverridable;
        private String extraJson;
        private Long tenantId;
        private Status status;

        public LookupDomain() {
        }

        public LookupDomain(Long domainId, String domainCode, String domainName, String domainDesc,
                            Long parentDomainId, Boolean hierarchyAllowed, Boolean multiselectAllowed,
                            Boolean tenantOverridable, String extraJson, Long tenantId, Status status) {
            this.domainId = domainId;
            this.domainCode = domainCode;
            this.domainName = domainName;
            this.domainDesc = domainDesc;
            this.parentDomainId = parentDomainId;
            this.hierarchyAllowed = hierarchyAllowed;
            this.multiselectAllowed = multiselectAllowed;
            this.tenantOverridable = tenantOverridable;
            this.extraJson = extraJson;
            this.tenantId = tenantId;
            this.status = status;
        }

        public Long getDomainId() {
            return domainId;
        }

        public void setDomainId(Long domainId) {
            this.domainId = domainId;
        }

        public String getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(String domainCode) {
            this.domainCode = domainCode;
        }

        public String getDomainName() {
            return domainName;
        }

        public void setDomainName(String domainName) {
            this.domainName = domainName;
        }

        public String getDomainDesc() {
            return domainDesc;
        }

        public void setDomainDesc(String domainDesc) {
            this.domainDesc = domainDesc;
        }

        public Long getParentDomainId() {
            return parentDomainId;
        }

        public void setParentDomainId(Long parentDomainId) {
            this.parentDomainId = parentDomainId;
        }

        public Boolean getHierarchyAllowed() {
            return hierarchyAllowed;
        }

        public void setHierarchyAllowed(Boolean hierarchyAllowed) {
            this.hierarchyAllowed = hierarchyAllowed;
        }

        public Boolean getMultiselectAllowed() {
            return multiselectAllowed;
        }

        public void setMultiselectAllowed(Boolean multiselectAllowed) {
            this.multiselectAllowed = multiselectAllowed;
        }

        public Boolean getTenantOverridable() {
            return tenantOverridable;
        }

        public void setTenantOverridable(Boolean tenantOverridable) {
            this.tenantOverridable = tenantOverridable;
        }

        public String getExtraJson() {
            return extraJson;
        }

        public void setExtraJson(String extraJson) {
            this.extraJson = extraJson;
        }

        public Long getTenantId() {
            return tenantId;
        }

        public void setTenantId(Long tenantId) {
            this.tenantId = tenantId;
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
    }
}
