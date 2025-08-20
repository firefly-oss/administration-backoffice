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

@Route(value = "role-operation-types", layout = MainLayout.class)
@PageTitle("Role Operation Types")
public class RoleOperationTypes extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<RoleOperationType> grid;
    private Registration resizeListener;
    private ListDataProvider<RoleOperationType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField operationCodeFilter;
    private TextField nameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public RoleOperationTypes() {
        setViewContent(createContent());
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
        operationCodeFilter = new TextField();
        operationCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        operationCodeFilter.setClearButtonVisible(true);

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

        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createButton = UIUtils.createSuccessButton("Create Role Operation Type");
        createButton.addClickListener(e -> openCreateDialog());

        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        HorizontalLayout buttonLayout = new HorizontalLayout(createButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(operationCodeFilter, "Operation Code");
        formLayout.addFormItem(nameFilter, "Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(creationDateFilter, "Creation Date After");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<RoleOperationType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("role-operation-types");
        grid.setSizeFull();

        grid.addColumn(RoleOperationType::getOperationCode)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Operation Code")
                .setSortable(true);
        grid.addColumn(RoleOperationType::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(RoleOperationType::getDescription)
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

        dataProvider = DataProvider.ofCollection(getMockRoleOperationTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(RoleOperationType item) {
        return item.isActive() ? UIUtils.createPrimaryIcon(VaadinIcon.CHECK) : UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
    }

    private Component createDate(RoleOperationType item) {
        return new Span(item.getDateCreated() != null ? UIUtils.formatDate(item.getDateCreated().toLocalDate()) : "");
    }

    private Component createActionButtons(RoleOperationType item) {
        Button viewButton = UIUtils.createButton(VaadinIcon.EYE);
        viewButton.addClickListener(e -> showDetails(item));
        viewButton.getElement().getThemeList().add("small");
        viewButton.getElement().getThemeList().add("tertiary");
        viewButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewButton);

        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteItem(item));
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

    private void showDetails(RoleOperationType item) {
        RoleOperationTypeDetails dialog = new RoleOperationTypeDetails(item);
        dialog.open();
    }

    private void deleteItem(RoleOperationType item) {
        UIUtils.showNotification("Deleting role operation type: " + item.getOperationCode());
    }

    private void filter() {
        dataProvider = DataProvider.ofCollection(getMockRoleOperationTypes());
        grid.setDataProvider(dataProvider);
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        if (operationCodeFilter.getValue() != null && !operationCodeFilter.getValue().isEmpty()) {
            String v = operationCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(i -> i.getOperationCode() != null && i.getOperationCode().toLowerCase().contains(v));
        }
        if (nameFilter.getValue() != null && !nameFilter.getValue().isEmpty()) {
            String v = nameFilter.getValue().toLowerCase();
            dataProvider.addFilter(i -> i.getName() != null && i.getName().toLowerCase().contains(v));
        }
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String v = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(i -> i.getDescription() != null && i.getDescription().toLowerCase().contains(v));
        }
        if (statusFilter.getValue() != null) {
            boolean active = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(i -> i.isActive() == active);
        }
        if (creationDateFilter.getValue() != null) {
            LocalDate filterDate = creationDateFilter.getValue();
            dataProvider.addFilter(i -> i.getDateCreated() != null && !i.getDateCreated().toLocalDate().isBefore(filterDate));
        }
    }

    private void clearFilter() {
        operationCodeFilter.clear();
        nameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();
        dataProvider.clearFilters();
    }

    private void openCreateDialog() {
        UIUtils.showNotification("Open Create Role Operation Type dialog");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        this.ui = attachEvent.getUI();
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
            // Could hide some columns for mobile, kept simple for now
        }
    }

    private List<RoleOperationType> getMockRoleOperationTypes() {
        List<RoleOperationType> list = new ArrayList<>();
        list.add(new RoleOperationType(1L, "OP_VIEW", "View", "Permission to view", true, LocalDateTime.now().minusDays(10)));
        list.add(new RoleOperationType(2L, "OP_EDIT", "Edit", "Permission to edit", true, LocalDateTime.now().minusDays(8)));
        list.add(new RoleOperationType(3L, "OP_DELETE", "Delete", "Permission to delete", false, LocalDateTime.now().minusDays(20)));
        list.add(new RoleOperationType(4L, "OP_APPROVE", "Approve", "Permission to approve", true, LocalDateTime.now().minusDays(3)));
        list.add(new RoleOperationType(5L, "OP_EXPORT", "Export", "Permission to export data", true, LocalDateTime.now().minusDays(1)));
        return list;
    }

    public static class RoleOperationType {
        private final Long id;
        private final String operationCode;
        private final String name;
        private final String description;
        private final Boolean isActive;
        private final LocalDateTime dateCreated;

        public RoleOperationType(Long id, String operationCode, String name, String description, Boolean isActive, LocalDateTime dateCreated) {
            this.id = id;
            this.operationCode = operationCode;
            this.name = name;
            this.description = description;
            this.isActive = isActive;
            this.dateCreated = dateCreated;
        }

        public Long getId() { return id; }
        public String getOperationCode() { return operationCode; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Boolean getIsActive() { return isActive; }
        public boolean isActive() { return Boolean.TRUE.equals(isActive); }
        public LocalDateTime getDateCreated() { return dateCreated; }
    }
}
