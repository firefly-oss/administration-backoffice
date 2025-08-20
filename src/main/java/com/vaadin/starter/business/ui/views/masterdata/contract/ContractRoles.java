package com.vaadin.starter.business.ui.views.masterdata.contract;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "contract-roles", layout = MainLayout.class)
@PageTitle("Contract Roles")
public class ContractRoles extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<ContractRole> grid;
    private Registration resizeListener;
    private ListDataProvider<ContractRole> dataProvider;
    private UI ui;

    // Search form fields
    private TextField roleCodeFilter;
    private TextField nameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public ContractRoles() {
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
        roleCodeFilter = new TextField();
        roleCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        roleCodeFilter.setClearButtonVisible(true);

        nameFilter = new TextField();
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.setClearButtonVisible(true);

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

        Button createRoleButton = UIUtils.createSuccessButton("Create Contract Role");
        createRoleButton.addClickListener(e -> openCreateContractRoleDialog());

        // Right-side search/clear
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Button layout with Create on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createRoleButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(roleCodeFilter, "Role Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(creationDateFilter, "Creation Date After");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Container for form and buttons
        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<ContractRole> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");
        grid.setId("contract-roles");
        grid.setSizeFull();

        grid.addColumn(ContractRole::getRoleCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Role Code")
                .setSortable(true);
        grid.addColumn(ContractRole::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(ContractRole::getDescription)
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

        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        dataProvider = DataProvider.ofCollection(getMockContractRoles());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(ContractRole role) {
        Icon icon;
        if (role.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(ContractRole role) {
        return new Span(role.getDateCreated() != null ? UIUtils.formatDate(role.getDateCreated().toLocalDate()) : "");
    }

    private Component createActionButtons(ContractRole role) {
        // View details button with eye icon
        Button viewButton = UIUtils.createButton(VaadinIcon.EYE);
        viewButton.addClickListener(e -> showDetails(role));
        viewButton.getElement().getThemeList().add("small");
        viewButton.getElement().getThemeList().add("tertiary");
        viewButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewButton);

        // Delete button with trash icon - red
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteContractRole(role));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        HorizontalLayout actions = new HorizontalLayout(viewButton, deleteButton);
        actions.setSpacing(true);
        actions.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        actions.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return actions;
    }

    private void showDetails(ContractRole role) {
        ContractRoleDetails details = new ContractRoleDetails(role);
        details.open();
    }

    private void deleteContractRole(ContractRole role) {
        UIUtils.showNotification("Deleting contract role: " + role.getRoleCode());
    }

    private void filter() {
        applyFilter();
    }

    private void applyFilter() {
        List<ContractRole> roles = getMockContractRoles();
        if (roleCodeFilter != null && !roleCodeFilter.getValue().trim().isEmpty()) {
            String code = roleCodeFilter.getValue().trim().toLowerCase();
            roles.removeIf(r -> r.getRoleCode() == null || !r.getRoleCode().toLowerCase().contains(code));
        }
        if (nameFilter != null && !nameFilter.getValue().trim().isEmpty()) {
            String name = nameFilter.getValue().trim().toLowerCase();
            roles.removeIf(r -> r.getName() == null || !r.getName().toLowerCase().contains(name));
        }
        if (descriptionFilter != null && !descriptionFilter.getValue().trim().isEmpty()) {
            String desc = descriptionFilter.getValue().trim().toLowerCase();
            roles.removeIf(r -> r.getDescription() == null || !r.getDescription().toLowerCase().contains(desc));
        }
        if (statusFilter != null && statusFilter.getValue() != null) {
            boolean active = statusFilter.getValue().equals("Active");
            roles.removeIf(r -> r.isActive() != active);
        }
        // creationDateFilter is present for parity with other views; not applied on mock data beyond placeholder

        dataProvider = DataProvider.ofCollection(roles);
        grid.setDataProvider(dataProvider);
    }

    private void clearFilter() {
        roleCodeFilter.clear();
        nameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();
        applyFilter();
    }

    private void openCreateContractRoleDialog() {
        UIUtils.showNotification("Open Create Contract Role dialog");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ui = attachEvent.getUI();
        Page page = ui.getPage();
        resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
        page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        if (resizeListener != null) {
            resizeListener.remove();
            resizeListener = null;
        }
    }

    private void updateVisibleColumns(int width) {
        boolean isMobile = width < MOBILE_BREAKPOINT;
        grid.getColumns().forEach(column -> column.setVisible(true));
        if (isMobile) {
            grid.getColumnByKey("Actions");
        }
    }

    private List<ContractRole> getMockContractRoles() {
        List<ContractRole> list = new ArrayList<>();
        list.add(new ContractRole(1L, "OWNER", "Owner", "Primary owner of the contract", true, LocalDateTime.now().minusDays(10)));
        list.add(new ContractRole(2L, "BENEF", "Beneficiary", "Receives benefits from the contract", true, LocalDateTime.now().minusDays(20)));
        list.add(new ContractRole(3L, "SIGNR", "Signer", "Authorized signatory", true, LocalDateTime.now().minusDays(5)));
        list.add(new ContractRole(4L, "BROKR", "Broker", "Intermediary role", false, LocalDateTime.now().minusDays(40)));
        list.add(new ContractRole(5L, "GUARN", "Guarantor", "Guarantees obligations", false, LocalDateTime.now().minusDays(60)));
        return list;
    }

    public static class ContractRole {
        private final Long roleId;
        private final String roleCode;
        private final String name;
        private final String description;
        private final Boolean isActive;
        private final LocalDateTime dateCreated;

        public ContractRole(Long roleId, String roleCode, String name, String description, Boolean isActive, LocalDateTime dateCreated) {
            this.roleId = roleId;
            this.roleCode = roleCode;
            this.name = name;
            this.description = description;
            this.isActive = isActive;
            this.dateCreated = dateCreated;
        }

        public Long getRoleId() { return roleId; }
        public String getRoleCode() { return roleCode; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Boolean getIsActive() { return isActive; }
        public boolean isActive() { return Boolean.TRUE.equals(isActive); }
        public LocalDateTime getDateCreated() { return dateCreated; }
    }
}
