package com.vaadin.starter.business.ui.views.masterdata.lookup;

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
import java.util.ArrayList;
import java.util.List;

@Route(value = "lookup-items", layout = MainLayout.class)
@PageTitle("Lookup Items")
public class LookupItems extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<LookupItem> grid;
    private Registration resizeListener;
    private ListDataProvider<LookupItem> dataProvider;
    private UI ui;

    // Search form fields
    private TextField itemCodeFilter;
    private TextField itemLabelFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<Long> domainIdFilter;
    private DatePicker effectiveFromFilter;
    private DatePicker effectiveToFilter;

    public LookupItems() {
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
        itemCodeFilter = new TextField();
        itemCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        itemCodeFilter.setClearButtonVisible(true);

        itemLabelFilter = new TextField();
        itemLabelFilter.setValueChangeMode(ValueChangeMode.EAGER);
        itemLabelFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        domainIdFilter = new ComboBox<>();
        // Populate with domain IDs from mock data
        List<Long> domainIds = new ArrayList<>();
        domainIds.add(1L);
        domainIds.add(2L);
        domainIds.add(3L);
        domainIdFilter.setItems(domainIds);
        domainIdFilter.setClearButtonVisible(true);

        effectiveFromFilter = new DatePicker();
        effectiveFromFilter.setClearButtonVisible(true);

        effectiveToFilter = new DatePicker();
        effectiveToFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createItemButton = UIUtils.createSuccessButton("Create Lookup Item");
        createItemButton.addClickListener(e -> openCreateItemDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Lookup Item on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createItemButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(itemCodeFilter, "Item Code");
        formLayout.addFormItem(itemLabelFilter, "Item Label");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(domainIdFilter, "Domain ID");
        formLayout.addFormItem(effectiveFromFilter, "Effective From");
        formLayout.addFormItem(effectiveToFilter, "Effective To");

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

    private Grid<LookupItem> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("lookup-items");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(LookupItem::getItemId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Item ID")
                .setSortable(true);
        grid.addColumn(LookupItem::getDomainId)
                .setAutoWidth(true)
                .setHeader("Domain ID")
                .setSortable(true);
        grid.addColumn(LookupItem::getItemCode)
                .setAutoWidth(true)
                .setHeader("Item Code")
                .setSortable(true);
        grid.addColumn(LookupItem::getItemLabelDefault)
                .setAutoWidth(true)
                .setHeader("Item Label")
                .setSortable(true);
        grid.addColumn(LookupItem::getItemDesc)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(LookupItem::getSortOrder)
                .setAutoWidth(true)
                .setHeader("Sort Order")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createEffectiveDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Effective Period")
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
        dataProvider = DataProvider.ofCollection(getMockLookupItems());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(LookupItem item) {
        Icon icon;
        if (item.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createEffectiveDate(LookupItem item) {
        String effectiveText = "";
        if (item.getEffectiveFrom() != null) {
            effectiveText += UIUtils.formatDate(item.getEffectiveFrom());
        } else {
            effectiveText += "N/A";
        }

        effectiveText += " - ";

        if (item.getEffectiveTo() != null) {
            effectiveText += UIUtils.formatDate(item.getEffectiveTo());
        } else {
            effectiveText += "N/A";
        }

        return new Div(new Span(effectiveText));
    }

    private Component createActionButtons(LookupItem item) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(item));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteItem(item));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(LookupItem item) {
        LookupItemDetails itemDetails = new LookupItemDetails(item);
        itemDetails.open();
    }

    private void deleteItem(LookupItem item) {
        // This would be implemented to delete the item
        System.out.println("[DEBUG_LOG] Delete lookup item: " + item.getItemCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply item code filter if not empty
        if (itemCodeFilter.getValue() != null && !itemCodeFilter.getValue().isEmpty()) {
            String itemCodeFilterValue = itemCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(item -> 
                item.getItemCode() != null &&
                item.getItemCode().toLowerCase().contains(itemCodeFilterValue));
        }

        // Apply item label filter if not empty
        if (itemLabelFilter.getValue() != null && !itemLabelFilter.getValue().isEmpty()) {
            String itemLabelFilterValue = itemLabelFilter.getValue().toLowerCase();
            dataProvider.addFilter(item -> 
                item.getItemLabelDefault() != null &&
                item.getItemLabelDefault().toLowerCase().contains(itemLabelFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(item -> 
                item.getItemDesc() != null &&
                item.getItemDesc().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            Status statusValue = "Active".equals(statusFilter.getValue()) ? Status.ACTIVE : Status.INACTIVE;
            dataProvider.addFilter(item -> 
                item.getStatus() == statusValue);
        }

        // Apply domain ID filter if selected
        if (domainIdFilter.getValue() != null) {
            Long domainIdValue = domainIdFilter.getValue();
            dataProvider.addFilter(item -> 
                item.getDomainId() != null && item.getDomainId().equals(domainIdValue));
        }

        // Apply effective from filter if selected
        if (effectiveFromFilter.getValue() != null) {
            LocalDate filterDate = effectiveFromFilter.getValue();
            dataProvider.addFilter(item -> 
                item.getEffectiveFrom() != null && 
                !item.getEffectiveFrom().isBefore(filterDate));
        }

        // Apply effective to filter if selected
        if (effectiveToFilter.getValue() != null) {
            LocalDate filterDate = effectiveToFilter.getValue();
            dataProvider.addFilter(item -> 
                item.getEffectiveTo() != null && 
                !item.getEffectiveTo().isAfter(filterDate));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        itemCodeFilter.clear();
        itemLabelFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        domainIdFilter.clear();
        effectiveFromFilter.clear();
        effectiveToFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateItemDialog() {
        // This would be implemented to open a dialog for creating a new lookup item
        LookupItem newItem = new LookupItem();
        newItem.setStatus(Status.ACTIVE);
        LookupItemDetails itemDetails = new LookupItemDetails(newItem);
        itemDetails.open();
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
        List<Grid.Column<LookupItem>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<LookupItem> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<LookupItem> getMockLookupItems() {
        List<LookupItem> items = new ArrayList<>();

        items.add(new LookupItem(1L, 1L, "COUNTRY_US", "United States", "USA", null, 1, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(2L, 1L, "COUNTRY_CA", "Canada", "Canada", null, 2, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(3L, 1L, "COUNTRY_MX", "Mexico", "Mexico", null, 3, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(4L, 2L, "CURRENCY_USD", "US Dollar", "USD", null, 1, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(5L, 2L, "CURRENCY_EUR", "Euro", "EUR", null, 2, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(6L, 2L, "CURRENCY_GBP", "British Pound", "GBP", null, 3, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(7L, 3L, "LANGUAGE_EN", "English", "English language", null, 1, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(8L, 3L, "LANGUAGE_ES", "Spanish", "Spanish language", null, 2, 
                LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), true, null, null, Status.ACTIVE));
        items.add(new LookupItem(9L, 3L, "LANGUAGE_FR", "French", "French language", null, 3, 
                LocalDate.now().minusYears(1), null, true, null, null, Status.ACTIVE));
        items.add(new LookupItem(10L, 3L, "LANGUAGE_DE", "German", "German language", null, 4, 
                LocalDate.now().minusYears(1), LocalDate.now().minusYears(1), false, null, null, Status.INACTIVE));

        return items;
    }

    // LookupItem model class
    public static class LookupItem {
        private Long itemId;
        private Long domainId;
        private String itemCode;
        private String itemLabelDefault;
        private String itemDesc;
        private Long parentItemId;
        private Integer sortOrder;
        private LocalDate effectiveFrom;
        private LocalDate effectiveTo;
        private Boolean isCurrent;
        private String extraJson;
        private Long tenantId;
        private Status status;

        public LookupItem() {
        }

        public LookupItem(Long itemId, Long domainId, String itemCode, String itemLabelDefault, String itemDesc,
                          Long parentItemId, Integer sortOrder, LocalDate effectiveFrom, LocalDate effectiveTo,
                          Boolean isCurrent, String extraJson, Long tenantId, Status status) {
            this.itemId = itemId;
            this.domainId = domainId;
            this.itemCode = itemCode;
            this.itemLabelDefault = itemLabelDefault;
            this.itemDesc = itemDesc;
            this.parentItemId = parentItemId;
            this.sortOrder = sortOrder;
            this.effectiveFrom = effectiveFrom;
            this.effectiveTo = effectiveTo;
            this.isCurrent = isCurrent;
            this.extraJson = extraJson;
            this.tenantId = tenantId;
            this.status = status;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getDomainId() {
            return domainId;
        }

        public void setDomainId(Long domainId) {
            this.domainId = domainId;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemLabelDefault() {
            return itemLabelDefault;
        }

        public void setItemLabelDefault(String itemLabelDefault) {
            this.itemLabelDefault = itemLabelDefault;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public Long getParentItemId() {
            return parentItemId;
        }

        public void setParentItemId(Long parentItemId) {
            this.parentItemId = parentItemId;
        }

        public Integer getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
        }

        public LocalDate getEffectiveFrom() {
            return effectiveFrom;
        }

        public void setEffectiveFrom(LocalDate effectiveFrom) {
            this.effectiveFrom = effectiveFrom;
        }

        public LocalDate getEffectiveTo() {
            return effectiveTo;
        }

        public void setEffectiveTo(LocalDate effectiveTo) {
            this.effectiveTo = effectiveTo;
        }

        public Boolean getIsCurrent() {
            return isCurrent;
        }

        public void setIsCurrent(Boolean isCurrent) {
            this.isCurrent = isCurrent;
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
